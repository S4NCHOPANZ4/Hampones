package co.edu.udistrital.ciencias.hampones.controller;

import co.edu.udistrital.ciencias.hampones.model.Hampon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * la clase se encargada de la generación y gestión de objetos de tipo {@link Hampon}.
 * Proporciona métodos para generar listas de hampones con datos aleatorios, ordenados 
 * o importados desde archivos externos.
 * 
 */
public class GeneradorDeHampones {

    private Random random = new Random();
    
    // 50 nombres × 65 apellidos = 3 250 combinaciones únicas posibles
    
    /** 
     * Banco de nombres disponibles para la generación aleatoria.
     */

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
    
    /** 
     * Banco de apellidos disponibles para la generación aleatoria.
     */

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
     * Construye una lista de combinaciones únicas de "Nombre Apellido".
     * El método garantiza que no existan nombres duplicados barajando todas las combinaciones y seleccionando la cantidad solicitada.
     * 
     * @param cantidad El número de nombres únicos requeridos.
     * @return Un arreglo de String con los nombres generados.
     * @throws IllegalArgumentException Si la cantidad solicitada supera el máximo de combinaciones posibles.
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
    
    /**
     * Genera un arreglo de Hampones con valores de edad y dinero totalmente aleatorios.
     * 
     * @param cantidad Número de objetos Hampon a generar.
     * @return Arreglo de {@link Hampon} con datos aleatorios.
     */
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
    
    /**
     * Genera una lista de Hampones cuyos datos numéricos (edad y dinero) 
     * están organizados de forma descendente (de mayor a menor).
     * 
     * @param cantidad Número de objetos Hampon a generar.
     * @return Arreglo de {@link Hampon} ordenado de forma descendente.
     */
    
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
    
    /**
     * Genera una lista de Hampones cuyos datos numéricos (edad y dinero) 
     * están organizados de forma ascendente (de menor a mayor).
     * 
     * @param cantidad Número de objetos Hampon a generar.
     * @return Arreglo de {@link Hampon} ordenado de forma ascendente.
     */
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
    
    /**
     * Importa una lista de hampones desde un archivo de texto plano.
     * El formato esperado por cada línea es: {@code nombre,edad,dinero}.
     * 
     * @param rutaArchivo Ruta local o absoluta del archivo .txt a importar.
     * @return Arreglo de {@link Hampon} con los datos extraídos del archivo.
     * @throws Exception Si ocurre un error de lectura de archivo o acceso.
     */
    public Hampon[] importarListaDesdeArchivo(String rutaArchivo) throws Exception {
        java.util.List<Hampon> lista = new java.util.ArrayList<>();

        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    try {
                        String nombre = partes[0].trim();
                        int edad = Integer.parseInt(partes[1].trim());
                        int dinero = Integer.parseInt(partes[2].trim());
                        lista.add(new Hampon(edad, dinero, nombre));
                    } catch (NumberFormatException e) {
                        System.err.println("Línea inválida: " + linea);
                    }
                }
            }
        }

        return lista.toArray(new Hampon[0]);
}
}