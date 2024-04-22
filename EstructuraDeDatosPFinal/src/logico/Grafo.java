package logico;

import java.util.ArrayList;

public interface Grafo<E> { //Interfaz a implmentar

	
	void insVertice(E x); // Método para insertar un vértice en el grafo.
	E obtVertice(int pos);// Método para obtener el vértice en la posición especificada.
	int costoArista(int vi, int vf);// Método para obtener el costo de la arista entre dos vértices dados.
	int orden();// Método para obtener el número de vértices en el grafo.
	ArrayList<E> sucesores(int pos);// Método para obtener los sucesores de un vértice en la posición especificada
	String mostrar();// Método para mostrar la representación del grafo.
	int inf=00;// Valor de referencia para la definición de infinito
	void eliminarVertice(int indice);// Método para eliminar un vértice del grafo.
	void editarVertice(int indice, E nuevoValor);// Método para editar el valor de un vértice en la posición especificada
	void insArista(int vi, int vf, int costo, int tiempo);// Método para insertar una arista con costo y tiempo entre dos vértices dados.
	
}
