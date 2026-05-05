package co.edu.udistrital.ciencias.hampones.controller;
import co.edu.udistrital.ciencias.hampones.model.Hampon;
import java.util.Comparator;

public class Ordenador {

    private int iteraciones;


    public int getIteraciones() {
        return iteraciones;
    }

    // Bubble Sort
    public Hampon[] bubbleSort(Hampon[] arr, Comparator<Hampon> criterio) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                iteraciones++;
                if (criterio.compare(arr[j], arr[j + 1]) > 0) {
                    Hampon temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
    
    public Hampon[][] ordenarMatrizBubblesort(Hampon[] datos){
        iteraciones = 0;
        Hampon[] copia = datos.clone();
        copia = bubbleSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i=0; i<filas; i++){
            asientos[i] = bubbleSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        return asientos;
    }

    // Selection Sort
    public Hampon[] selectionSort(Hampon[] arr, Comparator<Hampon> criterio){
        int n = arr.length;
        for(int i = 0; i < n-1; i++){
            int min = i;
            for(int j = i + 1; j < n; j++){
                iteraciones++;
                if(criterio.compare(arr[min], arr[j])> 0){
                    min = j;
                }
            }
            if(criterio.compare(arr[i] , arr[min]) > 0){
                Hampon temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        return arr;
    }
    public Hampon[][] ordenarSelectionSort(Hampon[] datos){
        iteraciones = 0;
        Hampon[] copia = datos.clone();
        copia = selectionSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i=0; i<filas; i++){
            asientos[i] = selectionSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        return asientos;
    }

    // Insertion Sort
    public Hampon[] insertionSort(Hampon[] arr, Comparator<Hampon> criterio){
        int n = arr.length;
        for(int i = 1; i < n; i++){
            Hampon temp = arr[i];
            int j = i - 1;
            while(j >= 0 && criterio.compare(arr[j], temp) > 0){
                iteraciones++;
                arr[j + 1] = arr[j];
                j--;
            }
            iteraciones++;
            arr[j + 1] = temp;
        }
        return arr;
    }
    public Hampon[][] ordenarInsertionSort(Hampon[] datos){
        iteraciones = 0;
        Hampon[] copia = datos.clone();
        copia = insertionSort(copia, (a,b) -> a.getDinero() - b.getDinero());
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i = 0; i < filas; i++){
            asientos[i] = insertionSort(asientos[i], (a,b) -> a.getEdad() - b.getEdad());
        }
        return asientos;
    }

    // Quick Sort
    public Hampon[] quickSort(Hampon[] arr, int start, int end, String filtro){
        if(end <= start) return arr;
        int pivot = 0;
        if(filtro.equals("dinero")){
            pivot = particion(arr, start, end, (a,b) -> a.getDinero() - b.getDinero());
        }else if(filtro.equals("edad")){
            pivot = particion(arr, start, end, (a,b) -> a.getEdad() - b.getEdad());
        }
        quickSort(arr, start, pivot - 1, filtro);
        quickSort(arr, pivot + 1, end, filtro);
        return arr;
    }
    public int particion(Hampon[] arr, int start, int end, Comparator<Hampon> comp){
        Hampon pivot = arr[end];
        int i = start - 1;
        for(int j = start; j <= end - 1; j++){
            iteraciones++;
            if(comp.compare(pivot , arr[j]) > 0){
                i++;
                Hampon temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        Hampon temp = arr[i];
        arr[i] = arr[end];
        arr[end] = temp;
        return i;
    }
    public Hampon[][] ordenarQuickSort(Hampon[] datos){
        iteraciones = 0;
        Hampon[] copia = datos.clone();
        copia = quickSort(copia, 0, copia.length - 1, "dinero");
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i = 0; i < filas; i++){
            asientos[i] = quickSort(asientos[i], 0, asientos[i].length - 1, "edad");
        }
        return asientos;
    }

    // Merge Sort
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
    public Hampon[][] ordenarMergeSort(Hampon[] data){
        iteraciones = 0;
        Hampon[] copia = data.clone();
        copia = mergeSort(copia, "dinero");
        Hampon[][] asientos = construirMatriz(copia);
        int filas = asientos.length;
        for(int i = 0; i < filas; i++){
            asientos[i] = mergeSort(asientos[i], "edad");
        }
        return asientos;
    }

    public Hampon[][] construirMatriz(Hampon[] lista){
        int n = lista.length;
        int k = (int) Math.sqrt(n);
        int m = (int) Math.ceil((double) n / k);
        Hampon[][] matriz = new Hampon[k][m];
        int index = 0;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < m; j++){
                if(index < n){ matriz[i][j] = lista[index++]; }
                else{ matriz[i][j] = null; }
            }
        }
        return matriz;
    }
}