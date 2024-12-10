package negocio.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import negocio.ComparatorPorPeso;
import negocio.ComparatorPorPromedio;
import negocio.GrafoListaVecinos;
import negocio.Solver;
import negocio.Vertice;

public class SolverTest {

    @Test
    public void cliquePorPeso() {
        GrafoListaVecinos grafo = grafoPromedio();
        Set<Vertice> cliquePorPeso = Solver.encontrarClique(grafo, new ComparatorPorPeso());
        
        Set<Vertice> esperado = esperadoPeso();
        assertEquals(esperado, cliquePorPeso);
    }
    
    @Test
    public void cliquePorPromedio() {
        GrafoListaVecinos grafo = grafoPromedio();
        Set<Vertice> cliquePorPromedio = Solver.encontrarClique(grafo, new ComparatorPorPromedio(grafo));
        
        Set<Vertice> esperado = esperadoPromedio();
        assertEquals(esperado, cliquePorPromedio);
    }
    
    @Test
    public void cliqueAislada() {
        GrafoListaVecinos grafo = grafoAislado();
        Set<Vertice> cliquePeso = Solver.encontrarClique(grafo, new ComparatorPorPeso());
        
        Set<Vertice> esperado = new HashSet<>();
        esperado.add(new Vertice(1, 6.0));
        
        assertEquals(esperado, cliquePeso);
    }

    private Set<Vertice> esperadoPeso() {
        Set<Vertice> cliqueEsperada = new HashSet<>();
        cliqueEsperada.add(new Vertice(1, 6.0));
        cliqueEsperada.add(new Vertice(2, 0.0));
        cliqueEsperada.add(new Vertice(4, 0.0));
        return cliqueEsperada;
    }
    
    private Set<Vertice> esperadoPromedio() {
        Set<Vertice> cliqueEsperada = new HashSet<>();
        cliqueEsperada.add(new Vertice(6, 6.0));
        cliqueEsperada.add(new Vertice(5, 3.0));
        cliqueEsperada.add(new Vertice(4, 0.0));
        cliqueEsperada.add(new Vertice(2, 0.0));
        return cliqueEsperada;
    }
    
    private GrafoListaVecinos grafoPromedio() {
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1, 6.0));
        vertices.add(new Vertice(2, 0.0));
        vertices.add(new Vertice(3, 1.0));
        vertices.add(new Vertice(4, 0.0));
        vertices.add(new Vertice(5, 3.0));
        vertices.add(new Vertice(6, 6.0));
        GrafoListaVecinos grafo = new GrafoListaVecinos(6, vertices);
        grafo.agregarArista(vertices.get(0), vertices.get(1));
        grafo.agregarArista(vertices.get(0), vertices.get(3));
        grafo.agregarArista(vertices.get(1), vertices.get(2));
        grafo.agregarArista(vertices.get(1), vertices.get(3));
        grafo.agregarArista(vertices.get(1), vertices.get(5));
        grafo.agregarArista(vertices.get(2), vertices.get(4));
        grafo.agregarArista(vertices.get(3), vertices.get(4));
        grafo.agregarArista(vertices.get(4), vertices.get(5));
        grafo.agregarArista(vertices.get(3), vertices.get(5));
        grafo.agregarArista(vertices.get(1), vertices.get(4));
        return grafo;
    }
    
    private GrafoListaVecinos grafoAislado() {
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1, 6.0));
        vertices.add(new Vertice(2, 0.0));
        vertices.add(new Vertice(3, 1.0));
        vertices.add(new Vertice(4, 0.0));
        vertices.add(new Vertice(5, 3.0));
        GrafoListaVecinos grafo = new GrafoListaVecinos(5, vertices);
        return grafo;
    }
}