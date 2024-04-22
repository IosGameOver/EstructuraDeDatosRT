package logico;

import java.util.*;

// Definici�n de la clase GrafoDinam implementando la interfaz Grafo
public class GrafoDinam<E> implements Grafo<E> {
    // Declaraci�n de la lista de v�rtices y la lista de aristas del �rbol de expansi�n m�nima
    private ArrayList<Vertice<E>> vertices = new ArrayList<>();
    private ArrayList<Arista<E>> arbolDeExpansionMinima = new ArrayList<>();

    // M�todo para insertar un v�rtice en el grafo
    @Override
    public void insVertice(E x) {
        vertices.add(new Vertice<>(x));
    }

    // M�todo para obtener el v�rtice en una posici�n espec�fica
    @Override
    public E obtVertice(int pos) {
        if (pos >= 0 && pos < vertices.size()) {
            return vertices.get(pos).getVertice();
        }
        return null;
    }

    // M�todo para editar el valor de un v�rtice en una posici�n espec�fica
    public void editarVertice(int indice, E nuevoValor) {
        if (indice >= 0 && indice < vertices.size()) {
            vertices.get(indice).setVertice(nuevoValor);
        } else {
            System.out.println("�ndice de v�rtice inv�lido.");
        }
    }

    // M�todo para eliminar un v�rtice en una posici�n espec�fica
    @Override
    public void eliminarVertice(int indice) {
        if (indice >= 0 && indice < vertices.size()) {
            vertices.remove(indice); // Eliminar el v�rtice en el �ndice especificado
            // Tambi�n eliminar cualquier arista conectada a este v�rtice
            for (Vertice<E> vertice : vertices) {
                List<Arista<E>> aristas = vertice.getAristas();
                aristas.removeIf(arista -> arista.getSucesor().equals(vertices.get(indice)));
            }
        } else {
            System.out.println("�ndice de v�rtice inv�lido.");
        }
    }

    // M�todo para insertar una arista entre dos v�rtices con su costo y tiempo
    @Override
    public void insArista(int vi, int vf, int costo, int tiempo) {
        if (vi >= 0 && vi < vertices.size() && vf >= 0 && vf < vertices.size()) {
            vertices.get(vi).addArista(new Arista<>(vertices.get(vf), costo, tiempo));
            vertices.get(vf).addArista(new Arista<>(vertices.get(vi), costo, tiempo)); // Para grafos no dirigidos
        }
    }

    // M�todo para obtener el costo de una arista entre dos v�rtices
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

    // M�todo para obtener el n�mero de v�rtices en el grafo
    @Override
    public int orden() {
        return vertices.size();
    }

    // M�todo para obtener los sucesores de un v�rtice en una posici�n espec�fica
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
    // M�todo para obtener la lista de v�rtices
    public ArrayList<Vertice<E>> getVertices() {
        return vertices;
    }

    // M�todo para mostrar el grafo original
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

    // Algoritmo de Floyd-Warshall para encontrar todos los caminos m�s cortos
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

        // Calcula los caminos m�s cortos usando el algoritmo de Floyd-Warshall
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

        // Calcula los caminos m�s cortos usando el algoritmo de Floyd-Warshall
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
        private Map<Vertice<E>, Vertice<E>> parent; // Mapa para almacenar el padre de cada v�rtice en el conjunto disjunto
        private Map<Vertice<E>, Integer> rank; // Mapa para almacenar el rango de cada v�rtice en el conjunto disjunto

        public DisjointSet(List<Vertice<E>> vertices) { // Constructor que recibe una lista de v�rtices
            parent = new HashMap<>(); // Inicializa el mapa de padres
            rank = new HashMap<>(); // Inicializa el mapa de rangos
            for (Vertice<E> v : vertices) { // Itera sobre la lista de v�rtices
                parent.put(v, v); // Establece a cada v�rtice como su propio padre inicialmente
                rank.put(v, 0); // Establece el rango de cada v�rtice como 0 inicialmente
            }
        }

        public Vertice<E> find(Vertice<E> v) { // M�todo para encontrar el representante del conjunto al que pertenece un v�rtice
            if (v != parent.get(v)) { // Si el v�rtice no es su propio padre
                parent.put(v, find(parent.get(v))); // Actualiza el padre del v�rtice recursivamente
            }
            return parent.get(v); // Devuelve el representante del conjunto
        }

        public void union(Vertice<E> u, Vertice<E> v) { // M�todo para unir dos conjuntos disjuntos
            Vertice<E> uRoot = find(u); // Encuentra el representante del conjunto que contiene al v�rtice u
            Vertice<E> vRoot = find(v); // Encuentra el representante del conjunto que contiene al v�rtice v
            if (uRoot == vRoot) { // Si los v�rtices ya est�n en el mismo conjunto
                return; // No hacer nada, agregar esta arista crear�a un ciclo
            }
            // Combina los conjuntos seg�n sus rangos para mantener el �rbol balanceado
            if (rank.get(uRoot) < rank.get(vRoot)) { // Si el rango del conjunto que contiene a u es menor que el del conjunto que contiene a v
                parent.put(uRoot, vRoot); // Hace que el representante del conjunto que contiene a u sea el mismo que el de v
            } else if (rank.get(uRoot) > rank.get(vRoot)) { // Si el rango del conjunto que contiene a u es mayor que el del conjunto que contiene a v
                parent.put(vRoot, uRoot); // Hace que el representante del conjunto que contiene a v sea el mismo que el de u
            } else { // Si los rangos son iguales
                parent.put(vRoot, uRoot); // Hace que el representante del conjunto que contiene a v sea el mismo que el de u
                rank.put(uRoot, rank.get(uRoot) + 1); // Incrementa el rango del conjunto que contiene a u
            }
        }

        // M�todo para encontrar el n�mero de componentes conectados en el conjunto disjunto
        public int connectedComponents() {
            Set<Vertice<E>> components = new HashSet<>(); // Conjunto para almacenar los representantes �nicos de los conjuntos
            for (Vertice<E> v : parent.keySet()) { // Itera sobre las claves (v�rtices) del mapa de padres
                components.add(find(v)); // Agrega el representante del conjunto al conjunto de componentes
            }
            return components.size(); // Devulve el tama�o del conjunto de componentes, que es igual al n�mero de conjuntos conectados
        }
    }


    public GrafoDinam<E> kruskalAlgoritmo() {
        // Inicializa la cola de prioridad
        PriorityQueue<Arista<E>> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getCosto));

        // Inicializa el conjunto disjunto para mantener el seguimiento de los componentes conectados
        DisjointSet ds = new DisjointSet(vertices);

        // Inicializa el grafo del �rbol de expansi�n m�nima
        GrafoDinam<E> mst = new GrafoDinam<>();

        int contadorAristas = 0; // Contador de aristas en el �rbol de expansi�n m�nima

        // Procesa las aristas hasta que el MST est� completo o se hayan examinado todas las aristas
        while (!pq.isEmpty() && contadorAristas < vertices.size() - 1) {
            Arista<E> arista = pq.poll();
            Vertice<E> desde = arista.getVertice();
            Vertice<E> hacia = arista.getSucesor();

            // Verifica si agregar la arista crea un ciclo en el MST
            if (!ds.find(desde).equals(ds.find(hacia))) {
                // Agrega v�rtices al MST si a�n no est�n presentes
                if (!mst.vertices.contains(desde.getVertice())) {
                    mst.insVertice(desde.getVertice());
                }
                if (!mst.vertices.contains(hacia.getVertice())) {
                    mst.insVertice(hacia.getVertice());
                }

                // Agrega la arista al MST
                mst.insArista(mst.vertices.indexOf(desde.getVertice()), mst.vertices.indexOf(hacia.getVertice()), arista.getCosto(), arista.getTiempo());

                // Une los conjuntos de v�rtices 'desde' y 'hacia'
                ds.union(desde, hacia);

                contadorAristas++; // Incrementa el contador de aristas
            }
        }

        // Verifica si el MST est� completo
        if (contadorAristas != vertices.size() - 1) {
            System.out.println("No se encontr� un �rbol de expansi�n m�nima.");
            return null;
        }

        return mst;
    }

    // M�todo para mostrar el grafo para el algoritmo de Kruskal
    public String mostrarParaKrustal() {
        StringBuilder mos = new StringBuilder();
        boolean originalGraphPrinted = false;
        boolean mstFound = !arbolDeExpansionMinima.isEmpty();

        // Imprime el grafo original si hay v�rtices
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

        // Imprime el mensaje para el �rbol de expansi�n m�nima solo si se encuentra
        if (mstFound) {
            if (originalGraphPrinted) {
                mos.append("\n");
            }
            mos.append("�rbol de Expansi�n M�nima (algoritmo de Kruskal):\n");
            // Imprime el MST
            for (Arista<E> arista : arbolDeExpansionMinima) {
                mos.append(arista.getVertice().getVertice()).append(" ").append(arista.getSucesor().getVertice()).append(" ").append(arista.getCosto()).append("\n");
            }
        } else if (!originalGraphPrinted) {
            // Imprimir mensaje indicando que no se encontr� un �rbol de expansi�n m�nima solo si el grafo original no se imprime
            mos.append("No se encontr� un �rbol de expansi�n m�nima.\n");
        }

        return mos.toString();
    }


 // M�todo para mostrar solo los v�rtices
   
    public String mostrarVerticesOnly() {
        StringBuilder mos = new StringBuilder();
        // Itera a trav�s de los v�rtices en la lista de v�rtices
        for (Vertice<E> vertice : vertices) {
            // Agrega el v�rtice actual seguido de un espacio a la cadena de salida
            mos.append(vertice.getVertice()).append(" ");
        }
        // Agregar un salto de l�nea al final de la cadena de salida
        mos.append("\n");
        // Devuelve la cadena de salida como una representaci�n de los v�rtices del grafo
        return mos.toString();
    }

 

    // Algoritmo de Prim para encontrar el �rbol de Expansi�n M�nima
    public GrafoDinam<E> primAlgoritmo(int initialVertexIndex) {
    	// Conjunto para almacenar los v�rtices visitados durante el proceso
    	Set<Vertice<E>> visitado = new HashSet<>();

    	// Cola de prioridad para almacenar las aristas en orden de prioridad basado en el costo
    	PriorityQueue<Arista<E>> pq = new PriorityQueue<>(Comparator.comparingInt(Arista::getCosto));
    	GrafoDinam<E> mst = new GrafoDinam<>(); // Crear un nuevo grafo para el �rbol de Expansi�n M�nima

    	Vertice<E> verticeInicial = vertices.get(initialVertexIndex); // Obtener el v�rtice inicial del �ndice dado
    	visitado.add(verticeInicial); // Agregar el v�rtice inicial al conjunto de v�rtices visitados
    	pq.addAll(verticeInicial.getAristas()); // Agregar todas las aristas del v�rtice inicial a la cola de prioridad

    	System.out.println("�rbol de Expansi�n M�nima (Algoritmo de Prim):");
    	System.out.println("Agregando v�rtice inicial: " + verticeInicial.getVertice());

    	while (!pq.isEmpty() && visitado.size() < vertices.size()) { // Mientras la cola de prioridad no est� vac�a y no se hayan visitado todos los v�rtices
    		Arista<E> arista = pq.poll(); // Obtiene y eliminar la arista con el menor costo de la cola de prioridad
    	    Vertice<E> proximoVertice = arista.getSucesor(); // Obtiene el v�rtice sucesor de la arista

    	    if (!visitado.contains(proximoVertice)) { // Verifica si el v�rtice sucesor no ha sido visitado
    	        visitado.add(proximoVertice); // Agrega el v�rtice sucesor al conjunto de v�rtices visitados
    	        mst.insVertice(proximoVertice.getVertice()); // Agrega el pr�ximo v�rtice al �rbol de Expansi�n M�nima

    	        // Obtiene los �ndices de los v�rtices en el �rbol de Expansi�n M�nima
    	        int origen = mst.vertices.indexOf(arista.getVertice());
    	        int destino = mst.vertices.indexOf(proximoVertice);

    	        // Agrega la arista al �rbol de Expansi�n M�nima usando los �ndices obtenidos
    	        mst.insArista(origen, destino, arista.getCosto(), arista.getTiempo());

    	        System.out.println("Agregando arista desde " + arista.getVertice().getVertice() + " hasta " + proximoVertice.getVertice() +
    	                " con costo " + arista.getCosto() + " al �rbol de Expansi�n M�nima.");

    	        pq.addAll(proximoVertice.getAristas()); // Agrega todas las aristas del v�rtice sucesor a la cola de prioridad
    	    }
    	}
    	return mst; // Devuelve el grafo resultante del �rbol de Expansi�n M�nima
    }
}
