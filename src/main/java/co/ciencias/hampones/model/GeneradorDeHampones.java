package co.ciencias.hampones.model;

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
    
    public Hampon[] generarListaAleatoria(int cantidad){
        Hampon[] lista = new Hampon[cantidad];
        for(int i = 0; i < cantidad; i++){
            int edad = random.nextInt(60) + 18;
            int dinero = random.nextInt(100000)+ 1;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad,dinero,nombre);
        }
        return lista;
    } 
    
    // Método para generar la matriz del auditorio de hampones
    public Hampon[][] generarAuditorio(int filas, int columnas) {
        Hampon[][] auditorio = new Hampon[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String nombre =  nombresDeHampones[random.nextInt(nombresDeHampones.length)];
                int dinero = random.nextInt(100000)+ 1;
                int edad = random.nextInt(60) + 18;
                auditorio[i][j] = new Hampon(edad,dinero,nombre);
            }
        }
        return auditorio;
    }
    
    ///MEJOR CASO
    
        public Hampon[] generarListaDatosMayorAMenor(int cantidad){
                Hampon[] lista = new Hampon[cantidad];
                int sub = cantidad;
                for(int i = cantidad - 1; i >= 0; i--){
                        int edad = sub+18; 
                        int dinero = cantidad*sub + 100;
                        String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
                        lista[i] = new Hampon(edad,dinero,nombre);
                        if(sub % 4 == 0){
                                sub--;
                    } 
                }
                return lista;    
        }
    
    public Hampon[] generarListaDatosMenorAMayor(int cantidad){
        Hampon[] lista = new Hampon[cantidad];
        int add = 0;
        for(int i = 0; i < cantidad; i++){
            int edad = add + 18;
            int dinero = cantidad*add + 10;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad,dinero,nombre);
            if(i % 4 == 0){
                add++;
            }
        }
        return lista;
    }
}