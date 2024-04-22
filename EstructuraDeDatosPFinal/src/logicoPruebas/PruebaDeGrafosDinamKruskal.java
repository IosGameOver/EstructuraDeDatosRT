package logicoPruebas;

import logico.GrafoDinam;

public class PruebaDeGrafosDinamKruskal {

    public static void main(String[] args) {
        GrafoDinam<String> g = new GrafoDinam<>();

        g.insVertice("O"); //0
        g.insVertice("A"); //1
        g.insVertice("B"); //2
        g.insVertice("C"); //3
        g.insVertice("D"); //4
        g.insVertice("E"); //5
        g.insVertice("F"); //6
        g.insVertice("G"); //7

        g.insArista(0, 1, 8, 0); //O A 8
        g.insArista(0, 2, 7, 0); //O B 7
        g.insArista(0, 3, 10, 0); //O C 10
        g.insArista(1, 2, 5, 0); //A B 5
        g.insArista(1, 4, 9, 0); //A D 9
        g.insArista(2, 4, 10, 0); //B D 10
        g.insArista(2, 5, 9, 0); //B E 9
        g.insArista(2, 6, 10, 0); //B F 10
        g.insArista(2, 3, 4, 0); //B C 4
        g.insArista(3, 6, 9, 0); //C F 9
        g.insArista(4, 5, 4, 0); //D E 4
        g.insArista(4, 7, 8, 0); //D G 8
        g.insArista(5, 7, 6, 0); //E G 6
        g.insArista(5, 6, 5, 0); //E F 5
        g.insArista(6, 7, 7, 0); //F G 7

        System.out.println("Grafo Original:");
        System.out.println(g.mostrar());

        // Llamar al algoritmo de Kruskal
        GrafoDinam<String> mstKruskal = g.kruskalAlgoritmo();

        // Mostrar el árbol de expansión mínima generado por el algoritmo de Kruskal
        System.out.println("\nÁrbol de Expansión Mínima (algoritmo de Kruskal):");
        if (mstKruskal != null) {
            System.out.println(mstKruskal.mostrarParaKrustal());
        } else {
            System.out.println("No se encontró un árbol de expansión mínima.");
        }
    }
}
