package negocio.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import negocio.ComparatorPorPromedio;
import negocio.GrafoListaVecinos;
import negocio.Vertice;

import java.util.ArrayList;
import java.util.List;

public class ComparatorPorPromedioTest {
    private ComparatorPorPromedio comp;
    private Vertice v1;
    private Vertice v2;
    private Vertice v3;

    @Before
    public void setUp() {
        List<Vertice> vertices = new ArrayList<>();
        vertices.add(new Vertice(1, 1.0));
        vertices.add(new Vertice(2, 2.0));
        vertices.add(new Vertice(3, 1.0));

        GrafoListaVecinos grafo = new GrafoListaVecinos(vertices.size(), vertices);
        grafo.agregarArista(vertices.get(0), vertices.get(1));
        grafo.agregarArista(vertices.get(1), vertices.get(2));
        
        comp = new ComparatorPorPromedio(grafo);
        v1 = vertices.get(0);
        v2 = vertices.get(1);
        v3 = vertices.get(2);
    }

    @Test
    public void comparador() {
        assertEquals(1, comp.compare(v1, v2));
        assertEquals(-1, comp.compare(v2, v1));
        assertEquals(0, comp.compare(v1, v3));
    }
}