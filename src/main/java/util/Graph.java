package util;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;

/**
 * Clase que representa un grafo dirigido utilizando una lista de adyacencia.
 * Permite añadir vértices, añadir aristas y encontrar el camino más corto entre dos vértices.
 *
 * @param <V> el tipo de los vértices del grafo.
 */
public class Graph<V> {

    // Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    /**
     * Añade el vértice {@code v} al grafo.
     *
     * @param v vértice a añadir.
     * @return {@code true} si el vértice no estaba anteriormente en el grafo, {@code false} en caso contrario.
     */
    public boolean addVertex(V v) {
        if (!adjacencyList.containsKey(v)) {
            adjacencyList.put(v, new HashSet<>());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Añade un arco dirigido entre los vértices {@code v1} y {@code v2}.
     * Si alguno de los vértices no existe en el grafo, también se añade.
     *
     * @param v1 el vértice origen del arco.
     * @param v2 el vértice destino del arco.
     * @return {@code true} si el arco no existía previamente, {@code false} en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        addVertex(v1);
        addVertex(v2);
        return adjacencyList.get(v1).add(v2);
    }

    /**
     * Devuelve el conjunto de vértices adyacentes a un vértice dado.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes, o {@code null} si el vértice no existe.
     * @throws Exception si ocurre un error al acceder a la lista de adyacencia.
     */
    public Set<V> obtainAdjacents(V v) {
            if (adjacencyList.containsKey(v)) {
                return adjacencyList.get(v);
            } else {
                return null;
            }
    }

    /**
     * Comprueba si el grafo contiene un vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return {@code true} si el vértice existe en el grafo, {@code false} en caso contrario.
     */
    public boolean containsVertex(V v) {
        return adjacencyList.containsKey(v);
    }

    /**
     * Devuelve una representación en forma de cadena de la lista de adyacencia del grafo.
     *
     * @return una cadena que representa los vértices y sus conexiones.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<V> vertices = adjacencyList.keySet();
        for (V v : vertices) {
            sb.append(v).append(" -> ").append(adjacencyList.get(v)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene el camino más corto entre los vértices {@code v1} y {@code v2}, si existe.
     * Utiliza una búsqueda en anchura (BFS) para encontrar el camino.
     * <p>
     * - Se utiliza una cola para el orden de visita.<br>
     * - Se lleva un mapa de predecesores para reconstruir el camino.<br>
     * - Se usa un conjunto para registrar los vértices visitados.<br>
     * - Si se alcanza el vértice destino, se reconstruye el camino desde el final hasta el inicio y se invierte.<br>
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return una lista con la secuencia de vértices del camino más corto, o {@code null} si no existe dicho camino.
     */
    public List<V> shortestPath(V v1, V v2) {
        Queue<V> cola = new ArrayDeque<>();
        HashMap<V, V> anteriores = new HashMap<>();
        HashSet<V> visitados = new HashSet<>();

        // Añadimos el primer vértice a la cola y al conjunto de visitados
        cola.add(v1);
        visitados.add(v1);

        // Mientras la cola no esté vacía
        while (!cola.isEmpty()) {
            V actual = cola.poll();

            // Si el vértice actual es el destino, terminamos
            if (actual == v2) {
                break;
            } else {
                // Por cada vértice adyacente al actual
                for (V vertice : adjacencyList.get(actual)) {
                    // Si no ha sido visitado, se marca, se enlaza con su anterior y se encola
                    if (!visitados.contains(vertice)) {
                        visitados.add(vertice);
                        anteriores.put(vertice, actual);
                        cola.add(vertice);
                    }
                }
            }
        }

        // Si no se ha alcanzado el destino, retornamos null
        if (!visitados.contains(v2) && !v1.equals(v2)) {
            return null;
        }

        // Reconstrucción del camino desde el destino al origen
        List<V> camino = new ArrayList<>();
        V vertice = v2;
        while (vertice != null) {
            camino.add(vertice);
            vertice = anteriores.get(vertice);
        }

        // Si el camino está vacío, retornamos null
        if (camino.isEmpty()) {
            return null;
        }

        // Invertimos el camino para que vaya de v1 a v2
        Collections.reverse(camino);
        return camino;
    }
}

