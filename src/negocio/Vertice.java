package negocio;

import java.util.Objects;

public class Vertice {
	private int ID;
	private double peso;
	
	public Vertice(int ID, double peso) {
		this.ID = ID;
		this.peso = peso;
	}

	public int getID() {
		return ID;
	}

	public double getPeso() {
		return peso;
	}
	
	@Override
    public String toString() {
        return "Vertice{" +
                "ID=" + ID +
                ", peso=" + peso +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(ID, peso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertice other = (Vertice) obj;
		return ID == other.ID && Double.doubleToLongBits(peso) == Double.doubleToLongBits(other.peso);
	}
	
}