package logico;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Vertice<T> {
    private T vertice;
    private ArrayList<Arista<T>> aristas = new ArrayList<>(); // Colección de aristas
    private List<Vertice<T>> caminoMasCorto = new LinkedList<>();
    private Vertice<T> previo;
    private int costo = Integer.MAX_VALUE; // Initialize default costo

    public void addArista(Arista<T> arista) {
        aristas.add(arista);
    }

    public Vertice(T vertice) {
        this.vertice = vertice;
    }

    public T getVertice() {
        return vertice;
    }

    public void setVertice(T vertice) {
        this.vertice = vertice;
    }

    public ArrayList<Arista<T>> getAristas() {
        return aristas;
    }

    public List<Vertice<T>> getCaminoMasCorto() {
        return caminoMasCorto;
    }

    public void setCaminoMasCorto(List<Vertice<T>> caminoMasCorto) {
        this.caminoMasCorto = caminoMasCorto;
    }

    public Vertice<T> getPrevio() {
        return previo;
    }

    public void setPrevio(Vertice<T> previo) {
        this.previo = previo;
    }

    // Método para obtener los vértices
    public List<Vertice<T>> getVertices(GrafoDinam<T> grafo) {
        return grafo.getVertices();
    }

    public void dijkstra(Vertice<T> origen) {
        PriorityQueue<Vertice<T>> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(Vertice::getCosto));
        origen.setCosto(0);
        colaPrioridad.add(origen);

        while (!colaPrioridad.isEmpty()) {
            Vertice<T> actual = colaPrioridad.poll();

            for (Arista<T> arista : actual.getAristas()) {
                Vertice<T> proximoNodo = arista.getSucesor();
                int nuevoCosto = actual.getCosto() + arista.getCosto(); // Use arista.getCosto() instead of arista.getTiempo()

                // Actualizar si el nuevo costo es más corto
                if (nuevoCosto < proximoNodo.getCosto()) {
                    proximoNodo.setCosto(nuevoCosto);
                    proximoNodo.setPrevio(actual);
                    // Actualizar el camino más corto
                    proximoNodo.getCaminoMasCorto().clear(); // Limpiar el camino más corto anterior
                    proximoNodo.getCaminoMasCorto().addAll(actual.getCaminoMasCorto());
                    proximoNodo.getCaminoMasCorto().add(actual); // Agregar el vértice actual al camino más corto
                    // Agregar a la cola de prioridad para una exploración adicional
                    colaPrioridad.add(proximoNodo);
                }
            }
        }
    }

    // Método para obtener el costo del vértice
    public int getCosto() {
        return costo;
    }

    // Método para establecer el costo del vértice
    public void setCosto(int costo) {
        this.costo = costo;
    }
}