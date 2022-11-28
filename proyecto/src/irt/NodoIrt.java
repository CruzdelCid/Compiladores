package irt;

public class NodoIrt {
    public String operacion;

    // declaracion de variables
    public String nombre;
    public int field;
    public int size;
    public int bytes; 
    public int scope;

    // operacion DecimalLiteral
    public int value; 

    // operacion artih y condicionales
    public String var1;
    public String var2;
    public String var_store;

    // declaracion de un methodo
    public String etiqueta_method;

    // creacion de nuevo scope 
    public int scopeid; 

    // creacion de parametros
    public int param_position; 
    public String param_name;
    public String name_method; 

    // asginacion 
    public String var_entrada; 
    public String var_salida; 


    // eliminacion de un metodo; 


    // 

    @Override
    public String toString() {
        return this.operacion;
    }
}
