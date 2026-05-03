package co.ciencias.hampones.view;

import co.ciencias.hampones.model.Hampon;
import javax.swing.*;
import java.awt.*;

public class PanelListaOriginal extends JPanel {

    private JTextArea textArea;

    public PanelListaOriginal() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Lista original (sin ordenar)"));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void mostrar(Hampon[] lista) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lista.length; i++) {
            Hampon h = lista[i];
            sb.append((i + 1) + ". " + h.getNombre() + " | $" + h.getDinero() + " | " + h.getEdad() + " anios\n");
        }
        textArea.setText(sb.toString());
    }
}