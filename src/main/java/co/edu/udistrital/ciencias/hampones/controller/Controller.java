package co.edu.udistrital.ciencias.hampones.controller;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import co.edu.udistrital.ciencias.hampones.view.VistaSwing;

/**
 * Clase controladora del sistema de ordenamiento de hampones.
 * Actúa como intermediaria entre la  Vista y la lógica generación y ordenamiento de datos
 * 
 */
public class Controller {

    private GeneradorDeHampones generador;  /** se encarga de crear datos de prueba. */
    private Ordenador ordenador;            /** contiene la implementación de los algoritmos de ordenamiento. */
    private VistaSwing vista;               /** Interfaz gráfica. */

    private String[] algoritmos = {"Bubble Sort", "Selection Sort", "Insertion Sort", "Quick Sort", "Merge Sort"};
    
    //Constructor
    /**
     * Inicializa una nueva instancia del controlador.
     * Configura los componentes internos y establece el enlace de listeners entre la vista y el controlador.
     */
    public Controller() {
        generador = new GeneradorDeHampones();  
        ordenador = new Ordenador();            
        vista = new VistaSwing();               
        vista.agregarListenerOrdenar(e -> ordenar());
    }
    
    /**
     * Ejecuta el flujo principal del programa cuando se activa el evento de ordenamiento.
     * 
     * <p>Incluyendo:</p>
     * <ul>
     *   <li>la validacion de si el número {@code n} es un cuadrado perfecto.</li>
     *   <li>Obtener la lista de hampones </li>
     *   <li>Ejecutar los 5 algoritmos de ordenamiento en paralelo sobre clones de la lista inicial.</li>
     *   <li>Capturar tiempos en nanosegundos e iteraciones.</li>
     *   <li>Enviar los resultados finales a la vista para su representación visual.</li>
     * </ul>
     */
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
        
        // Muestra la lista inicial antes de ordenar
        vista.getPanelLista().mostrar(lista);
        
        // Almacenamiento de resultados en matrices por algoritmo (5), iteraciones y tiempos
        Hampon[][][] resultados  = new Hampon[5][][];
        int[]        iteraciones = new int[5];
        long[]       tiempos     = new long[5];  
        
        // Ejecución de Bubble Sort
        resultados[0] = ordenador.ordenarMatrizBubblesort(lista.clone());  
        iteraciones[0] = ordenador.getIteraciones(); 
        tiempos[0] = ordenador.getTiempoNs();
        
        // Ejecución de Selection Sort
        resultados[1] = ordenador.ordenarSelectionSort(lista.clone());      
        iteraciones[1] = ordenador.getIteraciones(); 
        tiempos[1] = ordenador.getTiempoNs();
        
        // Ejecución de Insertion Sort
        resultados[2] = ordenador.ordenarInsertionSort(lista.clone());      
        iteraciones[2] = ordenador.getIteraciones(); 
        tiempos[2] = ordenador.getTiempoNs();
        
        // Ejecución de Quick Sort
        resultados[3] = ordenador.ordenarQuickSort(lista.clone());          
        iteraciones[3] = ordenador.getIteraciones(); 
        tiempos[3] = ordenador.getTiempoNs();
        
        // Ejecución de Merge Sort
        resultados[4] = ordenador.ordenarMergeSort(lista.clone());          
        iteraciones[4] = ordenador.getIteraciones(); 
        tiempos[4] = ordenador.getTiempoNs();
        
        // Actualiza el auditorio
        vista.getPanelAuditorio().mostrar(algoritmos, resultados, iteraciones, tiempos);
    }
}