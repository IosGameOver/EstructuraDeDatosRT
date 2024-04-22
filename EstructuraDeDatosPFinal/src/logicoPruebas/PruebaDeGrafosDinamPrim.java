package logicoPruebas;


import logico.GrafoDinam;



public class PruebaDeGrafosDinamPrim {
    public static void main(String[] args) {
        GrafoDinam<String> g = new GrafoDinam<>();
        g.insVertice("A");
        g.insVertice("B");
        g.insVertice("C");
        g.insVertice("D");

        g.insArista(0, 1, 2, 0);
        g.insArista(0, 2, 10, 0);
        g.insArista(2, 3, 2, 0);
        g.insArista(3, 2, 2, 0);

        System.out.println("Grafo Original:");
        System.out.println(g.mostrar());

        // Llama al algoritmo de Prim sobre el grafo ¨g¨
        g.primAlgoritmo(0); // Muestra el  Minimum Spanning Tree/Arbol de expansión mínima (MST)
        					// desde el vertice inicial declarado, ¨0¨ - A.
        
        
        

          }
}





