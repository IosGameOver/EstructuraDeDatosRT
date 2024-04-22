package logico;

import java.util.*;

// Definición de la clase GrafoDinam implementando la interfaz Grafo
public class GrafoDinam<E> implements Grafo<E> {
    // Declaración de la lista de vértices y la lista de aristas del árbol de expansión mínima
    private ArrayList<Vertice<E>> vertices = new ArrayList<>();
    private ArrayList<Arista<E>> arbolDeExpansionMinima = new ArrayList<>();

    // Método para insertar un vértice en el grafo
    @Override
    public void insVertice(E x) {
        vertices.add(new Vertice<>(x));
    }

    // Método para obtener el vértice en una posición específica
    @Override
    public E obtVertice(int pos) {
        if (pos >= 0 && pos < vertices.size()) {
            return vertices.get(pos).getVertice();
        }
        return null;
    }

    // Método para editar el valor de un vértice en una posición específica
    public void editarVertice(int indice, E nuevoValor) {
        if (indice >= 0 && indice < vertices.size()) {
            vertices.get(indice).setVertice(nuevoValor);
        } else {
            System.out.println("Índice de vértice inválido.");
        }
    }

    // Método para eliminar un vértice en una posición específica
    @Override
    public void eliminarVertice(int indice) {
        if (indice >= 0 && indice < vertices.size()) {
            vertices.remove(indice); // Eliminar el vértice en el índice especificado
            // También eliminar cualquier arista conectada a este vértice
            for (Vertice<E> vertice : vertices) {
                List<Arista<E>> aristas = vertice.getAristas();
                aristas.removeIf(arista -> arista.getSucesor().equals(vertices.get(indice)));
            }
        } else {
            System.out.println("Índice de vértice inválido.");
        }
    }

    // Método para insertar una arista entre dos vértices con su costo y tiempo
    @Override
    public void insArista(int vi, int vf, int costo, int tiempo) {
        if (vi >= 0 && vi < vertices.size() && vf >= 0 && vf < vertices.size()) {
            vertices.get(vi).addArista(new Arista<>(vertices.get(vf), costo, tiempo));
            vertices.get(vf).addArista(new Arista<>(vertices.get(vi), costo, tiempo)); // Para grafos no dirigidos
        }
    }

    // Método para obtener el costo de una arista entre dos vértices
    @Override
    public int costoArista(int vi, int vf) {
        if (vi >= 0 && vi < vertices.size() && vf >= 0 && vf < vertices.size()) {
            Vertice<E> verti = vertices.get(vi);
            ArrayList<Arista<E>> aristas = verti.getAristas();
            for (Arista<E> aris : aristas) {
                if (aris.getSucesor().equals(vertices.get(vf))) {
                    return aris.getCosto();
                }
            }
        }
        return -1;
    }

    // Método para obtener el número de vértices en el grafo
    @Override
    public int orden() {
        return vertices.size();
    }

    // Método para obtener los sucesores de un vértice en una posición específica
    @Override
    public ArrayList<E> sucesores(int pos) {
        ArrayList<E> suce = new ArrayList<>();
        if (pos >= 0 && pos < vertices.size()) {
            Vertice<E> verti = vertices.get(pos);
            ArrayList<Arista<E>> aristas = verti.getAristas();
            for (Arista<E> aris : aristas) {
                suce.add(aris.getSucesor().getVertice());
            }
        }
        return suce;
    }
    // Método para obtener la lista de vértices
    public ArrayList<Vertice<E>> getVertices() {
        return vertices;
    }

    // Método para mostrar el grafo original
    @Override
    public String mostrar() {
        StringBuilder mos = new StringBuilder();
        for (Vertice<E> vertice : vertices) {
            mos.append(vertice.getVertice());
            ArrayList<Arista<E>> aristas = vertice.getAristas();
            for (Arista<E> arista : aristas) {
                mos.append(" ").append(arista.getSucesor().getVertice()).append(" ").append(arista.getCosto());
            }
            mos.append("\n");
        }
        return mos.toString();
    }

    // Algoritmo de Floyd-Warshall para encontrar todos los caminos más cortos
    public int[][] floydWarshall() {
        int n = vertices.size();
        int[][] dist = new int[n][n];

        // Inicializa la matriz de distancias
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
            for (Arista<E> arista : vertices.get(i).getAristas()) {
                int j = vertices.indexOf(arista.getSucesor());
                dist[i][j] = arista.getCosto();
            }
        }

        // Calcula los caminos más cortos usando el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        return dist;
    }

    // Algoritmo de Floyd-Warshall modificado para tomar en cuenta el tiempo en lugar del costo/distancia
    public int[][] floydWarshallTiempo() {
        int n = vertices.size();
        int[][] tiempos = new int[n][n];

        // Inicializa la matriz de tiempos
        for (int i = 0; i < n; i++) {
            Arrays.fill(tiempos[i], Integer.MAX_VALUE);
            tiempos[i][i] = 0;
            for (Arista<E> arista : vertices.get(i).getAristas()) {
                int j = vertices.indexOf(arista.getSucesor());
                tiempos[i][j] = arista.getTiempo(); // Almacenar tiempo en lugar de distancia
            }
        }

        // Calcula los caminos más cortos usando el algoritmo de Floyd-Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (tiempos[i][k] != Integer.MAX_VALUE && tiempos[k][j] != Integer.MAX_VALUE) {
                        tiempos[i][j] = Math.min(tiempos[i][j], tiempos[i][k] + tiempos[k][j]);
                    }
                }
            }
        }

        return tiempos;
    }

    // Clase para representar un conjunto disjunto para el Algoritmo de Kruskal
    class DisjointSet { // Clase para representar un conjunto disjunto
        private Map<Vertice<E>, Vertice<E>> parent; // Mapa para almacenar el padre de cada vértice en el conjunto disjunto
        private Map<Vertice<E>, Integer> rank; // Mapa para almacenar el rango de cada vértice en el conjunto disjunto

        public DisjointSet(List<Vertice<E>> vertices) { // Constructor que recibe una lista de vértices
            parent = new HashMap<>(); // Inicializa el mapa de padres
            rank = new HashMap<>(); // Inicializa el mapa de rangos
            for (Vertice<E> v : vertices) { // Itera sobre la lista de vértices
                parent.put(v, v); // Establece a cada vértice como su propio padre inicialmente
                rank.put(v, 0); // Establece el rango de cada vértice como 0 inicialmente
            }
        }

        public Vertice<E> find(Vertice<E> v) { // Método para encontrar el representante del conjunto al que pertenece un vértice
            if (v != parent.get(v)) { // Si el vértice no es su propio padre
                parent.put(v, find(parent.get(v))); // Actualiza el padre del vértice recursivamente
            }
            return parent.get(v); // Devuelve el representante del conjunto
        }

        public void union(Vertice<E> u, Vertice<E> v) { // Método para unir dos conjuntos disjuntos
            Vertice<E> uRoot = find(u); // Encuentra el representante del conjunto que contiene al vértice u
            Vertice<E> vRoot = find(v); // Encuentra el representante del conjunto que contiene al vértice v
            if (uRoot == vRoot) { // Si los vértices ya están en el mismo conjunto
                return; // No hacer nada, agregar esta arista crearía un ciclo
            }
            // Combina los conjuntos según sus rangos para mantener el árbol balanceado
            if (rank.get(uRoot) < rank.get(vRoot)) { // Si el rango del conjunto que contiene a u es menor que el del conjunto que contiene a v
                parent.put(uRoot, vRoot); // Hace que el representante del conjunto que contiene a u sea el mismo que el de v
            } else if (rank.get(uRoot) > rank.get(vRoot)) { // Si el rango del conjunto que contiene a u es mayor que el del conjunto que contiene a v
                parent.put(vRoot, uRoot); // Hace que el representante del conjunto que contiene a v sea el mismo que el de u
            } else { // Si los rangos son iguales
                parent.put(vRoot, uRoot); // Hace que el representante del conjunto que contiene a v sea el mismo que el de u
                rank.put(uRoot, rank.get(uRoot) + 1); // Incrementa el rango del conjunto que contiene a u
            }
        }

        // Método para encontrar el número de componentes conectados en el conjunto disjunto
        public int connectedComponents() {
            Set<Vertice<E>> components = new HashSet<>(); // Conjunto para almacenar los representantes únicos de los conjuntos
            for (Vertice<E> v : parent.keySet()) { // Itera sobre las claves (vértices) del mapa de padres
                components.add(find(v)); // Agrega el representante del conjunto al conjunto de componentes
            }
            return components.size(); // Devulve el tamaño del conjunto de componentes, que es igual al número de conjuntos conectados
        }
    }


    public GrafoDinam<E> kruskalAlgoritmo() {
        // Inicializa la cola de prioridad
        PriorityQueue<Arista<E>> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getCosto));

        // Inicializa el conjunto disjunto para mantener el seguimiento de los componentes conectados
        DisjointSet ds = new DisjointSet(vertices);

        // Inicializa el grafo del árbol de expansión mínima
        GrafoDinam<E> mst = new GrafoDinam<>();

        int contadorAristas = 0; // Contador de aristas en el árbol de expansión mínima

        // Procesa las aristas hasta que el MST esté completo o se hayan examinado todas las aristas
        while (!pq.isEmpty() && contadorAristas < vertices.size() - 1) {
            Arista<E> arista = pq.poll();
            Vertice<E> desde = arista.getVertice();
            Vertice<E> hacia = arista.getSucesor();

            // Verifica si agregar la arista crea un ciclo en el MST
            if (!ds.find(desde).equals(ds.find(hacia))) {
                // Agrega vértices al MST si aún no están presentes
                if (!mst.vertices.contains(desde.getVertice())) {
                    mst.insVertice(desde.getVertice());
                }
                if (!mst.vertices.contains(hacia.getVertice())) {
                    mst.insVertice(hacia.getVertice());
                }

                // Agrega la arista al MST
                mst.insArista(mst.vertices.indexOf(desde.getVertice()), mst.vertices.indexOf(hacia.getVertice()), arista.getCosto(), arista.getTiempo());

                // Une los conjuntos de vértices 'desde' y 'hacia'
                ds.union(desde, hacia);

                contadorAristas++; // Incrementa el contador de aristas
            }
        }

        // Verifica si el MST está completo
        if (contadorAristas != vertices.size() - 1) {
            System.out.println("No se encontró un árbol de expansión mínima.");
            return null;
        }

        return mst;
    }

    // Método para mostrar el grafo para el algoritmo de Kruskal
    public String mostrarParaKrustal() {
        StringBuilder mos = new StringBuilder();
        boolean originalGraphPrinted = false;
        boolean mstFound = !arbolDeExpansionMinima.isEmpty();

        // Imprime el grafo original si hay vértices
        if (!vertices.isEmpty()) {
            for (Vertice<E> vertice : vertices) {
                mos.append(vertice.getVertice());
                ArrayList<Arista<E>> aristas = vertice.getAristas();
                for (Arista<E> arista : aristas) {
                    mos.append(" ").append(arista.getSucesor().getVertice()).append(" ").append(arista.getCosto());
                }
                mos.append("\n");
            }
            originalGraphPrinted = true;
        }

        // Imprime el mensaje para el árbol de expansión mínima solo si se encuentra
        if (mstFound) {
            if (originalGraphPrinted) {
                mos.append("\n");
            }
            mos.append("Árbol de Expansión Mínima (algoritmo de Kruskal):\n");
            // Imprime el MST
            for (Arista<E> arista : arbolDeExpansionMinima) {
                mos.append(arista.getVertice().getVertice()).append(" ").append(arista.getSucesor().getVertice()).append(" ").append(arista.getCosto()).append("\n");
            }
        } else if (!originalGraphPrinted) {
            // Imprimir mensaje indicando que no se encontró un árbol de expansión mínima solo si el grafo original no se imprime
            mos.append("No se encontró un árbol de expansión mínima.\n");
        }

        return mos.toString();
    }


 // Método para mostrar solo los vértices
   
    public String mostrarVerticesOnly() {
        StringBuilder mos = new StringBuilder();
        // Itera a través de los vértices en la lista de vértices
        for (Vertice<E> vertice : vertices) {
            // Agrega el vértice actual seguido de un espacio a la cadena de salida
            mos.append(vertice.getVertice()).append(" ");
        }
        // Agregar un salto de línea al final de la cadena de salida
        mos.append("\n");
        // Devuelve la cadena de salida como una representación de los vértices del grafo
        return mos.toString();
    }

 

    // Algoritmo de Prim para encontrar el Árbol de Expansión Mínima
    public GrafoDinam<E> primAlgoritmo(int initialVertexIndex) {
    	// Conjunto para almacenar los vértices visitados durante el proceso
    	Set<Vertice<E>> visitado = new HashSet<>();

    	// Cola de prioridad para almacenar las aristas en orden de prioridad basado en el costo
    	PriorityQueue<Arista<E>> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getCosto));
    	GrafoDinam<E> mst = new GrafoDinam<>(); // Crear un nuevo grafo para el Árbol de Expansión Mínima

    	Vertice<E> verticeInicial = vertices.get(initialVertexIndex); // Obtener el vértice inicial del índice dado
    	visitado.add(verticeInicial); // Agregar el vértice inicial al conjunto de vértices visitados
    	pq.addAll(verticeInicial.getAristas()); // Agregar todas las aristas del vértice inicial a la cola de prioridad

    	System.out.println("Árbol de Expansión Mínima (Algoritmo de Prim):");
    	System.out.println("Agregando vértice inicial: " + verticeInicial.getVertice());

    	while (!pq.isEmpty() && visitado.size() < vertices.size()) { // Mientras la cola de prioridad no esté vacía y no se hayan visitado todos los vértices
    		Arista<E> arista = pq.poll(); // Obtiene y eliminar la arista con el menor costo de la cola de prioridad
    	    Vertice<E> proximoVertice = arista.getSucesor(); // Obtiene el vértice sucesor de la arista

    	    if (!visitado.contains(proximoVertice)) { // Verifica si el vértice sucesor no ha sido visitado
    	        visitado.add(proximoVertice); // Agrega el vértice sucesor al conjunto de vértices visitados
    	        mst.insVertice(proximoVertice.getVertice()); // Agrega el próximo vértice al Árbol de Expansión Mínima

    	        // Obtiene los índices de los vértices en el Árbol de Expansión Mínima
    	        int origen = mst.vertices.indexOf(arista.getVertice());
    	        int destino = mst.vertices.indexOf(proximoVertice);

    	        // Agrega la arista al Árbol de Expansión Mínima usando los índices obtenidos
    	        mst.insArista(origen, destino, arista.getCosto(), arista.getTiempo());

    	        System.out.println("Agregando arista desde " + arista.getVertice().getVertice() + " hasta " + proximoVertice.getVertice() +
    	                " con costo " + arista.getCosto() + " al Árbol de Expansión Mínima.");

    	        pq.addAll(proximoVertice.getAristas()); // Agrega todas las aristas del vértice sucesor a la cola de prioridad
    	    }
    	}
    	return mst; // Devuelve el grafo resultante del Árbol de Expansión Mínima
    }
}
