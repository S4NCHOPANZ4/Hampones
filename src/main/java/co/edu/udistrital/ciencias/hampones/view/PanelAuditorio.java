package co.edu.udistrital.ciencias.hampones.view;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelAuditorio extends JPanel {

    private JTabbedPane tabs;

    public PanelAuditorio() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Auditorio ordenado"));
        tabs = new JTabbedPane();
        add(tabs, BorderLayout.CENTER);
    }


    public void mostrar(String[] algoritmos, Hampon[][][] resultados, int[] iteraciones, long[] tiemposNs) {
        tabs.removeAll();
        for (int k = 0; k < algoritmos.length; k++) {
            tabs.addTab(algoritmos[k], crearTab(resultados[k], iteraciones[k], tiemposNs[k]));
        }
    }

    private JPanel crearTab(Hampon[][] matriz, int iteraciones, long tiempoNs) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

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

        String[] encabezados = new String[cols];
        for (int j = 0; j < cols; j++) {
                encabezados[j] = "Silla " + (j + 1);
        }

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

    private String[][] generarEncabezadosFilas(int filas) {
        String[][] data = new String[filas][1];
        for (int i = 0; i < filas; i++){
            data[i][0] = "Fila " + (i + 1);    
        }
        return data;
    }
}