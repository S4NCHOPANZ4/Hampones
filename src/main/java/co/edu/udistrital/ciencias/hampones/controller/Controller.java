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
    Hampon[] lista = null;
    
    int tipoGenerador = vista.getGeneradorSeleccionado();
    
    // Si es importar desde TXT
    if (tipoGenerador == 3) {
        java.io.File archivo = vista.getArchivoSeleccionado();
        if (archivo == null || !archivo.exists()) {
            vista.mostrarError("Debe seleccionar un archivo válido.");
            return;
        }
        
        try {
            lista = generador.importarListaDesdeArchivo(archivo.getAbsolutePath());
            n = lista.length;
            
            if (n == 0) {
                vista.mostrarError("El archivo está vacío.");
                return;
            }
        } catch (Exception ex) {
            vista.mostrarError("Error al importar archivo: " + ex.getMessage());
            return;
        }
     } else {
        // Generadores automáticos
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

        int raiz = (int) Math.round(Math.sqrt(n));
        if (raiz * raiz != n) {
            vista.mostrarError("El numero ingresado no es un cuadrado perfecto.\nEjemplo válido: 4, 9, 16, 25...");
            return;
        }

        // Elegir generador
        switch (tipoGenerador) {
            case 1:  lista = generador.generarListaDatosMayorAMenor(n); break;
            case 2:  lista = generador.generarListaDatosMenorAMayor(n); break;
            default: lista = generador.generarListaAleatoria(n);        break;
        }
    }

        vista.getPanelLista().mostrar(lista);

        Hampon[][][] resultados  = new Hampon[5][][];
        int[]        iteraciones = new int[5];
        long[]       tiempos     = new long[5];  

        resultados[0] = ordenador.ordenarMatrizBubblesort(lista.clone());  
        iteraciones[0] = ordenador.getIteraciones(); 
        tiempos[0] = ordenador.getTiempoNs();

        resultados[1] = ordenador.ordenarSelectionSort(lista.clone());      
        iteraciones[1] = ordenador.getIteraciones(); 
        tiempos[1] = ordenador.getTiempoNs();

        resultados[2] = ordenador.ordenarInsertionSort(lista.clone());      
        iteraciones[2] = ordenador.getIteraciones(); 
        tiempos[2] = ordenador.getTiempoNs();

        resultados[3] = ordenador.ordenarQuickSort(lista.clone());          
        iteraciones[3] = ordenador.getIteraciones(); 
        tiempos[3] = ordenador.getTiempoNs();

        resultados[4] = ordenador.ordenarMergeSort(lista.clone());          
        iteraciones[4] = ordenador.getIteraciones(); 
        tiempos[4] = ordenador.getTiempoNs();

        vista.getPanelAuditorio().mostrar(algoritmos, resultados, iteraciones, tiempos);
    }
}