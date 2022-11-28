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

    // creacion de un if 
    public String var_cond;
    public String etiqueta_if;
    public String etiqueta_else; 
    public String etiqueta_endif;

    // creacion de method call variables
    public String var_call;
    public int position_call; 

    // creacion method call 
    public String etiqueta_call; 
    public int cantidad_params_call;
    public String var_result;  


    // 

    @Override
    public String toString() {
        return this.operacion;
    }
}
