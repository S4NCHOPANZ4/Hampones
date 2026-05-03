package co.ciencias.hampones.model;

public class Auditorio {
    
    private Hampon[][] asientos;
    private int filas;
    private int cols;
    
    public Auditorio(int i, int j){
        this.filas = i;
        this.cols = j;
        asientos = new Hampon[filas][cols];
    }
    public void borrarElementos(){
        for(int i = 0; i < filas; i++){
            for(int j = 0; j < cols; j++){
                asientos[i][j] = null;
            }
        }
    } 
    public void setSillas(Hampon[][] asientos){
        this.filas = asientos.length;
        if(asientos.length > 0){
            this.cols = asientos[0].length;
        }else{
        this.cols = 0;
        }
        this.asientos = asientos;
    }
    public Hampon[][] getSillas(){
        return asientos;
    }
    public int getFilas(){
        return filas;
    }
    public int getCols(){
        return cols;
    }
    
    
}