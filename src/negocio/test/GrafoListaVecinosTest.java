package negocio.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import negocio.GrafoListaVecinos;
import negocio.Vertice;

public class GrafoListaVecinosTest {
	private GrafoListaVecinos grafo ;
	private List<Vertice> vertices ;
	
	@Before
	public void setUp() {
		vertices = new ArrayList<Vertice>();
		vertices.add(new Vertice(1,1.0));
		vertices.add(new Vertice(2,2.0));
		vertices.add(new Vertice(3,2.0));
		grafo = new GrafoListaVecinos (vertices.size(),vertices);
	}
	
	
	@Test (expected = IllegalArgumentException.class)
	public void testVerticesNull() {
		new GrafoListaVecinos(vertices.size(),null);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testNumVerticesIncorrecto() {
		new GrafoListaVecinos(5,vertices);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarVeticeNull() {
		grafo.agregarArista(vertices.get(0), null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAgregarVeticeIgual() {
		grafo.agregarArista(vertices.get(0), vertices.get(0));
	}
	@Test
	public void testAgregarVertice() {
		grafo.agregarArista(vertices.get(0), vertices.get(1));
		assertTrue(grafo.existeArista(vertices.get(0), vertices.get(1)));
		assertTrue(grafo.existeArista(vertices.get(1), vertices.get(0)));
	}
	
	@Test
	public void testBorrarVertice() {
		grafo.agregarArista(vertices.get(0), vertices.get(1));
		grafo.borrarArista(vertices.get(0), vertices.get(1));
		assertFalse(grafo.existeArista(vertices.get(0), vertices.get(1)));
		assertFalse(grafo.existeArista(vertices.get(1), vertices.get(0)));
	}
	
	@Test
	public void testNeighbors() {
		grafo.agregarArista(vertices.get(0), vertices.get(1));
		grafo.agregarArista(vertices.get(0), vertices.get(2));
		Set<Vertice> vecinos = grafo.vecinos(vertices.get(0));
		assertTrue(vecinos.contains(vertices.get(1)));
		assertTrue(vecinos.contains(vertices.get(2)));
	}
	@Test (expected = IllegalArgumentException.class)
	public void testVerificarVerticeNull() {
		grafo.verificarVertice(null);
	}
	
	@Test
	public void testIgualTamanio() {
		assertEquals(3,grafo.size());
		
	}
}
