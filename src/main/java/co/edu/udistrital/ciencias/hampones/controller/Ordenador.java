package co.edu.udistrital.ciencias.hampones.controller;
import co.edu.udistrital.ciencias.hampones.model.Hampon;
import java.util.Comparator;

/**
 * Clase encargada de ejecutar múltiples algoritmos de ordenamiento sobre objetos de tipo {@link Hampon}.
 * 
 * <p>La clase implementa cinco algoritmos clásicos (Bubble, Selection, Insertion, Quick y Merge Sort). 
 * Realiza un ordenamiento en dos fases: primero ordena un arreglo lineal por dinero y luego 
 * transforma ese arreglo en una matriz donde cada fila se ordena individualmente por edad.</p>
 * 
 */
public class Ordenador {

    private int iteraciones;         /** Contador de iteraciones*/
    private long tiempoNs;           /** Tiempo de ejecución en nanosegundos */
    
    
    /**
     * Obtiene el número de iteraciones del último proceso de ordenamiento.
     * @return Cantidad de operaciones contabilizadas.
     */
    public int getIteraciones(){ 
        return iteraciones;
    }
    
    /**
     * Obtiene el tiempo de ejecución en nanosegundos del último proceso.
     * @return Tiempo transcurrido en nanosegundos.
     */
    public long getTiempoNs(){ 
        return tiempoNs;
    }
    
    
    // ─── Bubble Sort ────────────────────────────────────────────────────────────
    
    /**
     * Implementación estándar del algoritmo Bubble Sort.
     * @param arr Arreglo de Hampones a ordenar.
     * @param criterio Comparador que define la lógica de ordenamiento.
     * @return El mismo arreglo ordenado.
     */
    public Hampon[] bubbleSort(Hampon[] arr, Comparator<Hampon> criterio) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++) {
                iteraciones++;
                if (criterio.compare(arr[j], arr[j + 1]) > 0) {
                    Hampon t = arr[j]; arr[j] = arr[j+1]; arr[j+1] = t;
                }
            }
        }
        return arr;
    }
    
    /**
     * Ejecuta el proceso de ordenamiento Bubble Sort completo: linealmente por dinero 
     * y matricialmente por edad.
     * @param datos Arreglo original de hampones.
     * @return Matriz de hampones ordenada.
     */
    public Hampon[][] ordenarMatrizBubblesort(Hampon[] datos) {
        iteraciones = 0;
        long inicio = System.nanoTime();
        Hampon[] copia = datos.clone();
        copia = bubbleSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        for (int i = 0; i < asientos.length; i++){
            asientos[i] = bubbleSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        tiempoNs = System.nanoTime() - inicio;
        return asientos;
    }
    
    
    // ─── Selection Sort ─────────────────────────────────────────────────────────
    
    /**
     * Implementación del algoritmo Selection Sort.
     * @param arr Arreglo de Hampones a ordenar.
     * @param criterio Comparador que define la lógica de ordenamiento.
     * @return Arreglo ordenado.
     */
    public Hampon[] selectionSort(Hampon[] arr, Comparator<Hampon> criterio) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                iteraciones++;
                if (criterio.compare(arr[min], arr[j]) > 0) min = j;
            }
            if (criterio.compare(arr[i], arr[min]) > 0) {
                Hampon t = arr[i]; arr[i] = arr[min]; arr[min] = t;
            }
        }
        return arr;
    }
    
    /**
     * Ejecuta el proceso de ordenamiento Selection Sort.
     * @param datos Arreglo original de hampones.
     * @return Matriz de hampones ordenada.
     */
    public Hampon[][] ordenarSelectionSort(Hampon[] datos) {
        iteraciones = 0;
        long inicio = System.nanoTime();
        Hampon[] copia = datos.clone();
        copia = selectionSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        for (int i = 0; i < asientos.length; i++){
            asientos[i] = selectionSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        tiempoNs = System.nanoTime() - inicio;
        return asientos;
    }

    // ─── Insertion Sort ─────────────────────────────────────────────────────────
    
    /**
     * Implementación del algoritmo Insertion Sort.
     * @param arr Arreglo de Hampones a ordenar.
     * @param criterio Comparador que define la lógica de ordenamiento.
     * @return Arreglo ordenado.
     */
    public Hampon[] insertionSort(Hampon[] arr, Comparator<Hampon> criterio) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Hampon temp = arr[i];
            int j = i - 1;
            while (j >= 0 && criterio.compare(arr[j], temp) > 0) {
                iteraciones++;
                arr[j + 1] = arr[j];
                j--;
            }
            iteraciones++;
            arr[j + 1] = temp;
        }
        return arr;
    }
    
    /**
     * Ejecuta el proceso de ordenamiento Insertion Sort.
     * @param datos Arreglo original de hampones.
     * @return Matriz de hampones ordenada.
     */
    public Hampon[][] ordenarInsertionSort(Hampon[] datos) {
        iteraciones = 0;
        long inicio = System.nanoTime();
        Hampon[] copia = datos.clone();
        copia = insertionSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        for (int i = 0; i < asientos.length; i++){
            asientos[i] = insertionSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        tiempoNs = System.nanoTime() - inicio;
        return asientos;
    }

    // ─── Quick Sort ─────────────────────────────────────────────────────────────
    
    /**
     * Implementación recursiva del algoritmo Quick Sort.
     * @param arr Arreglo a ordenar.
     * @param start Índice inicial del sub-arreglo.
     * @param end Índice final del sub-arreglo.
     * @param filtro Atributo por el cual se ordena ("dinero" o "edad").
     * @return Arreglo ordenado.
     */
    public Hampon[] quickSort(Hampon[] arr, int start, int end, String filtro) {
        if (end <= start) return arr;
        int pivot;
        if (filtro.equals("dinero")){
            pivot = particion(arr, start, end, (a,b) -> a.getDinero() - b.getDinero());
        }
        else{
            pivot = particion(arr, start, end, (a,b) -> a.getEdad() - b.getEdad());
        }
        quickSort(arr, start, pivot - 1, filtro);
        quickSort(arr, pivot + 1, end, filtro);
        return arr;
    }
    
    /**
     * Método auxiliar de Quick Sort que particiona el arreglo en torno a un pivote.
     * @param arr Arreglo a particionar.
     * @param start Índice inicial.
     * @param end Índice final (donde se ubica el pivote inicial).
     * @param comp Comparador para la lógica de partición.
     * @return El índice final del pivote después de la reorganización.
     */
    public int particion(Hampon[] arr, int start, int end, Comparator<Hampon> comp) {
        Hampon pivot = arr[end];
        int i = start - 1;
        for (int j = start; j <= end - 1; j++) {
            iteraciones++;
            if (comp.compare(pivot, arr[j]) > 0) {
                i++;
                Hampon temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        Hampon t = arr[i];
        arr[i] = arr[end];
        arr[end] = t;
        return i;
    }
    
    /**
     * Ejecuta el proceso de ordenamiento Quick Sort.
     * @param datos Arreglo original de hampones.
     * @return Matriz de hampones ordenada.
     */
    public Hampon[][] ordenarQuickSort(Hampon[] datos) {
        iteraciones = 0;
        long inicio = System.nanoTime();
        Hampon[] copia = datos.clone();
        copia = quickSort(copia, 0, copia.length - 1, "dinero");
        Hampon[][] asientos = construirMatriz(copia);
        for (int i = 0; i < asientos.length; i++){
            asientos[i] = quickSort(asientos[i], 0, asientos[i].length - 1, "edad");
        }
        tiempoNs = System.nanoTime() - inicio;
        return asientos;
    }

    // ─── Merge Sort ─────────────────────────────────────────────────────────────

    /**
     * Implementación recursiva de Merge Sort.
     * Divide el arreglo en mitades hasta llegar a arreglos de tamaño 1.
     * @param arr Arreglo a dividir.
     * @param filtro Atributo por el cual se ordena ("dinero" o "edad").
     * @return Arreglo ordenado.
     */
    public Hampon[] mergeSort(Hampon[] arr, String filtro){
        int n = arr.length;
        if(n <= 1) return arr;
        int mid = Math.floorDiv(n, 2);
        Hampon[] left = new Hampon[mid];
        Hampon[] right = new Hampon[n - mid];
        int j = 0;
        for(int i = 0; i < n; i++){
            if(i < mid){ left[i] = arr[i]; }
            else{ right[j] = arr[i]; j++; }
        }
        mergeSort(left, filtro);
        mergeSort(right, filtro);
        if(filtro.equals("dinero")){
            merge(left, right, arr, (a,b) -> a.getDinero() - b.getDinero());
        }else if(filtro.equals("edad")){
            merge(left, right, arr, (a,b) -> a.getEdad() - b.getEdad());
        }
        return arr;
    }
    
    /**
     * Método auxiliar de Merge Sort que combina dos sub-arreglos de forma ordenada.
     * @param left Sub-arreglo izquierdo.
     * @param right Sub-arreglo derecho.
     * @param arr Arreglo destino donde se mezclan los datos.
     * @param comp Comparador para la lógica de mezcla.
     */
    public void merge(Hampon[] left, Hampon[] right, Hampon[] arr, Comparator<Hampon> comp){
        int leftSize = arr.length / 2;
        int rightSize = arr.length - leftSize;
        int i = 0, leftPivot = 0, rightPivot = 0;
        while(leftPivot < leftSize && rightPivot < rightSize){
            iteraciones++;
            if(comp.compare(right[rightPivot], left[leftPivot]) > 0){
                arr[i++] = left[leftPivot++];
            }else{
                arr[i++] = right[rightPivot++];
            }
        }
        while(leftPivot < leftSize){ arr[i++] = left[leftPivot++]; }
        while(rightPivot < rightSize){ arr[i++] = right[rightPivot++]; }
    }
    
    /**
     * Ejecuta el proceso de ordenamiento Merge Sort.
     * @param data Arreglo original de hampones.
     * @return Matriz de hampones ordenada.
     */
    public Hampon[][] ordenarMergeSort(Hampon[] data){
        iteraciones = 0;
        long inicio = System.nanoTime();
        Hampon[] copia = data.clone();
        copia = mergeSort(copia, "dinero");
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i = 0; i < filas; i++){
            asientos[i] = mergeSort(asientos[i], "edad");
        }
        tiempoNs = System.nanoTime() - inicio;
        return asientos;
    }
    
    
    // ─── construirMatriz — cuadrado perfecto k×k ────────────────────────────────
    
    /**
     * Transforma un arreglo lineal en una matriz cuadrada.
     * Se utiliza principalmente para representar los asientos en el auditorio.
     * @param lista Arreglo plano de hampones.
     * @return Una matriz de {@code k x m} donde {@code k} es la raíz cuadrada de n.
     */
    public Hampon[][] construirMatriz(Hampon[] lista) {
        int n = lista.length;
        int k = (int) Math.sqrt(n);
        int m = (k == 0) ? n : (int) Math.ceil((double) n / k);
        Hampon[][] matriz = new Hampon[k][m];
        int index = 0;
        for (int i = 0; i < k; i++)
            for (int j = 0; j < m; j++)
                matriz[i][j] = (index < n) ? lista[index++] : null;
        return matriz;
    }
}