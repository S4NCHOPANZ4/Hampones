package co.edu.udistrital.ciencias.hampones.controller;

/**
 * Clase de entrada principal (launcher) de la aplicación.
 * <p>
 * Esta clase se encarga de iniciar el ciclo de vida del programa, delegando 
 * la lógica de inicialización al controlador principal del sistema.
 * </p>
 */
public class AplMain {
    
    /**
     * Método principal que sirve como punto de inicio para la ejecución de la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        new Controller();
    }
}