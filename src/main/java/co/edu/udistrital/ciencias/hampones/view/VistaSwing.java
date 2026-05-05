package co.edu.udistrital.ciencias.hampones.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaSwing extends JFrame {

    private JTextField campoN;
    private JButton botonOrdenar;
    private JComboBox<String> comboGenerador;
    private PanelAuditorio panelAuditorio;
    private PanelListaOriginal panelLista;

    private static final String[] GENERADORES = {
        "Aleatoria",
        "Mayor a Menor",
        "Menor a Mayor"
    };

    public VistaSwing() {
        setTitle("APOCO - Organizador de Hampones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelTop = new JPanel();
        panelTop.add(new JLabel("Cantidad (cuadrado perfecto):"));
        campoN = new JTextField(6);
        panelTop.add(campoN);
        panelTop.add(new JLabel("Tipo de lista:"));
        comboGenerador = new JComboBox<>(GENERADORES);
        panelTop.add(comboGenerador);
        botonOrdenar = new JButton("Ordenar");
        panelTop.add(botonOrdenar);
        add(panelTop, BorderLayout.NORTH);

        // Paneles centrales
        panelAuditorio = new PanelAuditorio();
        panelLista = new PanelListaOriginal();

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelAuditorio, panelLista);
        split.setDividerLocation(700);
        add(split, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getCampoN() { return campoN.getText().trim(); }

    public int getGeneradorSeleccionado() { return comboGenerador.getSelectedIndex(); }

    public void mostrarError(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void agregarListenerOrdenar(ActionListener listener) {
        botonOrdenar.addActionListener(listener);
    }

    public PanelAuditorio getPanelAuditorio() { return panelAuditorio; }

    public PanelListaOriginal getPanelLista() { return panelLista; }
}