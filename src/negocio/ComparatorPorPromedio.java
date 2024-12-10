package negocio;

import java.util.Comparator;

public class ComparatorPorPromedio implements Comparator<Vertice> {
    private GrafoListaVecinos grafo;

    public ComparatorPorPromedio(GrafoListaVecinos grafo) {
        this.grafo = grafo;
    }

    @Override
    public int compare(Vertice v1, Vertice v2) {
        double promedio1 = calcularPromedio(v1);
        double promedio2 = calcularPromedio(v2);
        return Double.compare(promedio2, promedio1); // Orden descendente
    }

    private double calcularPromedio(Vertice vertice) {
        double peso = vertice.getPeso();
        int cantidadVecinos = grafo.vecinos(vertice).size();
        return (peso + cantidadVecinos) / 2.0;
    }
}