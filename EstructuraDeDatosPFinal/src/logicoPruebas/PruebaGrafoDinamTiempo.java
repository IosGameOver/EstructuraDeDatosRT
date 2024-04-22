package logicoPruebas;

import logico.GrafoDinam;

public class PruebaGrafoDinamTiempo {

    public static void main(String[] args) {
        GrafoDinam<String> g = new GrafoDinam<>();
        g.insVertice("A"); //0
	    g.insVertice("B"); //1
	    g.insVertice("C"); //2
	    g.insVertice("D"); //3
	    g.insVertice("E"); //4
	    

        g.insArista(0, 1, 3, 5); //A B 3
        g.insArista(0, 2, 1, 10); //A C 1
        g.insArista(1, 2, 7, 1); //B C 7
        g.insArista(2, 3, 2, 7); //C D 2
        g.insArista(3, 1, 5, 3); //D B 5
        g.insArista(4, 1, 1, 8); //E B 1
        g.insArista(4, 3, 7, 2); //E D 7
          
       
        System.out.println("Grafo original:");
        System.out.println(g.mostrar()); //Muestra el grafo en su forma de lista de Adyacencia.

        // Calcular los caminos más cortos basados en tiempo usando Floyd-Warshall
        int[][] tiemposMasCortos = g.floydWarshallTiempo();

        // Mostrar los tiempos más cortos
        System.out.println("\nTiempos más cortos (En minutos):\n");
        for (int i = 0; i < tiemposMasCortos.length; i++) {
            for (int j = 0; j < tiemposMasCortos[i].length; j++) {
                System.out.printf("El tiempo más corto de %s a %s: %d\n", g.obtVertice(i), g.obtVertice(j), tiemposMasCortos[i][j]);
            }
        }
    }
}
