package negocio;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControladoraGrafica {
	private GrafoListaVecinos grafo;

    public ControladoraGrafica() {
    	String rutaArchivo = "grafo.json";
        this.grafo = GrafoLoader.cargarDesdeArchivo(rutaArchivo);
        if (this.grafo == null) {
            throw new IllegalArgumentException("No se pudo cargar el grafo desde el archivo.");
        }
    }
    //los ... nos deja pasar un numero variable de argumentos
	@SafeVarargs
	public static Set<Vertice> encontrarMejorClique(Set<Vertice>... cliques) {
        Set<Vertice> mejorClique = new HashSet<>();
        for (Set<Vertice> clique : cliques) {
            if (clique.size() > mejorClique.size()) {
                mejorClique = clique;
            }
        }
        return mejorClique;
    }
   	
   	
    public Set<Vertice> encontrarCliquePorPeso() {
        return Solver.encontrarClique(grafo, new ComparatorPorPeso());
    }

    public Set<Vertice> encontrarCliquePorPromedio() {
        return Solver.encontrarClique(grafo, new ComparatorPorPromedio(grafo));
    }
    
    public Set<Vertice> encontrarCliqueAleatoria() {
        return Solver.encontrarClique(grafo, new ComparatorAleatorio());
    }
    
	public List<Vertice> getVertices() {
		return grafo.getVertices();
	}

	public Set<Vertice> vecinos(Vertice v) {
		return grafo.vecinos(v);
	}
}