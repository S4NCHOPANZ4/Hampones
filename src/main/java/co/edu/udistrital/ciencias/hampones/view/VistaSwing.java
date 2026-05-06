package co.edu.udistrital.ciencias.hampones.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clase que representa la ventana principal de la interfaz gráfica de usuario (GUI).
 * 
 * <p>Extiende de {@link JFrame} y organiza los componentes necesarios para capturar
 * los parámetros de simulación, seleccionar fuentes de datos y visualizar los 
 * resultados del ordenamiento de hampones.</p>
 */
public class VistaSwing extends JFrame {

    private JTextField campoN;                  /** Campo de texto para ingresar la cantidad de hampones*/
    private JButton botonOrdenar;               /** Botón para iniciar el proceso de generación y ordenamiento. */
    private JComboBox<String> comboGenerador;   /** Selector desplegable para elegir el tipo de generación de datos. */
    private PanelAuditorio panelAuditorio;      /** Panel para la visualización del auditorio*/
    private PanelListaOriginal panelLista;      /** Panel lateral para listar los datos originales*/
    private JFileChooser fileChooser;           /** Selector de archivos para la importación de datos desde archivos de texto (.txt). */
    
    
    /** Opciones disponibles para la generación de datos. */
    private static final String[] GENERADORES = {
        "Aleatoria",
        "Mayor a Menor",
        "Menor a Mayor",
        "Importar desde TXT"
    };
    
    
    /**
     * Constructor de la vista. Inicializa el marco principal, configura el layout,
     * los componentes de control superior y los paneles de visualización central.
     * 
     * <p>Incluye un listener interno para el {@code comboGenerador} que dispara 
     * automáticamente el selector de archivos cuando se elige la opción de importar.</p>
     */
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
        panelTop.add(new JLabel("Tipo de lista a generar:"));
        comboGenerador = new JComboBox<>(GENERADORES);
        panelTop.add(comboGenerador);
        botonOrdenar = new JButton("Ordenar");
        panelTop.add(botonOrdenar);
        add(panelTop, BorderLayout.NORTH);
        
        // Configuración del selector de archivos
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.dir") + "/DOCS"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Archivos TXT", "txt"));

        // listener de "Importar desde TXT"
        comboGenerador.addActionListener(e -> {
            if (comboGenerador.getSelectedIndex() == 3) { 
                int resultado = fileChooser.showOpenDialog(this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                   
                } else {
                    comboGenerador.setSelectedIndex(0); // Volver
                }
            }
        });

        // Paneles centrales
        panelAuditorio = new PanelAuditorio();
        panelLista = new PanelListaOriginal();
        
        // División visual entre el auditorio y la lista lateral
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelAuditorio, panelLista);
        split.setDividerLocation(700);
        add(split, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    /**
     * Obtiene el texto ingresado en el campo de cantidad.
     * @return String con el valor numérico ingresado.
     */
    public String getCampoN() { return campoN.getText().trim(); }
    
    
    /**
     * Obtiene el índice de la opción seleccionada en el combo de generación.
     * @return Entero que representa la opción (0: Aleatoria, 1: Mayor-Menor, 2: Menor-Mayor, 3: TXT).
     */
    public int getGeneradorSeleccionado() { return comboGenerador.getSelectedIndex(); }
    
    /**
     * Obtiene el archivo seleccionado por el usuario a través del {@link JFileChooser}.
     * @return El objeto {@link java.io.File} seleccionado, o null si no se eligió ninguno.
     */
    public java.io.File getArchivoSeleccionado() {
        return fileChooser.getSelectedFile();
    }
    
    /**
     * Despliega un cuadro de diálogo emergente para informar errores al usuario.
     * @param msj Mensaje descriptivo del error.
     */
    public void mostrarError(String msj) {
        JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Asigna un listener al botón de ordenamiento para ser gestionado por el controlador.
     * @param listener Objeto que implementa la interfaz {@link ActionListener}.
     */
    public void agregarListenerOrdenar(ActionListener listener) {
        botonOrdenar.addActionListener(listener);
    }
    
    /**
     * acceso al panel donde se visualizan los algoritmos y métricas.
     * @return Instancia de {@link PanelAuditorio}.
     */
    public PanelAuditorio getPanelAuditorio() { return panelAuditorio; }
    
    /**
     * acceso al panel donde se lista la información original de los hampones.
     * @return Instancia de {@link PanelListaOriginal}.
     */
    public PanelListaOriginal getPanelLista() { return panelLista; }
}