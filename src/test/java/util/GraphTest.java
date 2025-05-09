package util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase de prueba unitaria para verificar el funcionamiento del método
 * {@link Graph#shortestPath(Object, Object)} en la clase {@link Graph}.
 */
public class GraphTest {

    /**
     * Prueba que el método {@code shortestPath(V v1, V v2)} encuentra el camino
     * más corto entre dos vértices cuando este existe.
     * <p>
     * Se construye un grafo dirigido con múltiples caminos entre los nodos 1 y 4.
     * El método debe devolver el camino más corto, en este caso:
     * <pre>
     *     1 → 5 → 4
     * </pre>
     * El camino esperado se compara con el devuelto por {@code shortestPath()}.
     */
    @Test
    public void shortestPathFindsAPath() {
        System.out.println("\nTest shortestPathFindsAPath");
        System.out.println("----------------------------");

        // Construcción del grafo
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        // Camino esperado: 1 → 5 → 4
        List<Integer> expectedPath = new ArrayList<>();
        expectedPath.add(1);
        expectedPath.add(5);
        expectedPath.add(4);

        // Verificación de igualdad con el camino obtenido
        assertEquals(expectedPath, g.shortestPath(1, 4));
    }
}
