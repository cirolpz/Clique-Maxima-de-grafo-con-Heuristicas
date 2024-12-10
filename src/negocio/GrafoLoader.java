package negocio;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



public class GrafoLoader {

    public static GrafoListaVecinos cargarDesdeArchivo(String rutaArchivo) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(rutaArchivo)) {
            Type tipoGrafo = new TypeToken<Map<String, Object>>() {}.getType();
            Map<String, Object> datos = gson.fromJson(reader, tipoGrafo);

            List<Vertice> vertices = new ArrayList<>();
            Map<String, Double> verticesMap = gson.fromJson(gson.toJson(datos.get("vertices")), new TypeToken<Map<String, Double>>() {}.getType());
            for (Map.Entry<String, Double> entry : verticesMap.entrySet()) {
                vertices.add(new Vertice(Integer.parseInt(entry.getKey()), entry.getValue()));
            }

            GrafoListaVecinos grafo = new GrafoListaVecinos(vertices.size(), vertices);

            // Leer arcos
            List<List<Integer>> arcos = gson.fromJson(gson.toJson(datos.get("arcos")), new TypeToken<List<List<Integer>>>() {}.getType());
            for (List<Integer> arco : arcos) {
                grafo.agregarArista(vertices.get(arco.get(0) - 1), vertices.get(arco.get(1) - 1));
            }
            return grafo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Error de formato en el archivo JSON.");
            e.printStackTrace();
        }
        return null;
    }
}