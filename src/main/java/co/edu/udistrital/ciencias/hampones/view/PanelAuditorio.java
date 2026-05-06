package co.edu.udistrital.ciencias.hampones.view;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Componente gráfico encargado de visualizar los resultados de los algoritmos de ordenamiento.
 * 
 * <p>Esta clase extiende de {@link JPanel} y utiliza un {@link JTabbedPane} para organizar
 * los resultados de diferentes algoritmos en pestañas independientes. Cada pestaña representa
 * un "Auditorio" donde los hampones se ubican en una matriz de sillas y filas.</p>
 * 
 */
public class PanelAuditorio extends JPanel {

    private JTabbedPane tabs;   /** Panel de pestañas*/
    
    /**
     * Constructor del panel.
     * <p>Establece un diseño de borde, añade un título al componente e inicializa
     * el contenedor de pestañas en el centro del panel.</p>
     */
    public PanelAuditorio() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Auditorio ordenado"));
        tabs = new JTabbedPane();
        add(tabs, BorderLayout.CENTER);
    }

    /**
     * Actualiza la interfaz gráfica con los resultados de los procesos de ordenamiento.
     * 
     * @param algoritmos Arreglo con los nombres de los algoritmos ejecutados.
     * @param resultados Matriz tridimensional que contiene las matrices (filas x columnas) de {@link Hampon} por cada algoritmo.
     * @param iteraciones Arreglo con la cantidad de comparaciones realizadas por cada algoritmo.
     * @param tiemposNs Arreglo con los tiempos de ejecución medidos en nanosegundos.
     */
    public void mostrar(String[] algoritmos, Hampon[][][] resultados, int[] iteraciones, long[] tiemposNs) {
        tabs.removeAll();
        for (int k = 0; k < algoritmos.length; k++) {
            tabs.addTab(algoritmos[k], crearTab(resultados[k], iteraciones[k], tiemposNs[k]));
        }
    }
    
    
    /**
     * Crea un panel individual (pestaña) para un algoritmo específico.
     * 
     * @param matriz La disposición final de los hampones en el auditorio.
     * @param iteraciones Número de iteraciones del algoritmo.
     * @param tiempoNs Tiempo de ejecución en nanosegundos.
     * @return Un objeto {@link JPanel} configurado con la tabla y métricas.
     */
    private JPanel crearTab(Hampon[][] matriz, int iteraciones, long tiempoNs) {
        
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        // Conversión de tiempo para facilitar la lectura del usuario
        String tiempoStr;

        if (tiempoNs < 1_000_000) {
            tiempoStr = String.format("%.2f µs", tiempoNs / 1_000.0);
        } else {
            tiempoStr = String.format("%.3f ms", tiempoNs / 1_000_000.0);
        }

        JLabel lblIter = new JLabel(
            "Iteraciones: " + iteraciones + "    |    Tiempo: " + tiempoStr,
            SwingConstants.CENTER
        );
        lblIter.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel.add(lblIter, BorderLayout.NORTH);

        int filas = matriz.length;
        int cols;
        if (filas > 0) {
            cols = matriz[0].length;
        } else {
            cols = 0;
        }
        
        // Definición de encabezados de columnas (Sillas)
        String[] encabezados = new String[cols];
        for (int j = 0; j < cols; j++) {
                encabezados[j] = "Silla " + (j + 1);
        }
        
        // Preparación de datos para la JTable para saltos de línea
        String[][] datos = new String[filas][cols];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                Hampon h = matriz[i][j];
                if (h != null) {
                    datos[i][j] = "<html><small>" 
                        + h.getNombre() 
                        + "<br>$" + h.getDinero() 
                        + " | " + h.getEdad() + " a</small></html>";
                } else {
                    datos[i][j] = "";
                }
            }
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, encabezados) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(36);                                    
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 8));      
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 10));
        tabla.getTableHeader().setReorderingAllowed(false);
        
        // Configuración de encabezados de filas (Fila 1, Fila 2, etc.)
        JTable filaHeader = new JTable(new DefaultTableModel(
            generarEncabezadosFilas(filas), new String[]{""}
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        filaHeader.setRowHeight(36);
        filaHeader.setFont(new Font("SansSerif", Font.BOLD, 10));
        filaHeader.setPreferredScrollableViewportSize(new Dimension(50, 0));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setRowHeaderView(filaHeader);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    
    
    /**
     * Genera una matriz de etiquetas para identificar cada fila de la tabla.
     * 
     * @param filas Cantidad de filas a identificar.
     * @return Una matriz bidimensional de Strings con las etiquetas "Fila X".
     */
    private String[][] generarEncabezadosFilas(int filas) {
        String[][] data = new String[filas][1];
        for (int i = 0; i < filas; i++){
            data[i][0] = "Fila " + (i + 1);    
        }
        return data;
    }
}