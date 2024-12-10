package negocio;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Estadisticas {
    private Map<String, Long> tiemposEjecucion;
    private Map<String, Integer> tamañosClique;
    
    public Estadisticas() {
        tiemposEjecucion = new HashMap<>();
        tamañosClique = new HashMap<>();
    }
    
    public void evaluarHeuristica(GrafoListaVecinos grafo, Comparator<Vertice> comparator, String nombreHeuristica) {
        long inicio = System.nanoTime();
        Set<Vertice> clique = Solver.encontrarClique(grafo, comparator);
        long fin = System.nanoTime();
        
        long tiempoEjecucion = fin - inicio;
        int tamañoClique = clique.size();
        
        tiemposEjecucion.put(nombreHeuristica, tiempoEjecucion);
        tamañosClique.put(nombreHeuristica, tamañoClique);
    }
    
    public void imprimirResultados() {
        System.out.println("Resultados de la evaluación de heurísticas:");
        for (String nombreHeuristica : tiemposEjecucion.keySet()) {
            long tiempo = tiemposEjecucion.get(nombreHeuristica);
            int tamaño = tamañosClique.get(nombreHeuristica);
            System.out.println("Heurística: " + nombreHeuristica);
            System.out.println("Tiempo de ejecución (nanosegundos): " + tiempo);
            System.out.println("Tamaño de la clique encontrada: " + tamaño);
            System.out.println();
        }
    }
    

}