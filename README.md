# Clique-Maxima-de-grafo-con-Heuristicas
## VIDEO
[![Video del Proyecto](https://img.youtube.com/vi/21LM3EXHvR4/maxresdefault.jpg)](https://www.youtube.com/watch?v=21LM3EXHvR4)

## Descripción General
Este proyecto está desarrollado en **Java** y se centra en la implementación de estructuras de datos de **grafos** y algoritmos relacionados. Proporciona funcionalidades como:
- Cargar grafos desde archivos JSON.
- Encontrar cliques en grafos.
- Métodos utilitarios para la manipulación y análisis de grafos.

## Tecnologías Utilizadas
- **Java**: Lenguaje principal del proyecto.
- **Google Gson**: Biblioteca para serialización y deserialización de objetos JSON.
- **Jgraph**: Biblioteca de Java para la visualización y manipulación de grafos. Proporciona una amplia gama de funcionalidades para crear, editar y visualizar grafos de manera eficiente. JGraphX es parte de la suite de herramientas de JGraph, que es ampliamente utilizada en aplicaciones que requieren la representación gráfica de estructuras de datos complejas.
## Características de JGraphX

- **Visualización de Grafos**  
  Permite la representación gráfica de nodos y aristas.

- **Edición de Grafos**  
  Proporciona herramientas para agregar, eliminar y modificar nodos y aristas.

- **Layout Automático**  
  Incluye algoritmos para organizar automáticamente los grafos de manera legible.

- **Interactividad**  
  Soporta la interacción del usuario con el grafo, como arrastrar y soltar nodos.

- **Exportación**  
  Permite exportar los grafos a varios formatos, como imágenes o archivos XML.
  
## Conceptos Aplicados

### Estructuras de Datos
- **Grafo**: Representado por la clase `GrafoListaVecinos`, utilizando listas de adyacencia (`ArrayList<HashSet<Vertice>>`) para almacenar conexiones.
- **Vértice**: Representado por la clase `Vertice`, que incluye un identificador único y un peso asociado.

### Algoritmos
- **Carga de Grafo desde JSON**: Implementado en `GrafoLoader` utilizando Gson para leer y deserializar archivos JSON.
- **Búsqueda de Clique**: Implementado en `Solver` mediante ordenamiento de vértices y verificación de conexiones.

### Manejo de Excepciones
- **IOException**: Al leer el archivo JSON en `GrafoLoader`.
- **ClassCastException**: Al deserializar datos JSON.
- **IllegalArgumentException**: Validación en `GrafoListaVecinos`.

### Programación Orientada a Objetos (POO)
- **Encapsulamiento**: Clases como `Vertice` y `GrafoListaVecinos` manejan datos mediante métodos controlados.
- **Sobrescritura de Métodos**: Métodos como `toString`, `hashCode` y `equals` están sobrescritos en `Vertice`.

## Estructura del Proyecto


### **Clases Principales**
1. **src/negocio/GrafoLoader.java**: Clase para cargar un grafo desde un archivo JSON.
2. **src/negocio/GrafoListaVecinos.java**: Representa un grafo utilizando una lista de adyacencia.
3. **src/negocio/Vertice.java**: Modelo que representa un vértice en el grafo.
4. **src/negocio/Solver.java**: Implementa algoritmos para encontrar un clique en el grafo.

---

## **Pruebas**

### **1. ComparatorPorPesoTest**
Prueba la funcionalidad de `ComparatorPorPeso`, que compara vértices basándose en sus pesos.

#### Código:

@Test
public void comparador() {
    assertEquals(-1, comp.compare(v2, v1)); // v2 tiene más peso que v1.
    assertEquals(1, comp.compare(v1, v2)); // v1 tiene menos peso que v2.
    assertEquals(0, comp.compare(v1, v3)); // v1 y v3 tienen el mismo peso.
}
### **2. ComparatorPorPromedioTest**

Valida el comportamiento del comparador `ComparatorPorPromedio`, que ordena los vértices según el peso promedio de sus vecinos.

#### Código Ejemplo:


`@Test
public void comparador() {
    assertEquals(1, comp.compare(v1, v2));  // v1 tiene menor promedio de peso que v2.
    assertEquals(-1, comp.compare(v2, v1)); // v2 tiene mayor promedio que v1.
    assertEquals(0, comp.compare(v1, v3));  // v1 y v3 tienen promedios iguales.
}` 

----------

### **3. GrafoListaVecinosTest**

Prueba la implementación de la lista de adyacencia en el grafo.

#### Casos de Prueba:

-   **Aristas**: Verifica la correcta adición y eliminación de aristas.
-   **Vecinos**: Valida que los vecinos de un nodo se calculen correctamente.
-   **Validaciones**: Garantiza que se lancen excepciones al intentar añadir vértices nulos o inválidos.

#### Código Ejemplo:


`@Test
public void testAgregarVertice() {
    grafo.agregarArista(vertices.get(0), vertices.get(1));
    assertTrue(grafo.existeArista(vertices.get(0), vertices.get(1)));
    assertTrue(grafo.existeArista(vertices.get(1), vertices.get(0)));
}` 

----------

### **4. SolverTest**

Valida el algoritmo de búsqueda de cliques en el grafo, utilizando diferentes estrategias de comparación.

#### Casos de Prueba:

-   **Clique por peso**: Encuentra el conjunto de vértices conectados con el mayor peso total.
-   **Clique por promedio**: Encuentra un conjunto basado en el promedio de pesos de los vecinos.
-   **Clique aislado**: Detecta nodos sin conexiones significativas.

#### Código Ejemplo:

`@Test
public void cliquePorPeso() {
    Set<Vertice> cliquePorPeso = Solver.encontrarClique(grafo, new ComparatorPorPeso());
    assertEquals(esperadoPeso(), cliquePorPeso);
}` 

#### Métodos Auxiliares:

-   `esperadoPeso()`: Define el conjunto esperado de vértices para un grafo específico.
-   `grafoPromedio()`: Construye un grafo de prueba basado en pesos promedio.

----------

## Tecnologías Usadas

-   **Lenguaje**: Java 8+
-   **Framework de pruebas**: JUnit 4
-   **Estructuras de datos**: Listas y conjuntos
-   **Modelado de grafos**: Basado en listas de adyacencia

----------

## Cómo Ejecutar las Pruebas

1.  Clona este repositorio:
    
    bash
    
    Copiar código
    
    `git clone <repositorio-url>
    cd <nombre-proyecto>` 
    
2.  Configura el entorno de Java y asegúrate de tener instalado JUnit 4.
    
3.  Ejecuta las pruebas:
    
    bash
    
    Copiar código
    
    `./gradlew test` 
    
    O, si usas Maven:
    
    `mvn test`
