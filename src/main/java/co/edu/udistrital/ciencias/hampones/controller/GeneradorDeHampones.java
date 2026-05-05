package co.edu.udistrital.ciencias.hampones.controller;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeneradorDeHampones {

    private Random random = new Random();

    // 50 nombres × 65 apellidos = 3 250 combinaciones únicas posibles
    private String[] nombres = {
        "Andrés",     "Rafael",      "Claudia",     "Hernán",      "Julia",
        "Carlos",     "Christian",   "María Clara", "Honorio",     "Alirio",
        "Esteban",    "Enrique",     "Óscar",       "Juan",        "María Angélica",
        "Zandra",     "Lizeth",      "José",        "Marelen",     "Carolina",
        "Estefanía",  "Amalia",      "Camilo",      "Álvaro",      "Sofía",
        "Diego",      "Valentina",   "Sebastián",   "Lucía",       "Felipe",
        "Natalia",    "Daniela",     "Miguel Ángel","Juliana",     "Mauricio",
        "Patricia",   "Roberto",     "Gloria",      "Fabio",       "Marcela",
        "Hernando",   "Sandra",      "Jorge",       "Cecilia",     "Luis",
        "Ana",        "Gustavo",     "Elena",       "Raúl",        "Martha"
    };

    private String[] apellidos = {
        "Forero",     "Nieto",       "Zuleta",      "Márquez",     "Nuttin",
        "Vergara",    "Garcés",      "Posada",      "Henríquez",   "Barrera",
        "Quintero",   "Cabrales",    "Villamizar",  "Espinal",     "Guerra",
        "Caicedo",    "Bernal",      "Reina",       "Vicente",     "Castillo",
        "Restrepo",   "Colmenares",  "Salgado",     "Gaviria",     "Uribe",
        "Mendoza",    "Rincón",      "Pardo",       "Mora",        "Herrera",
        "Arango",     "Cárdenas",    "Pedraza",     "Salcedo",     "Torres",
        "Ospina",     "Lozano",      "Guerrero",    "Suárez",      "Ramírez",
        "Ochoa",      "Londoño",     "Arbeláez",    "Ríos",        "Palacio",
        "Montoya",    "Vélez",       "Pineda",      "Rojas",       "Vargas",
        "Echeverri",  "Díaz",        "Duque",       "Noriega",     "Tovar",
        "Castro",     "Mejía",       "Patiño",      "Cano",        "Muñoz",
        "Upegui",     "Jaramillo",   "Gómez",       "Aguilar",     "Cruz"
    };

    /**
     * Construye la lista completa de combinaciones "Nombre Apellido",
     * la baraja y devuelve las primeras `cantidad`.
     */
    private String[] obtenerNombresUnicos(int cantidad) {
        int max = nombres.length * apellidos.length;
        if (cantidad > max) {
            throw new IllegalArgumentException(
                "No hay suficientes combinaciones para garantizar nombres distintos"
            );
        }

        List<String> combinaciones = new ArrayList<>(max);

        for (int i = 0; i < nombres.length; i++) {
            for (int j = 0; j < apellidos.length; j++) {
                combinaciones.add(nombres[i] + " " + apellidos[j]);
            }
        }

        Collections.shuffle(combinaciones);

        String[] pool = new String[cantidad];
        for (int i = 0; i < cantidad; i++) {
            pool[i] = combinaciones.get(i);
        }

        return pool;
    }

    // ─── Generadores ────────────────────────────────────────────────────────────

    public Hampon[] generarListaAleatoria(int cantidad) {
        String[] pool = obtenerNombresUnicos(cantidad);
        Hampon[] lista = new Hampon[cantidad];
        for (int i = 0; i < cantidad; i++) {
            lista[i] = new Hampon(
                random.nextInt(60) + 18,
                random.nextInt(100_000) + 1,
                pool[i]
            );
        }
        return lista;
    }

    public Hampon[] generarListaDatosMayorAMenor(int cantidad) {
        String[] pool = obtenerNombresUnicos(cantidad);
        Hampon[] lista = new Hampon[cantidad];
        for (int i = 0; i < cantidad; i++) {
            lista[i] = new Hampon(
                (cantidad - i) + 18,
                (cantidad - i) * 1000 + 100,
                pool[i]
            );
        }
        return lista;
    }

    public Hampon[] generarListaDatosMenorAMayor(int cantidad) {
        String[] pool = obtenerNombresUnicos(cantidad);
        Hampon[] lista = new Hampon[cantidad];
        for (int i = 0; i < cantidad; i++) {
            lista[i] = new Hampon(
                i + 18,
                i * 1000 + 100,
                pool[i]
            );
        }
        return lista;
    }
}