package negocio;

import java.util.Comparator;
import java.util.Random;

public class ComparatorAleatorio implements Comparator<Vertice> {
    private Random random;

    public ComparatorAleatorio() {
        this.random = new Random();
    }

    @Override
    public int compare(Vertice v1, Vertice v2) {
        return random.nextInt(2) - 1; // Devuelve -1 o 0 o 1
    }
}