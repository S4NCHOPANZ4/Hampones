package co.ciencias.hampones.model;

/**
 * Representa la entidad de un Hampon dentro del sistema.
 *
 * @author
 */
public class Hampon {

    private int edad;
    private int dinero;
    private String nombre;

    /**
     * Constructor por defecto. Inicializa un objeto Hampon con valores base (0
     * para números y cadena vacía para el nombre).
     */
    public Hampon() {
        edad = 0;
        dinero = 0;
        nombre = "";
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos de la
     * clase.
     *
     * @param edad El valor entero para la edad inicial.
     * @param dinero El capital inicial asignado al hampón.
     * @param nombre El nombre o alias identificativo.
     */
    public Hampon(int edad, int dinero, String nombre) {
        this.edad = edad;
        this.dinero = dinero;
        this.nombre = nombre;
    }

    // GETTERS
    public int getEdad() {
        return edad;
    }

    public int getDinero() {
        return dinero;
    }

    public String getNombre() {
        return nombre;
    }

    // SETTERS
    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //TO STRING
    /**
     * Genera una representación en texto del estado actual del hampon.
     *
     * @return Una cadena formateada que incluye la edad, el dinero y el nombre.
     */
    @Override
    public String toString() {
        return "Hampon{" + "edad=" + edad + ", dinero=" + dinero + ", nombre=" + nombre + '}';
    }

}
