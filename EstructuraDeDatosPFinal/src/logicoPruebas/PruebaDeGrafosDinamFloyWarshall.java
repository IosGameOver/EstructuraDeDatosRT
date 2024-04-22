package logicoPruebas;

import logico.GrafoDinam;

public class PruebaDeGrafosDinamFloyWarshall {

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
          
       
        System.out.println("Grafo original:");
        System.out.println(g.mostrar());

        // Calcular los caminos más cortos entre todos los pares de vértices utilizando el algoritmo de Floyd-Warshall
        int[][] shortestPaths = g.floydWarshall();

        // Mostrar los caminos más cortos
        System.out.println("\nCaminos más cortos:");
        for (int i = 0; i < shortestPaths.length; i++) {
            for (int j = 0; j < shortestPaths[i].length; j++) {
                System.out.printf("Distancia más corta de %s a %s: %d\n", g.obtVertice(i), g.obtVertice(j), shortestPaths[i][j]);
            }
        }
    }
}
