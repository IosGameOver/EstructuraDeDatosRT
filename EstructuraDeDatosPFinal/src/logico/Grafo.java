package logico;

import java.util.ArrayList;

public interface Grafo<E> { //Interfaz a implmentar

	
	void insVertice(E x); // M�todo para insertar un v�rtice en el grafo.
	E obtVertice(int pos);// M�todo para obtener el v�rtice en la posici�n especificada.
	int costoArista(int vi, int vf);// M�todo para obtener el costo de la arista entre dos v�rtices dados.
	int orden();// M�todo para obtener el n�mero de v�rtices en el grafo.
	ArrayList<E> sucesores(int pos);// M�todo para obtener los sucesores de un v�rtice en la posici�n especificada
	String mostrar();// M�todo para mostrar la representaci�n del grafo.
	int inf=00;// Valor de referencia para la definici�n de infinito
	void eliminarVertice(int indice);// M�todo para eliminar un v�rtice del grafo.
	void editarVertice(int indice, E nuevoValor);// M�todo para editar el valor de un v�rtice en la posici�n especificada
	void insArista(int vi, int vf, int costo, int tiempo);// M�todo para insertar una arista con costo y tiempo entre dos v�rtices dados.
	
}
