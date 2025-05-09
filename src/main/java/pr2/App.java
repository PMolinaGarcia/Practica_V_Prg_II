package pr2;

import util.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase de prueba principal para demostrar el funcionamiento de la clase {@link Graph}.
 * <p>
 * Se crea un grafo dirigido de ejemplo, se imprimen sus conexiones y
 * se prueban algunos caminos más cortos entre distintos vértices.
 */
public class App {

    /**
     * Método principal que ejecuta las pruebas sobre el grafo.
     *
     * @param args argumentos pasados por la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Creación de un grafo dirigido de enteros
        System.out.println("\nGrafo de prueba");
        System.out.println("----------------------------");
        Graph<Integer> g = new Graph<>();

        // Añade aristas al grafo
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(5, 4);

        // Imprime la lista de adyacencia del grafo
        System.out.println(g);

        // Imprime el camino más corto entre 1 y 4
        System.out.println(g.shortestPath(1, 4));

        // Camino más corto de un nodo a sí mismo
        System.out.println(g.shortestPath(1, 1));

        // Camino más corto desde 2 hasta 4
        System.out.println(g.shortestPath(2, 4));
    }
}
