package co.ciencias.hampones.controller;

import co.ciencias.hampones.model.Hampon;
import co.ciencias.hampones.view.VistaSwing;

public class Controller {

    private GeneradorDeHampones generador;
    private Ordenador ordenador;
    private VistaSwing vista;

    private String[] algoritmos = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort"};

    public Controller() {
        generador = new GeneradorDeHampones();
        ordenador = new Ordenador();
        vista = new VistaSwing();
        vista.agregarListenerOrdenar(e -> ordenar());
    }

    private void ordenar() {
        int n;
        try {
            n = Integer.parseInt(vista.getCampoN());
        } catch (NumberFormatException ex) {
            vista.mostrarError("Ingrese un número entero válido.");
            return;
        }
        
        if (n <= 0) {
            vista.mostrarError("La cantidad debe ser mayor que 0.");
            return;
        }

        // Elegir generador
        Hampon[] lista;
        switch (vista.getGeneradorSeleccionado()) {
            case 1:  lista = generador.generarListaDatosMayorAMenor(n); break;
            case 2:  lista = generador.generarListaDatosMenorAMayor(n); break;
            default: lista = generador.generarListaAleatoria(n);        break;
        }

        vista.getPanelLista().mostrar(lista);

        Hampon[][][] resultados = new Hampon[5][][];
        int[] iteraciones = new int[5];

        resultados[0] = ordenador.ordenarMatrizBubblesort(lista);   iteraciones[0] = ordenador.getIteraciones();
        resultados[1] = ordenador.ordenarSelectionSort(lista);       iteraciones[1] = ordenador.getIteraciones();
        resultados[2] = ordenador.ordenarInsertionSort(lista);       iteraciones[2] = ordenador.getIteraciones();
        resultados[3] = ordenador.ordenarQuickSort(lista);           iteraciones[3] = ordenador.getIteraciones();
        resultados[4] = ordenador.ordenarMergeSort(lista);           iteraciones[4] = ordenador.getIteraciones();

        vista.getPanelAuditorio().mostrar(algoritmos, resultados, iteraciones);
    }
    
}