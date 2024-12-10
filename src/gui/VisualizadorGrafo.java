package gui;

import negocio.ControladoraGrafica;
import negocio.Vertice;

import javax.swing.*;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.border.CompoundBorder;

public class VisualizadorGrafo extends JFrame {

    private static final long serialVersionUID = 1L;
    private ControladoraGrafica controladora;
    private JComboBox<String> heuristicaComboBox;
    private mxGraph grafo;
    private Object contenedorGrafo;

    public VisualizadorGrafo() {
        controladora = new ControladoraGrafica();
        setTitle("Visualizador de Grafo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 725);
        getContentPane().setLayout(new BorderLayout());

        initControlPanel();

        // Inicializar el grafo completo
        grafo = new mxGraph();
        grafo.setCellsEditable(false);
        contenedorGrafo = grafo.getDefaultParent();

        // Crear un componente de gráfico de JGraphX
        mxGraphComponent graphComponent = new mxGraphComponent(grafo);
        graphComponent.getGraphControl().setBackground(new Color(221, 221, 221));
        graphComponent.setEnabled(false);
        getContentPane().add(graphComponent, BorderLayout.CENTER);

        // Dibujar el grafo completo al inicio
        dibujarGrafo(controladora.getVertices());
    }

    private void initControlPanel() {
        // Panel para los controles
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(new CompoundBorder());
        controlPanel.setBackground(new Color(255, 255, 255));
        controlPanel.setLayout(new FlowLayout());

        // Combo box para seleccionar la heurística
        heuristicaComboBox = new JComboBox<>();
        heuristicaComboBox.addItem("Clique máximo por peso");
        heuristicaComboBox.addItem("Clique máximo por promedio");
        heuristicaComboBox.addItem("Clique máximo Aleatoria");
        
        JLabel lblSeleccioneClique = new JLabel("Seleccione la heuristica deseada:");
        controlPanel.add(lblSeleccioneClique);
        controlPanel.add(heuristicaComboBox);

        // Botón para ejecutar la heurística
        JButton ejecutarButton = new JButton("Ejecutar");
        ejecutarButton.addActionListener(e -> ejecutarHeuristica());
        controlPanel.add(ejecutarButton);
        
        // Botón para mostrar el mejor resultado
        JButton mejorResultadoButton = new JButton("Mostrar Mejor Resultado");
        mejorResultadoButton.addActionListener(e -> mostrarMejorResultado());
        controlPanel.add(mejorResultadoButton);
        
        // Botón para mostrar el grafo completo
        JButton mostrarGrafoCompletoButton = new JButton("Mostrar Grafo Completo");
        mostrarGrafoCompletoButton.addActionListener(e -> mostrarGrafoCompleto());
        controlPanel.add(mostrarGrafoCompletoButton);

        getContentPane().add(controlPanel, BorderLayout.NORTH);
    }

    private void dibujarGrafo(Collection<Vertice> vertices) {
        grafo.getModel().beginUpdate();
        try {
            grafo.removeCells(grafo.getChildVertices(contenedorGrafo));
            Map<Vertice, Object> vertexMap = new HashMap<>();
            layoutVertices(vertices, vertexMap);
            layoutEdges(vertices, vertexMap);
        } finally {
            grafo.getModel().endUpdate();
        }
    }

    private void layoutVertices(Collection<Vertice> vertices, Map<Vertice, Object> vertexMap) {
        int numVertices = vertices.size();
        double centerX = 400;
        double centerY = 300;
        double radius = 200;
        double angleIncrement = 2 * Math.PI / numVertices;
        double currentAngle = 0;

        for (Vertice v : vertices) {
            double x = centerX + radius * Math.cos(currentAngle);
            double y = centerY + radius * Math.sin(currentAngle);

            // Generar un color aleatorio
            String color = String.format("#%06X", (int)(Math.random() * 0xFFFFFF));

            // Crear el estilo del vértice con el color de relleno
            String estilo = mxConstants.STYLE_SHAPE + "=" + mxConstants.SHAPE_ELLIPSE + ";" +
                            mxConstants.STYLE_FILLCOLOR + "=" + color + ";" +
                            mxConstants.STYLE_FONTCOLOR + "=white;";

            Object vertex = grafo.insertVertex(contenedorGrafo, null, v.getID(), x, y, 50, 50, estilo);
            vertexMap.put(v, vertex);

            // Agregar etiqueta con el peso encima del vértice
            grafo.insertVertex(vertex, null, String.valueOf(v.getPeso()), 25, 45, 0, 0,
                    mxConstants.STYLE_ALIGN + "=" + mxConstants.ALIGN_CENTER + ";" +
                    mxConstants.STYLE_VERTICAL_ALIGN + "=" + mxConstants.ALIGN_BOTTOM + ";" +
                    mxConstants.STYLE_FONTSIZE + "=10;" +
                    mxConstants.STYLE_FONTCOLOR + "=white");

            currentAngle += angleIncrement;
        }
    }

    private void layoutEdges(Collection<Vertice> vertices, Map<Vertice, Object> vertexMap) {
        for (Vertice v : vertices) {
            for (Vertice vecino : controladora.vecinos(v)) {
                String estilo = mxConstants.STYLE_ENDARROW + "=" + mxConstants.NONE; // Estilo para remover las flechas
                grafo.insertEdge(contenedorGrafo, null, "", vertexMap.get(v), vertexMap.get(vecino), estilo);
            }
        }
    }

    private void ejecutarHeuristica() {
        String seleccion = (String) heuristicaComboBox.getSelectedItem();
        Set<Vertice> clique;

        if ("Clique máximo por peso".equals(seleccion)) {
            clique = controladora.encontrarCliquePorPeso();
        } else if ("Clique máximo por promedio".equals(seleccion)) {
            clique = controladora.encontrarCliquePorPromedio();
        } else {
            clique = controladora.encontrarCliqueAleatoria();
        }

        dibujarGrafo(clique);
    }
    @SuppressWarnings("static-access")
	private void mostrarMejorResultado() {
        Set<Vertice> cliquePorPeso = controladora.encontrarCliquePorPeso();
        Set<Vertice> cliquePorPromedio = controladora.encontrarCliquePorPromedio();
        Set<Vertice> cliqueAleatoria = controladora.encontrarCliqueAleatoria();

        Set<Vertice> mejorClique = controladora.encontrarMejorClique(cliquePorPeso, cliquePorPromedio, cliqueAleatoria);

        dibujarGrafo(mejorClique);
    }

   
    private void mostrarGrafoCompleto() {
        dibujarGrafo(controladora.getVertices());
    }

    public void mostrar() {
        setVisible(true);
    }
}
