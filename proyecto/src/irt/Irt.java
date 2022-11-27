package irt;

import java.util.ArrayList;
import java.util.Stack;

import parser.Nodo;

public class Irt {
    // Nodo padre para la generacion
    public Nodo padre;

    // Para scopes
    Stack<Integer> scopes;
    int idScopes; 

    // Para field declaration y var decl
    public Boolean field; 
    public int id; 
    public String name; 
    public String type;
    public int size;

    // Para declaration de metodo
    public Boolean method;
    public Boolean m_decl; 
    public int id_method; 
    public String name_method; 
    public String type_method; 

    // Para declaracion de variables 
    public Boolean var_decl; 


    // Tabla de simbolos 
    public Tabla_s tabla_sim; 

    // Tabla de metodos 
    public Tabla_m tabla_meth; 

    // Tabla de parametros
    public Tabla_p tabla_param; 


    // contructor con el nodo padre    
    public Irt(Nodo raiz){
        this.padre = raiz; 
    }

    public void run(){
        // banderas
        this.field = false; 
        this.method = false;
        this.m_decl = false; 
        this.var_decl = false; 

        // incializacion tablas
        this.tabla_sim = new Tabla_s(); 
        this.tabla_meth = new Tabla_m(); 
        this.tabla_param = new Tabla_p(); 

        this.scopes = new Stack<Integer>(); 
        this.idScopes = 0; 
        this.recorrer(this.padre);
    }

    public void debug(){
        this.run(); 
    }


    public int recorrer(Nodo padre){
        String name = padre.getNombre();


        // creación de un field declaration
        if(name.equals("FIELD_DECL")){
            this.field = true;
        }
        else if(this.field && (name.equals("Int") || name.equals("Boolean"))){
            this.type = padre.getValor(); 
        }
        else if(this.field && name.equals("Id")){
            this.name = padre.getValor();
            this.id = padre.getNumNodo(); 
            this.size = 1; 
        }
        else if(this.field && name.equals("DecimalLiteral")){
            this.size = Integer.parseInt(padre.getValor()); 
        }
        else if(this.field && name.equals("Comma")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 1);
        }
        else if(this.field && name.equals("SemiColom")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 1);
            this.field = false; // ¡IMPORTANTE!: se apaga la bandera
        }

        // creacion de un método
        else if(name.equals("METHOD_DECL")){
            this.method = true;
        }
        else if(this.method && (name.equals("Int") || name.equals("Boolean") || name.equals("Void"))){
            this.type_method = padre.getValor(); 
        }
        else if(this.method && name.equals("Id")){
            this.name_method= padre.getValor(); 
            this.id_method = padre.getNumNodo();

            this.tabla_meth.nuevaFila(this.id_method, this.name_method, this.type_method);
            this.method = false; // ¡IMPORTANTE!: se apaga la bandera
        }


        // creacion de una variable de método 
        else if(name.equals("M_DECL")){
            this.m_decl = true;
            // se agrega un nuevo scope 
            this.idScopes += 1; 
            this.scopes.add(this.idScopes); 
        }
        else if(this.m_decl && (name.equals("Int") || name.equals("Boolean"))){
            this.type = padre.getValor(); 
        }
        else if(this.m_decl && name.equals("Id")){
            this.name = padre.getValor();
            this.id = padre.getNumNodo(); 
            this.size = 1; 
        }
        else if(this.m_decl && name.equals("Comma")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

            // creacion de nuevo parametro en la tabla de parametros
            this.tabla_param.nuevaFila(this.name_method, this.type);
        }
        else if(this.m_decl && name.equals("LeftKey")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

            // creacion de nuevo parametro en la tabla de parametros
            this.tabla_param.nuevaFila(this.name_method, this.type);

            this.m_decl = false; // ¡IMPORTANTE!: se apaga la bandera
        }


        
        // creacion de una variable metodo
        else if(name.equals("VAR_DECL")){
            this.var_decl = true;
        }
        else if(this.var_decl && (name.equals("Int") || name.equals("Boolean"))){
            this.type = padre.getValor(); 
        }
        else if(this.var_decl && name.equals("Id")){
            this.name = padre.getValor();
            this.id = padre.getNumNodo(); 
            this.size = 1; 
        }
        else if(this.var_decl && name.equals("Comma")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

        }
        else if(this.var_decl && name.equals("SemiColom")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

            this.var_decl = false; // ¡IMPORTANTE!: se apaga la bandera
        }

        // creacion de un nuevo scope
        else if(name.equals("LeftKey")){
            this.idScopes += 1; 
            this.scopes.add(this.idScopes);
        }

        // eliminacion de un scope 
        else if(name.equals("RightKey")){
            int scope = this.scopes.pop(); 
            System.out.println("\n\nVista Scope: " + scope);
            //this.tabla_sim.print(); 
            //this.tabla_meth.print(); 
            //this.tabla_param.print(); 

            this.tabla_sim.pullScope(scope);
        }


        

        // antes
        // para generar la tabla de simbolos
        // System.out.println("--> Primero");
        // System.out.println("Numero: " + padre.getNumNodo());
        // System.out.println("Nombre: " + padre.getNombre());
        // System.out.println("Identificador: " + padre.getIdentifier());
        // System.out.println("Location: " + padre.getLocation());
        // System.out.println("Valor: " + padre.getValor());
        // System.out.println("");
        

        ArrayList<Nodo> hijos = padre.getHijos();

        for (Nodo hijo : hijos){
            if(hijo!=null){
                recorrer(hijo); 
            }
        }

        // System.out.println("--> Segundo");
        // System.out.println("Numero: " + padre.getNumNodo());
        // System.out.println("Nombre: " + padre.getNombre());
        // System.out.println("Identificador: " + padre.getIdentifier());
        // System.out.println("Location: " + padre.getLocation());
        // System.out.println("Valor: " + padre.getValor());
        // System.out.println("");

        // y después
        // para generar el ist 

        return 1; 
    } 
}
