package co.ciencias.hampones.controller;

import co.ciencias.hampones.model.Hampon;
import java.util.Random;

public class GeneradorDeHampones {

    private Random random = new Random();

    private String[] nombresDeHampones = {
        "ANDRÉS FORERO",
        "RAFAEL NIETO",
        "CLAUDIA  ZULETA",
        "HERNÁN  MÁRQUEZ",
        "JULIA  NUTTIN",
        "CARLOS  VERGARA",
        "CHRISTIAN GARCÉS",
        "MARIA CLARA POSADA",
        "HONORIO HENRIQUEZ",
        "ALIRIO BARRERA",
        "ESTEBAN QUINTERO",
        "ENRIQUE CABRALES",
        "OSCAR VILLAMIZAR",
        "JUAN ESPINAL RAMÍREZ",
        "MARIA ANGÉLICA GUERRA ",
        "JUAN CAICEDO CALLEJAS",
        "ZANDRA BERNAL",
        "LIZETH REINA",
        "JOSE VICENTE",
        "MARELEN CASTILLO",
        "CAROLINA RESTREPO",
        "ESTEFANIA COLMENARES",
        "AMALIA SALGADO ",
        "CAMILO GAVIRIA",
        "ALVARO URIBE VÉLEZ"
    };

    public Hampon[] generarListaAleatoria(int cantidad) {
        Hampon[] lista = new Hampon[cantidad];
        for (int i = 0; i < cantidad; i++) {
            int edad = random.nextInt(60) + 18;
            int dinero = random.nextInt(100000) + 1;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad, dinero, nombre);
        }
        return lista;
    }

//MEJOR CASO
    public Hampon[] generarListaDatosMayorAMenor(int cantidad) {
        Hampon[] lista = new Hampon[cantidad];
        for(int i = 0; i < cantidad; i++) {
            int edad = (cantidad - i) + 18;
            int dinero = (cantidad - i) * 1000 + 100;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad, dinero, nombre);
        }
        return lista;
    }

    public Hampon[] generarListaDatosMenorAMayor(int cantidad) {
        Hampon[] lista = new Hampon[cantidad];
        for (int i = 0; i < cantidad; i++) {
            int edad = i + 18;           
            int dinero = i * 1000 + 100; 
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad, dinero, nombre);
        }
        return lista;
    }
}
