package logico;

public class Arista<T> {
    private Vertice<T> sucesor;
    private int costo; // costo/peso/distancia
    private int tiempo; // Representa el tiempo asociado con atravesar la arista
    
    public Arista(Vertice<T> sucesor, int costo, int tiempo) {
        this.sucesor = sucesor;
        this.costo = costo;
        this.tiempo = tiempo;
    }
    
    //Setters & getters
    
    public Vertice<T> getSucesor() {
        return sucesor;
    }
    
    public void setSucesor(Vertice<T> sucesor) {
        this.sucesor = sucesor;
    }
    
    public int getCosto() {
        return costo;
    }
    
    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    public int getTiempo() {
        return tiempo;
    }
    
    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
    
    // Método para obtener el vértice asociado
    public Vertice<T> getVertice() {
        return sucesor;
    }
}