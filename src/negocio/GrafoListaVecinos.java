package negocio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GrafoListaVecinos {
	private ArrayList<HashSet<Vertice>> listaVecinos;
	List<Vertice> vertices;

	public GrafoListaVecinos(int numVertices, List<Vertice> nodos) {
		if (nodos == null) {
			throw new IllegalArgumentException("no hay una lista de nodos creada");
		}
		if (numVertices != nodos.size()) {
			throw new IllegalArgumentException("no hay las suficientes nodos para cada vertice del grafo");
		}
		listaVecinos = new ArrayList<HashSet<Vertice>>();
		for (int i = 0; i < numVertices; i++) {
			listaVecinos.add(new HashSet<Vertice>());
		}
		vertices = nodos;
	}

	public void agregarArista(Vertice i, Vertice j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistinto(i, j);

		if (!existeArista(i, j)) {
			listaVecinos.get(vertices.indexOf(i)).add(j);
			listaVecinos.get(vertices.indexOf(j)).add(i);
		}
	}

	public void borrarArista(Vertice i, Vertice j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistinto(i, j);
		listaVecinos.get(vertices.indexOf(i)).remove(j);
		listaVecinos.get(vertices.indexOf(j)).remove(i);
	}

	public boolean existeArista(Vertice i, Vertice j) {
		verificarVertice(i);
		verificarVertice(j);
		verificarDistinto(i, j);
		return listaVecinos.get(vertices.indexOf(i)).contains(j) && listaVecinos.get(vertices.indexOf(j)).contains(i);
	}

	public void verificarDistinto(Vertice i, Vertice j) {
		if (i.equals(j)) {
			throw new IllegalArgumentException("no se permiten loops; (" + i + ", " + j + ")");
		}
	}

	public void verificarVertice(Vertice i) {
		if (i == null) {
			throw new IllegalArgumentException("El vertice no puede ser null: " + i);
		}
	}

	public int size() {
		return listaVecinos.size();
	}

	public Vertice getVertice(int i) {
		return vertices.get(i);
	}

	public List<Vertice> getVertices() {
		return vertices;
	}
	
	public Set<Vertice> vecinos (Vertice i){
		verificarVertice(i);
		return listaVecinos.get(vertices.indexOf(i));
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Vertices:\n");
	    for (Vertice v : vertices) {
	        sb.append(v).append(": ").append(vecinos(v)).append("\n");
	    }
	    return sb.toString();
	}
}
