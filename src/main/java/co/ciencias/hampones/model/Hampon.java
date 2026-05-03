package co.ciencias.hampones.model;

public class Hampon {
    private int edad;
    private int dinero;
    private String nombre;  

    public Hampon(){
        edad = 0;
        dinero = 0;
        nombre = "";
    }
    public Hampon(int edad, int dinero, String nombre){
        this.edad = edad;
        this.dinero = dinero;
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public int getDinero() {
        return dinero;
    }
    public String getNombre() {
        return nombre;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}