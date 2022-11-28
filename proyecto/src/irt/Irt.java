package irt;

import java.util.ArrayList;
import java.util.Stack;

import parser.Nodo;

public class Irt {
    // Nodo padre para la generacion
    public Nodo padre;
    public ArrayList<NodoIrt> irt_list;
    public String letra= "a";
    public int numero = 0;
    public String motivo; 

    // Para scopes
    Stack<Integer> scopes;
    int idScopes; 
    Stack<String> motivos; 

    // Para field declaration y var decl
    public Boolean field; 
    public int id; 
    public String name; 
    public String type;
    public int size;
    public int param_position;

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

        // inicializacion de tablas
        this.irt_list = new ArrayList<NodoIrt>(); 
        this.scopes = new Stack<Integer>(); 
        this.motivos = new Stack<String>(); 

        this.motivo = "Creacion"; 

        this.idScopes = 0; 
        this.recorrer(this.padre);
    }

    public void debug(){
        this.run(); 
    }


    public String generador(){
        String nueva = this.letra + this.numero + "_"; 
        
        this.numero += 1; 

        return nueva;
    }


    public int recorrer(Nodo padre){

        String name = padre.getNombre();

        if(name.equals("Program")){
            NodoIrt nodo =  new NodoIrt(); 

            nodo.operacion = "CrearMain";
            nodo.etiqueta_method = "main"; 

            this.irt_list.add(nodo);
        }
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
            
            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "FieldDecl";
            nodo.nombre = this.name; 
            nodo.field = 1;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 

            this.irt_list.add(nodo);
            
        }
        else if(this.field && name.equals("SemiColom")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 1);
            this.field = false; // ¡IMPORTANTE!: se apaga la bandera

            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "FieldDecl";
            nodo.nombre = this.name; 
            nodo.field = 1;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 

            this.irt_list.add(nodo);
        }

        // creacion de un método
        else if(name.equals("METHOD_DECL")){
            this.method = true;
            this.param_position = 0; 
            this.motivo = "Metodo"; 
        }
        else if(this.method && (name.equals("Int") || name.equals("Boolean") || name.equals("Void"))){
            this.type_method = padre.getValor(); 
        }
        else if(this.method && name.equals("Id")){
            this.name_method= padre.getValor(); 
            this.id_method = padre.getNumNodo();

            this.tabla_meth.nuevaFila(this.id_method, this.name_method, this.type_method);
            this.method = false; // ¡IMPORTANTE!: se apaga la bandera
            
            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "MethodDecl";
            nodo.etiqueta_method = this.name_method; 

            this.irt_list.add(nodo);

            // se agrega un nuevo scope 
            this.idScopes += 1; 


            
            this.m_decl = true;
            this.scopes.add(this.idScopes); 
            this.motivos.add(this.motivo); 
        }


        // creacion de una variable de método 
        else if(name.equals("M_DECL")){
            
            
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
            this.tabla_param.nuevaFila(this.param_position, this.name, this.name_method, this.type);

            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "VarDecl";
            nodo.nombre = this.name; 
            nodo.field = 0;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 
            this.irt_list.add(nodo);


            NodoIrt nodo1 =  new NodoIrt();
            nodo1.operacion = "ParamDecl";
            nodo1.param_position = this.param_position; 
            nodo1.param_name = this.name; 
            nodo1.name_method = this.name_method; 
            this.irt_list.add(nodo1);
            this.param_position += 1; 
        }
        else if(this.m_decl && name.equals("LeftKey")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

            // creacion de nuevo parametro en la tabla de parametros
            this.tabla_param.nuevaFila(this.param_position, this.name, this.name_method, this.type);

            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "VarDecl";
            nodo.nombre = this.name; 
            nodo.field = 0;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 
            this.irt_list.add(nodo);


            NodoIrt nodo1 =  new NodoIrt();
            nodo1.operacion = "ParamDecl";
            nodo1.param_position = this.param_position; 
            nodo1.param_name = this.name; 
            nodo1.name_method = this.name_method; 
            this.irt_list.add(nodo1);

            this.m_decl = false;
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

            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "VarDecl";
            nodo.nombre = this.name; 
            nodo.field = 0;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 
            this.irt_list.add(nodo);

        }
        else if(this.var_decl && name.equals("SemiColom")){
            // creamos un nuevo elemento en la tabla de simbolos 
            int scope = this.scopes.peek();
            this.tabla_sim.nuevaFila(this.id, this.name, this.type, scope, this.size, 0);

            this.var_decl = false; // ¡IMPORTANTE!: se apaga la bandera

            NodoIrt nodo =  new NodoIrt(); 
            nodo.operacion = "VarDecl";
            nodo.nombre = this.name; 
            nodo.field = 0;
            nodo.size = this.size;
            nodo.bytes = this.size * 4; 
            nodo.scope = scope; 
            this.irt_list.add(nodo);
        }

        // creacion de un nuevo scope
        else if(this.m_decl == false && name.equals("LeftKey")){
            this.idScopes += 1; 
            this.scopes.add(this.idScopes);
            this.motivos.add(this.motivo); 
        }

        // eliminacion de un scope 
        else if(name.equals("RightKey")){
            int scope = this.scopes.pop();
            String motivo_scope = this.motivos.pop(); 

            System.out.println("\n\nVista Scope: " + scope);
            this.tabla_sim.print(); 
            this.tabla_meth.print(); 
            this.tabla_param.print();


            if(motivo_scope.equals("Metodo")){
                // cantidad de variables eliminadas 
                this.tabla_sim.pullScope(scope);

                NodoIrt nodo = new NodoIrt(); 
                nodo.operacion = "MethodFin";
                this.irt_list.add(nodo); 
                // nodo.size = tamano; 
                // nodo.bytes = tamano * 4; 

            }
            else if(motivo_scope.equals("Creacion")){
                NodoIrt nodo = new NodoIrt(); 
                nodo.operacion = "CreacionFin";
                this.irt_list.add(nodo); 
                this.tabla_sim.pullScope(scope);
            } 
            else {
                this.tabla_sim.pullScope(scope);
            }


             

            
        }


        
        // System.out.println(this.irt_list);

        // recorrido de los hijos; 
        ArrayList<Nodo> hijos = padre.getHijos();
        for (Nodo hijo : hijos){
            if(hijo!=null){
                recorrer(hijo); 
            }
        }


        // formas recursivas 
        if(this.field == false && name.equals("DecimalLiteral")){
            NodoIrt nodo = new NodoIrt(); 
            nodo.operacion = "DecimalLiteral";
            nodo.var_store = this.generador(); 
            nodo.value = Integer.parseInt(padre.getValor());

            this.irt_list.add(nodo);
            padre.resultado = nodo.var_store; 
        }
        else if (this.field == false && name.equals("INT_LITERAL")){
            Nodo hijo = padre.getHijos().get(0); 

            padre.resultado = hijo.resultado; 
        }
        else if (name.equals("LITERAL")){
            Nodo hijo = padre.getHijos().get(0); 

            padre.resultado = hijo.resultado;
        }
        else if (name.equals("EXPR")){
            int tamano = padre.getHijos().size(); 

            if (tamano == 1){
                Nodo hijo = padre.getHijos().get(0); 
                padre.resultado = hijo.resultado;
            }
            if (tamano == 3){
                if(hijos.get(1).getNombre().equals("EXPR")){
                    padre.resultado = hijos.get(1).resultado; 
                }
                else {
                    String var1 = hijos.get(0).resultado; 
                    String operacion = hijos.get(1).resultado;
                    String var2 = hijos.get(2).resultado; 
                    String var_store = this.generador(); 
                    
                    NodoIrt nodo = new NodoIrt(); 
                    nodo.operacion = operacion;
                    nodo.var1 = var1; 
                    nodo.var2 = var2;
                    nodo.var_store = var_store; 

                    this.irt_list.add(nodo);

                    padre.resultado = var_store; 
                }
            }
        }
        else if (name.equals("LOCATION")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getValor(); 
        }
        else if (name.equals("ASSIGN_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getNombre(); 
        }
        else if (name.equals("STATEMENT")){
            int tamano = hijos.size(); 

            if (tamano == 4){
                String var_salida = hijos.get(0).resultado; 
                String operacion = hijos.get(1).resultado;
                String var_entrada = hijos.get(2).resultado; 
                
                NodoIrt nodo = new NodoIrt(); 
                nodo.operacion = operacion;
                nodo.var_salida = var_salida; 
                nodo.var_entrada = var_entrada; 

                this.irt_list.add(nodo);

                // LiberarTemp 
                NodoIrt nodo1 = new NodoIrt(); 
                nodo1.operacion = "LiberarTemp";
                this.irt_list.add(nodo1);
            }
        }
        else if (name.equals("BIN_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.resultado; 
        }
        else if (name.equals("ARITH_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getNombre(); 
        }
        else if (name.equals("REL_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getNombre(); 
        }
        else if (name.equals("EQ_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getNombre(); 
        }
        else if (name.equals("COND_OP")){
            Nodo hijo = padre.getHijos().get(0); 
            padre.resultado = hijo.getNombre(); 
        }
        

        return 1; 
    } 

    public ArrayList<NodoIrt> getIrt(){
        return this.irt_list; 
    }

    public void print(){
        for(NodoIrt i: this.irt_list){
            System.out.println("-----------------");
            if(i.operacion.equals("Asign")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("var_entrada: " + i.var_entrada);
                System.out.println("var_salida: " + i.var_salida);
            }
            else if(i.operacion.equals("Add") ||
                    i.operacion.equals("Substract") ||
                    i.operacion.equals("Multiplication") ||
                    i.operacion.equals("Division") ||
                    i.operacion.equals("LessThan") ||
                    i.operacion.equals("GreaterThan") ||
                    i.operacion.equals("LessEqualThan") ||
                    i.operacion.equals("GreaterEqualThan") ||
                    i.operacion.equals("Equal") ||
                    i.operacion.equals("NotEqual") ||
                    i.operacion.equals("And") ||
                    i.operacion.equals("Or")
            ){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("var1: " + i.var1);
                System.out.println("var2: " + i.var2);
                System.out.println("var_store: " + i.var_store);
            }
            else if(i.operacion.equals("CrearMain")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("etiqueta: " + i.etiqueta_method);
            }
            else if(i.operacion.equals("FieldDecl")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("nombre: " + i.nombre);
                System.out.println("field: " + i.field);
                System.out.println("size: " + i.size);
                System.out.println("bytes: " + i.bytes);
                System.out.println("scope: " + i.scope);
            }
            else if(i.operacion.equals("MethodDecl")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("etiqueta: " + i.etiqueta_method);
            }
            else if(i.operacion.equals("VarDecl")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("nombre: " + i.nombre);
                System.out.println("field: " + i.field);
                System.out.println("size: " + i.size);
                System.out.println("bytes: " + i.bytes);
                System.out.println("scope: " + i.scope);
            }
            else if(i.operacion.equals("ParamDecl")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("param_position: " + i.param_position);
                System.out.println("param_name: " + i.param_name);
                System.out.println("name_method: " + i.name_method);
            } 
            else if(i.operacion.equals("DecimalLiteral")){
                System.out.println("Operacion: " + i.operacion);
                System.out.println("var_store: " + i.var_store);
                System.out.println("value: " + i.value);
            }
            else {
                System.out.println("Operacion: " + i.operacion);
            }
            System.out.println("-----------------\n");
        }
    }
}
