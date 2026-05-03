package co.ciencias.hampones.controller;

import co.ciencias.hampones.model.GeneradorDeHampones;
import co.ciencias.hampones.model.Hampon;
import co.ciencias.hampones.model.Ordenador;
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
            vista.mostrarError("Ingrese un numero entero valido.");
            return;
        }
        int raiz = (int) Math.round(Math.sqrt(n));
        if (raiz * raiz != n || n <= 0) {
            vista.mostrarError("El numero debe ser un cuadrado perfecto (4, 9, 16, 25...).");
            return;
        }

        // Elegir generador según combo
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
        resultados[3] = ordenador.odenarQuickSort(lista);            iteraciones[3] = ordenador.getIteraciones();
        resultados[4] = ordenador.ordenarMergeSort(lista);           iteraciones[4] = ordenador.getIteraciones();

        vista.getPanelAuditorio().mostrar(algoritmos, resultados, iteraciones);
    }
}