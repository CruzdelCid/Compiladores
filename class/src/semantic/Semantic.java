package semantic;

import parser.Nodo;

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
    public String last_tipo; 
    public Boolean returnValid = false;



    //CONSTRUCTOR//  
    public Semantic(Nodo raiz){
        this.padre = raiz;
    }

    public void run(){

        System.out.println("---> ANÁLISIS DE UNICIDAD");
        this.createSimbolTable(false); //Ejercutamos el recorrido
        this.printErrores();

        if (this.erroresUnicidad){
            System.out.println("\n\nERRROR : El programa no cumple con unicidad.\n");
            System.out.println("\nstopping: semantic analysis\n");
            System.exit(1);
        }
        else {
            System.out.println("\nSUCCESS: Análsis de unicidad hecho correctamente.\n");
        }


        // analisis de tipos 

        System.out.println("---> ANÁLISIS DE TIPOS");
        this.analisisTipos();
        System.out.println("\nSUCCESS: Análsis de tipos realizado correctamente.\n");
    }


    public void debug(){
        System.out.println("---> ANÁLISIS DE UNICIDAD");
        this.createSimbolTable(true); //Ejercutamos el recorrido
        this.printErrores();

        if (this.erroresUnicidad){
            System.out.println("\n\nERRROR : El programa no cumple con unicidad.\n");
            System.out.println("\nstopping: semantic analysis\n");
            System.exit(1);
        }
        else {
            System.out.println("\nSUCCESS: Análsis de unicidad hecho correctamente.\n");
        }

        // analisis de tipos
        System.out.println("---> ANÁLISIS DE TIPOS");
        this.analisisTipos();
        System.out.println("\nSUCCESS: Análsis de tipos realizado correctamente.\n");
    }

    public void printStacks(){
        if (this.debu){
            System.out.println("Stack operaciones: " + this.operacionStack);
            System.out.println("Stack type: " + this.typeStack);
        }
        
    }

    public void printElim(String a, String b){
        if (this.debu){
            System.out.println("Se elimino la operacion: " + a);
            System.out.println("Se elimino el tipo: " + b + "\n");
        }
    }
    

    // Analsis de tipos 
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //METODOS//

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
        // System.out.println("Location: " + location1 + " " + nombre);

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

            // llamada de errores
            if(this.typeStack.size() > 0){
                String erro = this.operacionStack.peek(); 
                this.errorElim(erro, location1); 
            }
        }
        else if(nombre.equals("SemiColom")){
            this.declaracion = false;
            this.field_dcl = false; 

            // llamada de errores
            if(this.typeStack.size() > 0){
                this.errorElim("int_value", location1); 
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
        
        // Eliminacion de un Scope 
        else if(nombre.equals("RightKey")){
            
            int scope = this.bloques.peek(); 
            this.declaracion = false;

            if (scope == 1 && this.tabla.isMain() == false){
                System.out.println("\n\nERRROR : NO SE DECLARO LA FUNCION main()");
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }            
            this.tabla.popScope(scope);
            this.bloques.pop();

            if (this.bloques.size() > 0){
                if(this.bloques.peek() == 1){
                    if(this.tabla.isMain() == false){
                        if (this.tabla.isTypeLastFunction().equals("void")){
                            // pasamos sin verificar el return
                            this.returnValid = false;
                        }
                        else if(this.returnValid == true){
                            // Todo bien, sí tiene un return 
                            this.returnValid = false;
                        }
                        else {
                            System.out.println("\n\nERRROR : el metodo <<" + this.tabla.lastFunction() + 
                                                ">> no tiene ningún return, debe tener un return \n" + 
                                                " con un valor de tipo <<" + this.tabla.isTypeLastFunction() + ">>");
                            System.out.println("Linea: " + location1);
                            System.exit(1); 
                        }
                    }
                }
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
            Integer tamano = this.tabla.isVector(identifier); 

            this.tipo_var = type; 

            
            // Eliminación en el stack

            this.eliminacionStack(this.tipo_var, location1);
            
            // Añadicion de la funcion en el stack
            if (funcion == 1){
                this.tablaParams.ponerParams(operacionStack, typeStack, id);
                // System.out.println("Se añadieron más tipos a los stckas de tipo y operacion");
            }

            if (tamano > 0){
                this.operacionStack.add("Vector"); 
                this.typeStack.add(tamano.toString()); 
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
                System.out.println("\n\nERRROR : La asignacion += no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("AsignSubs")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("AsignSubs"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La asignacion -= no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }


        // operecion es aritmeticas
        else if(nombre.equals("Add")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Add"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La suma no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Substract")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Substract"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La resta no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Multiplication")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Multiplication"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La multiplicacion no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("Division")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Division"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La división no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("Mod")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("Mod"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La operacion mod no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }

        // operaciones con numeros
        else if(this.field_dcl == false && nombre.equals("INT_LITERAL")){  
            //System.out.println("INT_LITERAL FOUND");
            this.tipo_var = "Int";
            if (this.operacionStack.size() > 0){
                if (this.operacionStack.peek().equals("For")){
                    System.out.println("\n\nERRROR : La primera expresion del for no puede ser un Int_literal, debe ser una variable de tipo int.");
                    System.out.println("Linea: " + location1);
                    System.exit(1); 
                }
                if (this.operacionStack.peek().equals("Vector")){
                    // dejamos pasar el int_literal
                }
                else{
                    this.eliminacionStack("Int", location1);
                }
            }
        }
        else if(this.field_dcl == false && nombre.equals("BOOL_LITERAL")){
            //System.out.println("BOOL_LITERAL FOUND");
            this.tipo_var = "Boolean";

            if (this.operacionStack.size() > 0){
                if (this.operacionStack.peek().equals("For_cond")){
                    System.out.println("\n\nERRROR : La segunda expresion del for no puede ser un Bool_literal, debe ser una variable de tipo boolean.");
                    System.out.println("Linea: " + location1);
                    System.exit(1); 
                }
                this.eliminacionStack("Boolean", location1);
            }
        }

        // operaciones condicionales 
        else if(nombre.equals("LessThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("LessThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La comparacion LessThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("GreaterThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("GreaterThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La comparacion GreaterThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("LessEqualThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("LessEqualThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La comparacion LessEqualThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }

        }
        else if(nombre.equals("GreaterEqualThan")){
            if(this.tipo_var.equals("Int")){
                this.operacionStack.add("GreaterEqualThan"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La comparacion GreaterEqualThan no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("Equal")){
            this.operacionStack.add("Equal"); 
            this.typeStack.add(this.tipo_var);
        }
        else if(nombre.equals("NotEqual")){
            this.operacionStack.add("NotEqual"); 
            this.typeStack.add(this.tipo_var);
        }
        
        // casos especiales 
        else if(nombre.equals("And")){
            if(this.tipo_var.equals("Boolean")){
                this.operacionStack.add("And"); 
                this.typeStack.add(this.tipo_var);
            } else {
                System.out.println("\n\nERRROR : La condicional And no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("Or")){
            if(this.tipo_var.equals("Boolean")){
                this.operacionStack.add("Or"); 
                this.typeStack.add(this.tipo_var); 
            } else {
                System.out.println("\n\nERRROR : La condicional Or no es posible en variables de tipo "+ this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        else if(nombre.equals("If")){
            this.operacionStack.add("If"); 
            this.typeStack.add("Boolean"); 
            this.tipo_var = "Boolean"; 
        }
        else if(nombre.equals("For")){
            this.operacionStack.add("For"); 
            this.typeStack.add("Int"); 
            this.tipo_var = "Int"; 
        }
        else if(nombre.equals("Exclam")){
            if(this.tipo_var.equals("Boolean")){
                // seguimos normal  
            } else {
                System.out.println("\n\nERRROR : La condicional negada no es posible en variables de tipo " + this.tipo_var);
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
        }
        // verificaion de los return 
        else if(nombre.equals("Return")){
            String return_type = this.tabla.isTypeLastFunction(); 

            if(return_type.equals("NO FUNCTION")){
                System.out.println("\n\nERRROR : No es posibble declarar un return sin declarar un metodo antes.");
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
            
            if (return_type.equals("void")){
                System.out.println("\n\nERRROR: Los metodos de tipo void no pueden llevar return.");
                System.out.println("Linea: " + location1);
                System.exit(1); 
            }
            else{
                this.operacionStack.add("Return"); 
                this.typeStack.add(return_type); 
                this.tipo_var = return_type;
                this.returnValid = true; 
            }
        }


        // verificacion de un vector
        else if(this.typeStack.size() > 0){
            if(this.operacionStack.peek().equals("Vector")){
                if(nombre.equals("DecimalLiteral")){
                    int tamano = Integer.parseInt(this.typeStack.peek()); 
                    int valor = Integer.parseInt(raiz.getValor()); 

                    if (tamano > valor){
                        String a = this.operacionStack.pop();
                        String b = this.typeStack.pop(); 
                        this.tipo_var = "Int"; 

                        this.printElim(a,b);
                    }
                    else{
                        System.out.println("\n\nERRROR : Index Outbonding. El indice debe ser menor que el tamaño del vector.");
                        System.out.println("Linea: " + location1);
                        System.exit(1);
                    }

                }
                else if (nombre.equals("HexLiteral")){
                    int tamano = Integer.parseInt(this.typeStack.peek()); 
                    int valor = Integer.parseInt(raiz.getValor(), 16); 

                    if (tamano > valor){
                        String a = this.operacionStack.pop();
                        String b = this.typeStack.pop(); 
                        this.tipo_var = "Int"; 

                        this.printElim(a,b);
                    }
                    else{
                        System.out.println("\n\nERRROR : Index Outbonding. El indice debe ser menor que el tamaño del vector.");
                        System.out.println("Linea: " + location1);
                        System.exit(1);
                    }
                }
            }
        }

        //-------------------------------------------------------------------------------------------------------------------------

        return 0; 
    }


    /*
     * Elimina los elementos del stack 
     */
    public void eliminacionStack(String tipo, int location){
        this.printStacks(); 

        if (this.typeStack.size() > 0){
            String operacion = this.operacionStack.peek(); 
            String tipo_pico = this.typeStack.peek(); 
            
            if(tipo_pico.equals(tipo)){
                //eliminacion en stacks
                this.operacionStack.pop(); 
                this.typeStack.pop(); 

                this.printElim(operacion,tipo_pico);
                
                
                if(operacion.equals("For")){
                    this.operacionStack.add("For_cond");
                    this.typeStack.add("Boolean");
                }


                else if (this.typeStack.size() > 0){
                    if(operacion.equals("LessThan")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
                    }
                    else if(operacion.equals("GreaterThan")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
                    }
                    else if(operacion.equals("LessEqualThan")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop();
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b); 
                        }
    
                    }
                    else if(operacion.equals("GreaterEqualThan")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
    
                    }
                    else if(operacion.equals("Equal")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
    
                    }
                    else if(operacion.equals("NotEqual")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
    
                    }
                    else if(operacion.equals("And")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
    
                    }
                    else if(operacion.equals("Or")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
                    }
                    else if(operacion.equals("Return")){
                        if(this.typeStack.peek().equals("Boolean")){
                            String a = this.operacionStack.pop();
                            String b = this.typeStack.pop(); 
                            this.tipo_var = "Boolean"; 

                            this.printElim(a,b);
                        }
                    }
                } 
                else {
                    //System.out.println("La operacion era de tipo: " + operacion);
                }
            } 
            else {
                // errores de eliminacion
                this.errorElim(tipo, location);
            }
        } 
        else {
            //System.out.println("No se hace eliminacion");
        }
    }

    public void errorElim(String tipo, Integer location){
        String operacion = this.operacionStack.peek(); 
        String tipo_pico = this.typeStack.peek(); 

        // el pico es Boolean y se le mandó un Int
        if(tipo_pico.equals("Boolean") && tipo.equals("Int")){
            //System.out.println("No se hizo eliminacion, tipo Boolean permitida.");
        } 

        // El pico es un Int y se le mandó un Boolean hay error
        else if (tipo_pico.equals("Int") && tipo.equals("Boolean")) {
            if (operacion.equals("Asign")){
                System.out.println("\n\nERRROR : No le puede asignar valor tipo " + tipo + " a una variable tipo " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("AsignAdd")){
                System.out.println("\n\nERRROR : No le puede asignar valores tipo " + tipo + " a una variable tipo " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("AsignSubs")){
                System.out.println("\n\nERRROR : No le puede asignar valores tipo " + tipo + " a una variable tipo " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Add")){
                System.out.println("\n\nERRROR : No puede sumar valores tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Substract")){
                System.out.println("\n\nERRROR : No puede restar valores tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Multiplication")){
                System.out.println("\n\nERRROR : No puede multiplicar valores tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Division")){
                System.out.println("\n\nERRROR : No puede dividir valores tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Mod")){
                System.out.println("\n\nERRROR : No puede calcular el Mod en valores tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("LessThan")){
                System.out.println("\n\nERRROR : No puede hacer una comparación LessThan con variables tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("GreaterThan")){
                System.out.println("\n\nERRROR : No puede hacer una comparación GreaterThan con variables tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("LessEqualThan")){
                System.out.println("\n\nERRROR : No puede hacer una comparación LessEqualThan con variables tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("GreaterEqualThan")){
                System.out.println("\n\nERRROR : No puede hacer una comparación GreaterEqualThan con variables tipo " + tipo);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Equal")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("NotEqual")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("And")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Or")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
        }
        else {
            // Casos especiales
            if (operacion.equals("Asign")){
                System.out.println("\n\nERRROR : No le puede asignar valores tipo " + tipo + " a una variable tipo " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("And")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else if (operacion.equals("Or")){
                System.out.println("\n\nERRROR : No puede comparar variables de distinto tipo " + tipo + " y " + tipo_pico);
                System.out.println("Linea: " + location);
                System.exit(1);
            }
            else{
                System.out.println("\n\nERRROR : Error no clasificado en el: " + tipo);
                System.out.println("la expresion dentro del " + tipo +" no es válida.");
                System.out.println("Linea: " + location);
                System.exit(1);
            }
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