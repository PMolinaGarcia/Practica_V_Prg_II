package pr2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import util.Graph;

/**
 * Clase de pruebas unitarias para la clase {@link Graph}.
 * <p>
 * Contiene tests que validan:
 * <ul>
 *     <li>La correcta creación de un grafo con vértices y aristas.</li>
 *     <li>La exactitud de la lista de adyacencia.</li>
 *     <li>El correcto cálculo del camino más corto entre dos vértices.</li>
 * </ul>
 */
public class AppTest {

    /**
     * Verifica que los vértices se añaden correctamente al grafo
     * al insertar aristas, y que el método {@code containsVertex}
     * devuelve {@code true} para todos los vértices esperados.
     */
    @Test
    public void testCreacionGrafo() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        assertTrue(g.containsVertex(1));
        assertTrue(g.containsVertex(2));
        assertTrue(g.containsVertex(3));
        assertTrue(g.containsVertex(4));
        assertTrue(g.containsVertex(5));
    }

    /**
     * Verifica que la lista de adyacencia se construye correctamente.
     * Para cada vértice, se comprueba que el número de adyacentes
     * coincide con el esperado tras añadir las aristas.
     */
    @Test
    public void testListaAdyacencia() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        assertEquals(2, g.obtainAdjacents(1).size()); // 1 -> [2, 5]
        assertEquals(1, g.obtainAdjacents(2).size()); // 2 -> [3]
        assertEquals(1, g.obtainAdjacents(3).size()); // 3 -> [4]
        assertEquals(0, g.obtainAdjacents(4).size()); // 4 -> []
        assertEquals(1, g.obtainAdjacents(5).size()); // 5 -> [4]
    }

    /**
     * Verifica que el método {@code shortestPath} calcula correctamente
     * el camino más corto entre dos vértices.
     * Se validan varios casos:
     * <ul>
     *     <li>Camino directo: 1 → 5</li>
     *     <li>Camino de un solo nodo: 1 → 1</li>
     *     <li>Camino más corto entre 1 y 4: 1 → 5 → 4 (no 1 → 2 → 3 → 4)</li>
     * </ul>
     */
    @Test
    public void testCaminoMasCorto() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        assertEquals(2, g.shortestPath(1, 5).size());  // [1, 5]
        assertEquals(1, g.shortestPath(1, 1).size());  // [1]
        assertEquals(3, g.shortestPath(1, 4).size());  // [1, 5, 4]
    }
}

