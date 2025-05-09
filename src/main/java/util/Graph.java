package util;

import java.lang.reflect.Array;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.Queue;

public class Graph<V> {

    private HashSet<V> set = new HashSet<>();

    //Lista de adyacencia.
    private Map<V, Set<V>> adjacencyList = new HashMap<>();

    //Lista del grafo, que contiene vértices y listas de adyacencia
    private Map<V, Map<V, Set<V>>> grafo = new HashMap<>();

    /**
     * Añade el vértice `v` al grafo.
     *
     * @param v vértice a añadir.
     * @return `true` si no estaba anteriormente y `false` en caso contrario.
     */
    public boolean addVertex(V v) {
        if (grafo.containsKey(v)) {
            grafo.put(v, adjacencyList);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Añade un arco entre los vértices `v1` y `v2` al grafo. En caso de
     * que no exista alguno de los vértices, lo añade también.
     *
     * @param v1 el origen del arco.
     * @param v2 el destino del arco.
     * @return `true` si no existía el arco y `false` en caso contrario.
     */
    public boolean addEdge(V v1, V v2) {
        if (grafo.containsKey(v1) | grafo.containsKey(v2)) {
            if (!grafo.containsKey(v1)) {
                addVertex(v1);
                grafo.get(v2).put(v1, set);
                return true;
            } else if (!grafo.containsKey(v2)) {
                addVertex(v2);
                grafo.get(v1).put(v2, set);
                return true;
            }
        }
        if (grafo.containsKey(v1) && grafo.containsKey(v2)) {
            if (grafo.get(v1).containsKey(v2)) {
                return false;
            } else {
                return true;
            }
        } else {
            addVertex(v1);
            addVertex(v2);
            grafo.get(v1).put(v2, set);
            return true;
        }
    }

    /**
     * Obtiene el conjunto de vértices adyacentes a `v`.
     *
     * @param v vértice del que se obtienen los adyacentes.
     * @return conjunto de vértices adyacentes.
     */
    public Set<V> obtainAdjacents(V v) throws Exception {
        if (grafo.containsKey(v)) {
            return grafo.get(v).keySet();
        } else {
            return null;
        }
    }

    /**
     * Comprueba si el grafo contiene el vértice dado.
     *
     * @param v vértice para el que se realiza la comprobación.
     * @return `true` si `v` es un vértice del grafo.
     */
    public boolean containsVertex(V v) {
        if (grafo.containsKey(v)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método `toString()` reescrito para la clase `Grafo.java`.
     *
     * @return una cadena de caracteres con la lista de adyacencia.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<V> vertices = grafo.keySet();
        for (V v : vertices) {
            sb.append(grafo.get(v)).append(" -> ").append(grafo.get(v).keySet().toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Obtiene, en caso de que exista, el camino más corto entre
     * `v1` y `v2`. En caso contrario, devuelve `null`.
     *
     * @param v1 el vértice origen.
     * @param v2 el vértice destino.
     * @return lista con la secuencia de vértices del camino más corto
     * entre `v1` y `v2`
     **/
    public List<V> shortestPath(V v1, V v2) {
        //Creamos una cola para el orden
        //Creamos un mapa que relaciones a cada vértice con el anterior
        //Creamos un conjunto de los vértices visitados
        Queue<V> cola = new ArrayDeque<>();
        HashMap <V, V> anteriores = new HashMap<>();
        HashSet <V> visitados = new HashSet<>();

        //Añadimos el primer vértice tanto a la cola como a los visitados
        cola.add(v1);
        visitados.add(v1);

        //Si la cola no está vacía, pasamos el vértice v1, que está a la cabeza de la cola, como el actual
        while(!cola.isEmpty()) {
            V actual = cola.poll();
            //Si el vértice actual es el que buscamos, se rompe el bucle
            if (actual == v2) {
                break;
                //En caso de que el vértice no sea el que buscamos, para cada vértice obtenemos su lista de adyacencia. Si entre los vértices visitados no está alguno de los
                //vértices de la lista de adyacencia, lo añadimos; añadimos también el vértice con el actual a la relación con su anterior. El vértice anterior a vertice es actual. Añadimos
                //vertice a la cola.
            } else {
                for (V vertice : grafo.get(actual).keySet()) {
                    if (!visitados.contains(vertice)) {
                        visitados.add(vertice);
                        anteriores.put(vertice, actual);
                        cola.add(vertice);
                    }
                }
            }
        }
        //Si entre los vertices visitados no está v2 y v2 no es el mismo que v1, retornamos null, porque indica que no hay un camino entre v1 y v2.
        if (!visitados.contains(v2) && !v1.equals(v2)){
            return null;
        }

        //Creamos una lista que nos diga el camino que hemos seguido de v1 a v2.
        List <V> camino = new ArrayList<>();
        V vertice = v2;
        //Mientras que el vertice al que hacemos referencia no sea nulo, lo añadimos al camino y seguimos añadiendo los vértices anteriores, hasta llegar al original, del que no hay anterior.
        while (vertice != null){
            camino.add(v2);
            vertice=anteriores.get(vertice);
        }
        //Si el camino está vacío, es que no existe, por lo que retornamos null.
        if (camino.isEmpty()){
            return null;
        }
        //Retornamos el camino.
        return camino;
    }
}
