package irt;

// informacion de fila de la tabla de de parametros 
public class Fila_p {
    public int param_position; 
    public String var_name; 
    public String name; 
    public String type;

    public Fila_p (int param_position, String var_name, String name, String type){
        this.param_position = param_position; 
        this.var_name = var_name; 
        this.name = name; 
        this.type = type;
    }
}
