package logicoPruebas;

import logico.*;

import java.util.List;

public class PruebaDeGrafosDinamDijkstra {

    public static void main(String[] args) {
        GrafoDinam<String> g = new GrafoDinam<>();
        g.insVertice("A"); //0
	    g.insVertice("B"); //1
	    g.insVertice("C"); //2
	    g.insVertice("D"); //3
	    g.insVertice("E"); //4
	    

        g.insArista(0, 1, 3, 0); //A B 3
        g.insArista(0, 2, 1, 0); //A C 1
        g.insArista(1, 2, 7, 0); //B C 7
        g.insArista(2, 3, 2, 0); //C D 2
        g.insArista(3, 1, 5, 0); //D B 5
        g.insArista(4, 1, 1, 0); //E B 1
        g.insArista(4, 3, 7, 0); //E D 7
          
        
       

        // Inicializar el costo predeterminado para cada vértice
        for (Vertice<String> vertice : g.getVertices()) {
            vertice.setCosto(Integer.MAX_VALUE);
        }

        // Imprimir el grafo original
        System.out.println("Grafo original:");
        System.out.println(g.mostrar());
/*
        // Ejecutar el algoritmo de Dijkstra desde cada vértice
        for (int i = 0; i < g.orden(); i++) {
            Vertice<String> origen = g.getVertices().get(i);
            origen.dijkstra(origen);

            System.out.println("\nEjecutando el algoritmo de Dijkstra desde el vértice " + origen.getVertice() + ":");
            for (int j = 0; j < g.orden(); j++) {
                Vertice<String> destino = g.getVertices().get(j);
                if (destino != origen) { // Excluir el vértice de origen de la salida
                    List<Vertice<String>> shortestPath = destino.getCaminoMasCorto();
                    int shortestDistance = destino.getCosto();

                    System.out.println("Distancia más corta a " + destino.getVertice() + ": " + shortestDistance);
                    System.out.print("Camino más corto a " + destino.getVertice() + ": ");
                    for (Vertice<String> vertice : shortestPath) {
                        System.out.print(vertice.getVertice() + " ");
                    }
                    System.out.println();
                }
            }
        }
       */ 
        //De un vertice a otro.
        
      
        // Obtener los vértices correspondientes al origen y destino
        String origenVertice = "A";
        String destinoVertice = "D";

        Vertice<String> origen = null;
        Vertice<String> destino = null;

        for (Vertice<String> vertice : g.getVertices()) {
            if (vertice.getVertice().equals(origenVertice)) {
                origen = vertice;
            } else if (vertice.getVertice().equals(destinoVertice)) {
                destino = vertice;
            }
        }

        if (origen != null && destino != null) {
            // Ejecutar el algoritmo de Dijkstra desde el vértice de origen
            origen.dijkstra(origen);

            // Imprimir la ruta más corta desde el origen hasta el destino
            System.out.println("\nEjecutando el algoritmo de Dijkstra desde el vértice " + origen.getVertice() +
                    " hacia el vértice " + destino.getVertice() + ":");
            List<Vertice<String>> shortestPath = destino.getCaminoMasCorto();
            int shortestDistance = destino.getCosto();

            System.out.println("Distancia más corta a " + destino.getVertice() + ": " + shortestDistance);
            System.out.print("Camino más corto a " + destino.getVertice() + ": ");
            for (Vertice<String> vertice : shortestPath) {
                System.out.print(vertice.getVertice() + " ");
            }
            System.out.println();
        } else {
            System.out.println("El vértice de origen o destino no se encontró en el grafo.");
        }

    }
        
        
        
        
        
        
        
        
    }

