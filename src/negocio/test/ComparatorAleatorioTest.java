package negocio.test;

import static org.junit.Assert.*;
import org.junit.Test;

import negocio.ComparatorAleatorio;
import negocio.Vertice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorAleatorioTest {

    @Test
    public void testComparatorAleatorio() {
        ComparatorAleatorio comparator = new ComparatorAleatorio();

        List<Vertice> vertices = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            vertices.add(new Vertice(i, i * 10.0));
        }

        // Ordenar lista
        try {
            Collections.sort(vertices, comparator);
        } catch (Exception e) {
            fail("ComparatorAleatorio lanzó una excepción: " + e.getMessage());
        }

        assertTrue("ComparatorAleatorio funciona sin lanzar excepciones", true);
    }

    @Test
    public void testComparatorAleatorioConsistencia() {
        ComparatorAleatorio comparator = new ComparatorAleatorio();

        //Vértices de ejemplo
        Vertice vertice1 = new Vertice(1, 10);
        Vertice vertice2 = new Vertice(2, 20);

        // verificar consistencia
        boolean isConsistent = true;
        for (int i = 0; i < 1000; i++) {
            int result1 = comparator.compare(vertice1, vertice2);
            int result2 = comparator.compare(vertice1, vertice2);
            if (result1 != result2) {
                isConsistent = false;
                break;
            }
        }
        assertFalse("ComparatorAleatorio no es consistente en múltiples comparaciones", isConsistent);
    }
    
    @Test
    public void testComparatorAleatorioRange() {
        ComparatorAleatorio comparator = new ComparatorAleatorio();

        //vértices de ejemplo
        Vertice vertice1 = new Vertice(1, 10);
        Vertice vertice2 = new Vertice(2, 20);

        // Verifica que el comparator devuelve valores dentro del rango(-1, 0, 1)
        for (int i = 0; i < 1000; i++) {
            int result = comparator.compare(vertice1, vertice2);
            assertTrue("ComparatorAleatorio devuelve un valor fuera del rango esperado", result >= -1 && result <= 1);
        }
    }
}