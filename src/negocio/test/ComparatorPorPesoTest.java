package negocio.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import negocio.ComparatorPorPeso;
import negocio.Vertice;

public class ComparatorPorPesoTest {
	ComparatorPorPeso comp;
	private Vertice v1;
    private Vertice v2;
    private Vertice v3;

	@Before
	public void setUp() {
		comp = new ComparatorPorPeso();
		  v1 = new Vertice(1,1.0); 
	      v2 = new Vertice(2,2.0); 
	      v3 = new Vertice(3,1.0); 
	}
	
	@Test
	public void comparador() {
        assertEquals(-1, comp.compare(v2, v1));

        assertEquals(1, comp.compare(v1, v2));
     
        assertEquals(0, comp.compare(v1, v3));
    }
	

}
