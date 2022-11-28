package irt;

import java.util.ArrayList;

public class Tabla_s {
    private ArrayList<Fila_s> info;  

    public Tabla_s(){
        this.info = new ArrayList<Fila_s>();   
    }

    public void nuevaFila(int id, String name, String type, int scope, int size, int field){
        Fila_s fila = new Fila_s(id, name, type, scope, size, field); 
        this.info.add(fila);
    }

    public Fila_s lookUp(String name){
        Fila_s fila = null;
        int end = this.info.size() - 1; 

        for(int i = end; i >= 0; i--){
            fila = info.get(i);
            
            if(fila.name.equals(name)){
                return fila; 
            }
        }
        return fila;
    }

    public int pullScope(int scope){
        int end = this.info.size() - 1; 
        int cantidad = 0;

        for(int i = end; i >= 0; i--){
            Fila_s fila = info.get(i);
            
            if(fila.scope == scope){
                this.info.remove(i);
                cantidad += 1; 
            }
            else{
                break; 
            }
        }
        System.out.println("Scope <<" + scope + ">> eliminando.");

        return cantidad; 
    }

    public void print(){
        int end = this.info.size(); 
        System.out.println("TABLA DE SIMBOLOS");
        System.out.format("%3s %7s %7s %5s %5s %5s\n","Id", "Name", "Type", "Scope", "Size", "Field");
        System.out.println("-------------------------------");
        for(int i = 0; i < end; i ++){
            Fila_s fila = info.get(i);
            System.out.format("%3d %7s %7s %5d %5d %5d\n", fila.id, fila.name, fila.type, fila.scope, fila.size, fila.field);
        }
        System.out.println("");
    }
}