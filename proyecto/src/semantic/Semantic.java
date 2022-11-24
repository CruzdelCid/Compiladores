package semantic;

import parser.Nodo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Stack;

public class Semantic {
    private Boolean erroresUnicidad = false; 
    //ATRIBUTOS//
    private Nodo padre;
    private int idScope;
    //Scope ID's 
    private Stack<Integer> bloques;
    //tabla simbolica 
    private Simbolo tabla; 
    //tabla de doble declaracion
    private Simbolo tablaDuplicidad; 
    //tabla de errores 
    private ErrorR tablaErrores;
    //tabla de errores 
    private TablaParametros tablaParams;
    //Tipo de declaracion
    private String tipo; 
    //debug
    private Boolean debu;
    //Stack 
    //Declaracion de Variable
    private Boolean declaracion; 
    private Boolean field_dcl; 
    private Boolean method_dcl; 
    private Boolean method_dclared;
    private Integer last; 


    public Stack<String> operacionStack; 
    public Stack<String> typeStack;
    public String tipo_var; 



    //CONSTRUCTOR//  
    public Semantic(Nodo raiz){
        this.padre = raiz;
    }

    public void run(){

        System.out.println("ANÁLISIS DE UNICIDAD");
        this.createSimbolTable(false); //Ejercutamos el recorrido
        this.printErrores();

        if (this.erroresUnicidad){
            System.out.println("\nERROR: El programa no cumple con unidad.\n");
            System.out.println("\nstopping: semantic analysis\n");
            System.exit(1);
        }
        else {
            System.out.println("\nSUCCESS: Análsis de unicicidad hecho correctamente.\n");
        }


        // analisis de tipos 

        System.out.println("ANÁLISIS DE TIPOS");
        this.analisisTipos();
        System.out.println("\nSUCCESS: Análsis de tipos realizado correctamente.\n");
    }


    public void debug(){
        System.out.println("ANÁLISIS DE UNICIDAD");
        this.createSimbolTable(true); //Ejercutamos el recorrido
        this.printErrores();

        if (this.erroresUnicidad){
            System.out.println("\nERROR: El programa no cumple con unidad.\n");
            System.out.println("\nstopping: semantic analysis\n");
            System.exit(1);
        }
        else {
            System.out.println("\nSUCCESS: Análsis de unicicidad hecho correctamente.\n");
        }

        // analisis de tipos
        System.out.println("ANÁLISIS DE TIPOS");
    }
    

    // Analsis de tipos 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //METODOS//

    /*
     * createSimbolTable manda el nodo padre al metodo recorrer
     */
    public void analisisTipos(){
        //seteo inicial de variables 
        this.bloques = new Stack<Integer>(); 
        this.tabla = new Simbolo(); 
        this.tablaDuplicidad = new Simbolo(); 
        this.tablaErrores = new ErrorR(); 
        this.tablaParams = new TablaParametros(); 
        this.tipo = "";
        this.idScope = 0;

        // seteo de banderas 
        this.declaracion = false; 
        this.field_dcl = false; 
        this.method_dcl = false; 
        this.method_dclared = false;
        this.last = 0;  
        this.operacionStack = new Stack<String>(); 
        this.typeStack = new Stack<String>();
    

        this.recurrer1(this.padre); 
    }


    /*
     * recurrer recorrer los nodos y los manda al scanerNodos
     * iterador del arbol de nodos 
     */
    private int recurrer1(Nodo raiz){

        // Analiza los nodos
        this.scanerNodos1(raiz);

        ArrayList<Nodo> nodos = raiz.getHijos();

        // busqueda recursiva
        for (Nodo i : nodos){
            if(i!=null){
                recurrer1(i); 
            }
        }
        return 1;
    }


    /*
     * verfica el nombre y crea la tabla de signos  
     */
    private int scanerNodos1(Nodo raiz){

        String nombre = raiz.getNombre();
        int location1 = raiz.getLocation(); 

        // VERIFICACION DE FIELD DECLARATION 
        if(nombre.equals("FIELD_DECL")){
            this.field_dcl = true;
        }

        // convierte una variable a tipo vector añadiendole size 
        else if(this.field_dcl && nombre.equals("DecimalLiteral")){
            int size = Integer.parseInt(raiz.getValor());
            int method = 0; 
            this.tabla.toMethodVector(last, method, size);
        }
        // convierte una variable a tipo vector añadiendole size 
        else if(this.field_dcl && nombre.equals("HexLiteral")){
            int size = Integer.parseInt(raiz.getValor(), 16);
            int method = 0; 
            this.tabla.toMethodVector(last, method, size);
        }

        
        // VERIFICACION DE METHOD DECLARATION 
        else if(nombre.equals("METHOD_DECL")){
            this.method_dcl = true;
        }

        else if(this.method_dcl && nombre.equals("LeftParent")){
            this.idScope += 1; 
            this.bloques.add(idScope); 

            this.method_dcl = false;
            this.method_dclared = true;  // bandera  para no crear un nuevo scope 
        }

        else if(this.method_dcl && nombre.equals("ParentOpenClose")){
            this.declaracion = false; 
            this.field_dcl = false; 
            this.method_dcl = false; 
        }

        // se bajan todas las banderas 
        else if(this.method_dclared && nombre.equals("LeftKey")){
            this.declaracion = false; 
            this.field_dcl = false; 
            this.method_dclared = false;  
        }

        // VERIFICACION DEL RESTO DE BLOQUES
        // creacion de nuevo scope
        else if(nombre.equals("LeftKey")){
            this.idScope += 1; 
            this.bloques.add(idScope); 
            this.declaracion = false;
        }
        else if(nombre.equals("SemiColom")){
            this.declaracion = false;
            this.field_dcl = false; 


            // debemos añadir los errores
            if(this.typeStack.size() > 0){
                System.out.println("Existen errores de tipos");
                System.exit(1);
            }
        }

        else if(nombre.equals("Void") || 
            nombre.equals("Int") || 
            nombre.equals("Boolean")){
            
            this.declaracion = true; 
            this.tipo = raiz.getValor(); 

        }

        // Agregar a la tabla signos (tabla simbolica) una nueva declaracion
        else if(nombre.equals("Id") && (declaracion==true)){
            int id = raiz.getNumNodo(); 
            String identifier = raiz.getIdentifier();
            String type = this.tipo; 
            int location = raiz.getLocation(); 
            int scope = this.bloques.peek(); 

            // verifica que la variable no esté creada en este scope
            if(this.tabla.containsSimbolScope(identifier, scope)){
                this.tablaErrores.addError(id, identifier, scope, location);

                
            }
            else{
                this.tabla.addSimbol(id, identifier, type, scope, location, 0, 0);
                
                if(this.method_dclared){
                    this.tablaParams.newFila(id, this.last, identifier, type);
                }
            }

            if(this.field_dcl || this.method_dcl){
                this.last = id;
            }

            if(this.method_dcl){
                // convierte el ultimo elemento agregado en methodo
                this.tabla.toMethodVector(id, 1, 0);
            }
        }
        



        //-----------------------------------------------------------------------------------------------------------------------        
        
        // Verifica si la variable fue declarada antes de ser usada
        else if(raiz.getNombre().equals("Id") && (declaracion==false)){
            int id = raiz.getNumNodo(); 
            String identifier = raiz.getIdentifier();

            // obtenemos su tipo de la tabla de signos 
            String type = this.tabla.getTypeSimbol(identifier); 
            Integer funcion = this.tabla.isFunction(identifier);
            
            this.tipo_var = type; 

            
            // Eliminación en el stack

            System.out.println(raiz.getNombre());
            System.out.println(raiz.getIdentifier());
            System.out.println(raiz.getLocation());
            System.out.println(raiz.getNumNodo());
            System.out.println(raiz.getValor());
            this.eliminacionStack(this.tipo_var, location1);
            



            // Añadicion de la funcion en el stack
            if (funcion == 1){
                this.tablaParams.ponerParams(operacionStack, typeStack, id);
                System.out.println("Se añadieron más tipos a la tabla");
            }
        }







        // operaciones de asignación
        else if(nombre.equals("Asign")){
            this.operacionStack.add("Asign"); 
            this.typeStack.add(this.tipo_var); 
        }
        else if(nombre.equals("AsignAdd")){
            if(this.tipo_var.equals("int")){
                this.operacionStack.add("AsignAdd"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La asginacion += no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("AsignSubs")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("AsignSubs"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La asginacion -= no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }


        // operecion es arimeticas
        else if(nombre.equals("Add")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Add"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La suma no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Substract")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Substract"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La resta no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Multiplication")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Multiplication"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La multiplicacion no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("Division")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Division"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La división no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Mod")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Mod"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La operacion mod no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }

        // operaciones con numeros
        else if(this.field_dcl == false && nombre.equals("INT_LITERAL")){  
            
            System.out.println(raiz.getNombre());
            System.out.println(raiz.getIdentifier());
            System.out.println(raiz.getLocation());
            System.out.println(raiz.getNumNodo());
            System.out.println(raiz.getValor());
            this.eliminacionStack("Int", location1);
        }
        else if(this.field_dcl == false && nombre.equals("BOOL_LITERAL")){
            //this.eliminacionStack("boolean", location1);
        }

        //operaciones condicionales 
        else if(nombre.equals("LessThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("LessThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion LessThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("GreaterThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("GreaterThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion GreaterThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("LessEqualThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("LessEqualThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion LessEqualThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("GreaterEqualThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("GreaterEqualThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion GreaterEqualThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Equal")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("GreaterEqualThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion GreaterEqualThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("NotEqual")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("NotEqual"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion NotEqual no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }
        
        // casos especiales 
        else if(nombre.equals("And")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("And"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion And no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("Or")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Or"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("ERROR: La comparacion Or no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea:" + location1);
                System.exit(1); 
            }
        }





        //-------------------------------------------------------------------------------------------------------------------------

        // Eliminacion de un Scope 
        else if(nombre.equals("RightKey")){
            
            int scope = this.bloques.peek(); 
            this.declaracion = false;


            this.tabla.popScope(scope);
            this.bloques.pop();

        }
        return 0; 
    }

    public void eliminacionStack(String tipo, int location){
        System.out.println(this.typeStack);
        if (this.typeStack.size() > 0 && this.typeStack.peek() == this.tipo_var){
            this.operacionStack.pop();
            this.typeStack.pop();

            System.out.println("Cumple!");
        }
        else if (this.typeStack.size() == 0){
            // nada 
        }
        else{
            System.out.println("No cumple! algrannnnnnn:(");
            System.out.println("Línea: " + location);
            System.exit(1); 
            // ver distintos tipos de erores 
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    // Analsis de unicidad 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void printDuplicidad(){
        tablaDuplicidad.printTable();
    }

    public void printErrores(){
        tablaErrores.printTable();
    }

    

    public void printScopes(int scope){
        if (this.debu){
            System.out.println("Vista Scope: " + scope);
            this.tabla.printTable();
        }   
    }

    public void printParams(int method){
        if (this.debu){
            this.tablaParams.printGetParameters(this.last);
        }
    }
    
    //METODOS//

    /*
     * createSimbolTable manda el nodo padre al metodo recorrer
     */
    public void createSimbolTable(Boolean debuging){
        //seteo inicial de variables 
        this.bloques = new Stack<Integer>(); 
        this.tabla = new Simbolo(); 
        this.tablaDuplicidad = new Simbolo(); 
        this.tablaErrores = new ErrorR(); 
        this.tablaParams = new TablaParametros(); 
        this.tipo = "";
        this.idScope = 0;

        // seteo de banderas 
        this.declaracion = false; 
        this.field_dcl = false; 
        this.method_dcl = false; 
        this.method_dclared = false;
        this.last = 0;  

        this.debu = debuging; // Cuando es verdaro hace que se impriman las tablas
        this.recurrer(this.padre); 
    }


    /*
     * recurrer recorrer los nodos y los manda al scanerNodos
     * iterador del arbol de nodos 
     */
    private int recurrer(Nodo raiz){

        // Analiza los nodos
        this.scanerNodos(raiz);

        ArrayList<Nodo> nodos = raiz.getHijos();

        // busqueda recursiva
        for (Nodo i : nodos){
            if(i!=null){
                recurrer(i); 
            }
        }

        return 1;
    }


    /*
     * verfica el nombre y crea la tabla de signos  
     */
    private int scanerNodos(Nodo raiz){

        String nombre = raiz.getNombre();

        // VERIFICACION DE FIELD DECLARATION 
        if(nombre.equals("FIELD_DECL")){
            this.field_dcl = true;
        }

        // convierte una variable a tipo vector añadiendole size 
        else if(this.field_dcl && nombre.equals("DecimalLiteral")){
            int size = Integer.parseInt(raiz.getValor());
            int method = 0; 
            this.tabla.toMethodVector(last, method, size);
        }
        // convierte una variable a tipo vector añadiendole size 
        else if(this.field_dcl && nombre.equals("HexLiteral")){
            int size = Integer.parseInt(raiz.getValor(), 16);
            int method = 0; 
            this.tabla.toMethodVector(last, method, size);
        }

        
        // VERIFICACION DE METHOD DECLARATION 
        else if(nombre.equals("METHOD_DECL")){
            this.method_dcl = true;
        }

        else if(this.method_dcl && nombre.equals("LeftParent")){
            this.idScope += 1; 
            this.bloques.add(idScope); 

            this.method_dcl = false;
            this.method_dclared = true;  // bandera  para no crear un nuevo scope 
        }

        else if(this.method_dcl && nombre.equals("ParentOpenClose")){
            this.declaracion = false; 
            this.field_dcl = false; 
            this.method_dcl = false; 
        }

        // se bajan todas las banderas 
        else if(this.method_dclared && nombre.equals("LeftKey")){
            this.printParams(last); // se imprimen los parametros de esa funcion
            this.declaracion = false; 
            this.field_dcl = false; 
            this.method_dclared = false;  
        }

        // VERIFICACION DEL RESTO DE BLOQUES
        // creacion de nuevo scope
        else if(nombre.equals("LeftKey")){
            this.idScope += 1; 
            this.bloques.add(idScope); 
            this.declaracion = false;
        }
        else if(nombre.equals("SemiColom")){
            this.declaracion = false;
            this.field_dcl = false; 
        }

        else if(nombre.equals("Void") || 
            nombre.equals("Int") || 
            nombre.equals("Boolean")){
            
            this.declaracion = true; 
            this.tipo = raiz.getValor(); 

        }

        // Agregar a la tabla signos (tabla simbolica) una nueva declaracion
        else if(nombre.equals("Id") && (declaracion==true)){
            int id = raiz.getNumNodo(); 
            String identifier = raiz.getIdentifier();
            String type = this.tipo; 
            int location = raiz.getLocation(); 
            int scope = this.bloques.peek(); 

            // verifica que la variable no esté creada en este scope
            if(this.tabla.containsSimbolScope(identifier, scope)){
                //System.out.println("Doble declaracion de variables: " + identifier);
                this.tablaErrores.addError(id, identifier, scope, location);
                this.erroresUnicidad = true;
            }
            else{
                this.tabla.addSimbol(id, identifier, type, scope, location, 0, 0);
                
                if(this.method_dclared){
                    this.tablaParams.newFila(id, this.last, identifier, type);
                }
            }

            if(this.field_dcl || this.method_dcl){
                this.last = id;
            }

            if(this.method_dcl){
                // convierte el ultimo elemento agregado en methodo
                this.tabla.toMethodVector(id, 1, 0);
            }
        }
        
        // Verifica si la variable fue declarada antes de ser usada
        else if(raiz.getNombre().equals("Id") && (declaracion==false)){
            int id = raiz.getNumNodo(); 
            String identifier = raiz.getIdentifier();
            //String type = this.tipo; 
            int location = raiz.getLocation(); 
            int scope = this.bloques.peek(); 

            if(tabla.containsSimbol(identifier)){
                //
            }
            else{
                System.out.println("La variable no existe: " + identifier);
                this.tablaErrores.addError(id, identifier, scope, location);
                this.erroresUnicidad = true;
            }
        }

        // Eliminacion de un Scope 
        else if(nombre.equals("RightKey")){
            
            int scope = this.bloques.peek(); 
            this.declaracion = false;

            this.printScopes(scope); 

            this.tabla.popScope(scope);
            this.bloques.pop();

        }
        return 0; 
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}







