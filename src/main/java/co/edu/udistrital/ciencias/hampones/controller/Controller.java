package co.edu.udistrital.ciencias.hampones.controller;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import co.edu.udistrital.ciencias.hampones.view.VistaSwing;

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

        Hampon[][][] resultados  = new Hampon[5][][];
        int[]        iteraciones = new int[5];
        long[]       tiempos     = new long[5];  

        resultados[0] = ordenador.ordenarMatrizBubblesort(lista);  iteraciones[0] = ordenador.getIteraciones(); tiempos[0] = ordenador.getTiempoNs();
        resultados[1] = ordenador.ordenarSelectionSort(lista);      iteraciones[1] = ordenador.getIteraciones(); tiempos[1] = ordenador.getTiempoNs();
        resultados[2] = ordenador.ordenarInsertionSort(lista);      iteraciones[2] = ordenador.getIteraciones(); tiempos[2] = ordenador.getTiempoNs();
        resultados[3] = ordenador.ordenarQuickSort(lista);          iteraciones[3] = ordenador.getIteraciones(); tiempos[3] = ordenador.getTiempoNs();
        resultados[4] = ordenador.ordenarMergeSort(lista);          iteraciones[4] = ordenador.getIteraciones(); tiempos[4] = ordenador.getTiempoNs();

        vista.getPanelAuditorio().mostrar(algoritmos, resultados, iteraciones, tiempos);
    }
}