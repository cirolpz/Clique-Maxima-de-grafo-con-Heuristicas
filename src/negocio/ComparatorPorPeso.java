package negocio;

import java.util.Comparator;

public class ComparatorPorPeso implements Comparator<Vertice> {
    @Override
    public int compare(Vertice v1, Vertice v2) {
        return Double.compare(v2.getPeso(), v1.getPeso()); // Orden descendente
    }
}