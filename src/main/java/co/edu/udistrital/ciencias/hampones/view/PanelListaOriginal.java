package co.edu.udistrital.ciencias.hampones.view;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import javax.swing.*;
import java.awt.*;

/**
 * Componente gráfico que permite visualizar la lista de hampones en su estado original.
 * 
 * <p>Extiende de {@link JPanel} y utiliza un área de texto con desplazamiento
 * para presentar de forma secuencial y legible la información de cada {@link Hampon} 
 * (nombre, dinero y edad) antes de que se aplique cualquier lógica de ordenamiento.</p>
 * 
 */
public class PanelListaOriginal extends JPanel {

    private JTextArea textArea;
    
    
    /**
     * Constructor del panel.
     * <p>Configura el diseño de borde (BorderLayout), establece un título descriptivo
     * al borde del panel e inicializa el área de texto</p>
     */
    public PanelListaOriginal() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Lista original (sin ordenar)"));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    
    
    /**
     * Recibe un arreglo de hampones y actualiza el contenido visual del panel.
     * 
     * <p>El método recorre la lista, construye una cadena formateada para cada objeto
     * y reemplaza el texto actual en el componente {@code textArea}.</p>
     * 
     * @param lista Arreglo de objetos {@link Hampon} que se desea mostrar.
     */
    public void mostrar(Hampon[] lista) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.length; i++) {
            Hampon h = lista[i];
            sb.append((i + 1) + ". " + h.getNombre() + " | $" + h.getDinero() + " | " + h.getEdad() + " anios\n");
        }
        textArea.setText(sb.toString());
    }
}