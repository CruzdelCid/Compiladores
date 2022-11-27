package irt;
import java.util.ArrayList;

public class Tabla_p {
    private ArrayList<Fila_p> info;  

    public Tabla_p(){
        this.info = new ArrayList<Fila_p>();   
    }

    public void nuevaFila(String name, String type){
        Fila_p fila = new Fila_p(name, type); 
        this.info.add(fila);
    }

    public Fila_p lookUp(String name){
        Fila_p fila = null;
        int end = this.info.size() - 1; 

        for(int i = end; i >= 0; i--){
            fila = info.get(i);
            
            if(fila.name.equals(name)){
                return fila; 
            }
        }
        return fila;
    }

    public void print(){
        int end = this.info.size(); 
        System.out.println("TABLA DE PARAMETROS");
        System.out.format("%7s %7s\n","Method", "Type");
        System.out.println("----------------");
        for(int i = 0; i < end; i ++){
            Fila_p fila = info.get(i);
            System.out.format("%7s %7s\n", fila.name, fila.type);   
        }
        System.out.println("");
    }
}