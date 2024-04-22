# EstructuraDeDatosRT
 Proyecto Final (ICC-211): Sistema de Gestión de Rutas

1. Titulo del proyecto.

	EstructuraDeDatosFinal.


2. Descripción del proyecto.

	Este proyecto utiliza el concepto de creación y manejo de grafos para gestionar sus rutas utilizando algoritmos ampliamente utilizados comoo: Dijkstra, Prim´s, Krustal y Floyd-Warshall.

3. Cómo funciona el proyecto.

	En un inicio se crea un grafo dinámicamente (haciendo referencia a que no es necesario declarar el tamañp de la matriz que crea el grafo y que este se gestiona principalmente a través de listas de adyacencia, en su forma más primitiva -es decir, sin emplementar los algoritmos previamene mencionados-)

Luego, cada algoritmo (en su respectiva prueba) se encarga de comparar:

Dijkstra, para calcular la ruta más corta entre dos ubicaciones 
específicas.

Prim y Kruskal para encontrar árboles de expansión mínima, 
que representen rutas óptimas para conectar todas las ubicaciones.

Y por último, Floyd-Warshall para encontrar el camino más corto entre todas las 
ubicaciones.

4. Limitaciones.
 
1) La implementación del algoriutmo de Kruskal falla en obtener el arbol de expansión mínima.
Para solucionar esto se implementaron varios debugs, dónde se concluyó que el algoritmo detecta correctamente los ciclos en el grafo, pero por alguna razón el algoritmo falla en encontrar el árbol de expansión mínma.
Esto puede ocurrir por tres razones de las cuales, 2 están desartadas:

Grafo no conexo: Si el grafo no está completamente conectado, es decir, si hay vértices que no están conectados a otros vértices o si el grafo está dividido en componentes desconectados, entonces no se puede encontrar un árbol de expansión mínima. Esto se debe a que un árbol de expansión mínima debe abarcar todos los vértices del grafo. *DESCARTADO*

Aristas de peso negativo: El algoritmo de Kruskal asume que todas las aristas del grafo tienen pesos no negativos. Si hay aristas con pesos negativos en el grafo, el algoritmo puede no producir un resultado correcto. Esto se debe a que el algoritmo de Kruskal puede elegir aristas con pesos negativos, lo que podría llevar a un resultado incorrecto en el árbol de expansión mínima. *DESCARTADO*

Implementación del algoritmo: También es posible que el problema esté en la implementación del algoritmo de Kruskal. Puede haber errores en la lógica del algoritmo que impiden que encuentre el árbol de expansión mínima correctamente. *POSIBLE RAZÓN*

Por lo que se concluyé que la implementación total de esté algoritmo ha sido fallida.


5. Cómo usar el proyecto.

Dentro del parquete llamado lógicoPrueba se encuentra una clase main que prueba cada algoritmo de manera individual, por lo tanto las instrucciones son las mismas para todas:

1. Crear un objeto de la clase GrafoDinam<E>, el cual contará con el acceso a todos los métodos que está dispones.

2. Crear n vertices (mejor conocido como nodos), con el método insVertice, el cual tomao como valor datos de la clase String, por lo que puede el nombre correspondiente a cada vertice/nodo está a disposición del usuario.

3.Crear/definir las aristas que interconectan lo vertices/nodos previamente creados, para esto se utiliza el método insArista, que toma como constructor 4 entradas de tipo ¨int¨, los cuales corresponden de la siguiente manera(Vertice/Nodo saliente, Vertice/Nodo que conecta, Costo/también conocido como Distance, tiempo).

4. Los implememtanciones para imprimir y calcular las rutas vienen por defecto en las clases de prueba.
