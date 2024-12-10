package negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Solver {
    public static Set<Vertice> encontrarClique(GrafoListaVecinos grafo, Comparator<Vertice> comparator) {
        List<Vertice> verticesOrdenados = new ArrayList<>(grafo.getVertices());
        verticesOrdenados.sort(comparator);

        Set<Vertice> clique = new HashSet<>();
        for (Vertice v : verticesOrdenados) {
            boolean esClique = true;
            for (Vertice u : clique) {
                if (!grafo.existeArista(v, u)) {
                    esClique = false;
                    break;
                }
            }
            if (esClique) {
                clique.add(v);
            }
        }
        return clique;
    }
}


