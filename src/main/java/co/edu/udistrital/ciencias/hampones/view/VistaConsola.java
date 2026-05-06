package co.edu.udistrital.ciencias.hampones.view;
import java.util.Scanner;

/**
 * Clase encargada de gestionar la interacción con el usuario a través de la consola.
 * 
 * <p>Proporciona métodos simplificados para la lectura de datos desde el teclado
 * y la impresión de mensajes en la salida estándar, encapsulando el uso de 
 * {@link java.util.Scanner}.</p>
 * 
 */
public class VistaConsola {
    private Scanner sc;             /**Scanner para la lectura de datos*/
    
    /**
     * Constructor de la clase. 
     * Inicializa el flujo de entrada de datos utilizando el flujo {@code System.in}.
     */
    public VistaConsola(){
        sc = new Scanner(System.in);
        
    }    
    
    /**
     * Muestra un mensaje informativo o resultado en la consola del sistema.
     * 
     * @param msj El texto o mensaje que se desea imprimir en pantalla.
     */
    public void mostrarInformacion(String msj){
        System.out.println(msj);
    }
    
    /**
     * Solicita un dato al usuario mediante un mensaje y captura la respuesta.
     * 
     * 
     * @param msj El mensaje o instrucción que se le muestra al usuario.
     * @return {@code String} con el dato capturado desde la consola.
     */
    public String leerDato(String msj){
        System.out.println(msj);
        String dato = sc.nextLine();
        return dato;
    }
}