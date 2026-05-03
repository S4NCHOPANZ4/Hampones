package co.ciencias.hampones.view;

import co.ciencias.hampones.model.Hampon;
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

    public void mostrar(String[] algoritmos, Hampon[][][] resultados, int[] iteraciones) {
        tabs.removeAll();
        for (int k = 0; k < algoritmos.length; k++) {
            tabs.addTab(algoritmos[k], crearTab(resultados[k], iteraciones[k]));
        }
    }

    private JPanel crearTab(Hampon[][] matriz, int iteraciones) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));

        // Etiqueta de iteraciones arriba
        JLabel lblIter = new JLabel("Iteraciones: " + iteraciones, SwingConstants.CENTER);
        lblIter.setFont(new Font("SansSerif", Font.BOLD, 13));
        panel.add(lblIter, BorderLayout.NORTH);

        // Construir tabla
        int filas = matriz.length;
        int cols = matriz[0].length;

        String[] encabezados = new String[cols];
        for (int j = 0; j < cols; j++) encabezados[j] = "Silla " + (j + 1);

        String[][] datos = new String[filas][cols];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                Hampon h = matriz[i][j];
                if (h != null) {
                    datos[i][j] = "<html>" + h.getNombre() + "<br>$" + h.getDinero() + " | " + h.getEdad() + " a</html>";
                } else {
                    datos[i][j] = "";
                }
            }
        }

        DefaultTableModel modelo = new DefaultTableModel(datos, encabezados) {
            public boolean isCellEditable(int r, int c) { return false; }
        };

        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(45);
        tabla.getTableHeader().setReorderingAllowed(false);

        // Encabezados de fila con "Fila N"
        JTable filaHeader = new JTable(new DefaultTableModel(
            generarEncabezadosFilas(filas), new String[]{""}
        ) {
            public boolean isCellEditable(int r, int c) { return false; }
        });
        filaHeader.setRowHeight(45);
        filaHeader.setPreferredScrollableViewportSize(new Dimension(55, 0));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setRowHeaderView(filaHeader);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private String[][] generarEncabezadosFilas(int filas) {
        String[][] data = new String[filas][1];
        for (int i = 0; i < filas; i++) data[i][0] = "Fila " + (i + 1);
        return data;
    }
}