package irt;

// informacion de fila de la tabla de simbolos 
public class Fila_s {
    public int id; 
    public String name;
    public String type; 
    public int scope;
    public int size;
    public int field;

    public Fila_s (int id, String name, String type, int scope, int size, int field){
        this.id = id;
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.size = size; 
        this.field = size; 
    }
}
