package irt;

import java.util.ArrayList;

public class Tabla_m {
    private ArrayList<Fila_m> info;  

    public Tabla_m(){
        this.info = new ArrayList<Fila_m>();   
    }

    public void nuevaFila(int id, String name, String type){
        Fila_m fila = new Fila_m(id, name, type); 
        this.info.add(fila);
    }

    public Fila_m lookUp(String name){
        Fila_m fila = null;
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
        System.out.println("TABLA DE METODOS");
        System.out.format("%3s %7s %7s\n","Id", "Name", "Type");
        System.out.println("--------------------");
        for(int i = 0; i < end; i ++){
            Fila_m fila = info.get(i);
            System.out.format("%3d %7s %7s\n", fila.id, fila.name, fila.type);
            
        }
        System.out.println("");
    }
}