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
    
    public Hampon[] generarListaAleatoria(int n){
        Hampon[] lista = new Hampon[n];
        for(int i = 0; i < n; i++){
            int edad = random.nextInt(60) + 18;
            int dinero = random.nextInt(100000)+ 1;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad,dinero,nombre);
        }
        return lista;
    } 
    
    public Hampon[] generarListaDatosMayorAMenor(int n){
        Hampon[] lista = new Hampon[n];
        int sub = n;
        for(int i = n - 1; i >= 0; i--){
            int edad = sub+18; 
            int dinero = n*sub + 100;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad,dinero,nombre);
            if(sub % 4 == 0){
                sub--;
            } 
        }
        return lista;    
    }
    
    public Hampon[] generarListaDatosMenorAMayor(int n){
        Hampon[] lista = new Hampon[n];
        int add = 0;
        for(int i = 0; i < n; i++){
            int edad = add + 18;
            int dinero = n*add + 10;
            String nombre = nombresDeHampones[random.nextInt(nombresDeHampones.length)];
            lista[i] = new Hampon(edad,dinero,nombre);
            if(i % 4 == 0){
                add++;
            }
        }
        return lista;
    }
}