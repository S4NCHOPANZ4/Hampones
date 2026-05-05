package co.edu.udistrital.ciencias.hampones.model;

/**
 * Representa un auditorio compuesto por asientos.
 * La clase gestiona una matriz de  {@link Hampon}, permitiendo 
 * controlar la distribución de los mismos en filas y columnas.
 * 
 * @author 
 */
public class Auditorio {
    
    private Hampon[][] asientos;  // Matriz bidimensional para representar los asientos (k, m)
    private int filas;
    private int columnas;
    
    /**
     * Construye un nuevo Auditorio con dimensiones específicas.
     * 
     * @param i El número de filas para el auditorio.
     * @param j El número de columnas para el auditorio.
     */
    public Auditorio(int i, int j){
        this.filas = i;
        this.columnas = j;
        asientos = new Hampon[filas][columnas];
    }
   
    /**
     * Limpia todos los asientos del auditorio, asignando {@code null} a cada posición.
     * Este método recorre la matriz completa para eliminar las referencias existentes.
     */
    public void borrarElementos(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < columnas; j++){
                asientos[i][j] = null;
            }
        }
    } 
    
    /**
     * Actualiza la estructura de asientos del auditorio mediante una matriz externa.
     * Ajusta automáticamente los contadores de filas y columnas basándose en 
     * las dimensiones de la matriz proporcionada.
     * 
     * @param asientos Matriz de {@link Hampon} que define la nueva configuración.
     */
    public void setSillas(Hampon[][] asientos){
        this.filas = asientos.length;
        if(asientos.length > 0){
            this.columnas = asientos[0].length;
        }else{
        this.columnas = 0;
        }
        this.asientos = asientos;
    }
    
        /**
        * Permite sentar a un hampón en una coordenada específica 
        * sin tener que manipular toda la matriz.
        */
       public void setAsiento(int i ,int j, Hampon hampon) {
           if (i >= 0 && i < filas && j >= 0 && j < columnas) {
               this.asientos[i][j] = hampon;
           }
       }
       
     /**
     * Llena los asientos del auditorio tomando hampones desde un arreglo unidimensional.
     * Si hay más hampones que asientos, agrega las filas necesarias para acomodarlos a todos.
     * 
     * @param listaHampones El arreglo con el grupo de hampones que llegó.
     */
    public void acomodarDesdeLista(Hampon[] listaHampones) {
        int totalHampones = listaHampones.length;
        
        // Calcula  cuántas filas se necesitan 
        // Divide el total de personas entre las columnas. 
        int filasNecesarias = (totalHampones + this.columnas - 1) / this.columnas;

        // Comprueba si necesitamos expandir el auditorio
        if (filasNecesarias > this.filas) {
            this.filas = filasNecesarias;
            // Crea una nueva matriz
            this.asientos = new Hampon[this.filas][this.columnas]; 
            
        } else {
            // Si no necesita mas, solo lo limpia
            this.borrarElementos();
        }

        // Acomoda  los hampones en la matriz
        int indiceHampon = 0;   // Contador
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas; j++) {
                
                // Mientras queden hampones en la lista, los sentamos
              
                if (indiceHampon < totalHampones) {

                setAsiento(i, j, listaHampones[indiceHampon]);
                indiceHampon++;
                } else {
                    return; // Todos sentados
                }
                
            }
        }
    }
    
    // GETTERS
    
    public Hampon[][] getAsientos(){
        return asientos;
    }
    public int getFilas(){
        return filas;
    }
    public int getColumnas(){
        return columnas;
    }
    
    
}