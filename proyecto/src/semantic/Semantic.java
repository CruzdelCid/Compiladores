package semantic;
import java.util.ArrayList;
import java.util.Stack;

import parser.Nodo;


public class Semantic {
    //ATRIBUTOS//
    private Nodo padre;
    private int idScope;

    //Scope ID's 
    private Stack<Integer> bloques;

    //Contenido de Scopes 

    //tabla simbolica 
    private Simbolo tabla; 

    //tabla de doble declaracion
    private Simbolo tablaDuplicidad; 

    //tabla de errores 
    private ErrorR tablaErrores;

    //Declaracion de Variable
    private Boolean declaracion; 

    //Tipo de declaracion
    private String tipo; 




    //CONSTRUCTOR//  
    public Semantic(Nodo raiz){
        this.padre = raiz;
    }

    public void printDuplicidad(){
        tablaDuplicidad.printTable();
    }

    public void printErrores(){
        tablaErrores.printTable();
    }

    
    //METODOS//

    /*
     * recorrer manda el nodo padre al metodo reccorrer 0
     */
    public void createSimbolTable(){
        //seteo inicial de variables 
        this.bloques = new Stack<Integer>(); 
        this.tabla = new Simbolo(); 
        this.tablaDuplicidad = new Simbolo(); 
        this.tablaErrores = new ErrorR(); 
        this.declaracion = false; 
        this.tipo = "";
        this.idScope = 0;  

        this.recurrer(this.padre); 
    }

    /*
     * recurrerR recorrer el arbol AST y crea la tabla  
     */
    private int recurrer(Nodo raiz){

        if(raiz.getNombre().equals("LeftKey")){
            this.idScope += 1; 
            this.bloques.add(idScope); 
            this.declaracion = false;
            
        }
        else if(raiz.getNombre().equals("SemiColom")){
            this.declaracion = false;
        }

        else if(raiz.getNombre().equals("Void") || 
            raiz.getNombre().equals("Int") || 
            raiz.getNombre().equals("Boolean")){
            
            this.declaracion = true; 
            this.tipo = raiz.getValor(); 

        }
        // Eliminacion de un Scope 
        else if(raiz.getNombre().equals("RightKey")){
            
            int scope = this.bloques.peek(); 
            this.declaracion = false;

            System.out.println("Vista Scope: " + scope);
            this.tabla.printTable();

            this.tabla.popScope(scope);
            this.bloques.pop();

        }
        // Agregar a la tabla signos (tabla simbolica)
        else if(raiz.getNombre().equals("Id") && (declaracion==true)){
            int id = raiz.getNumNodo(); 
            String identifier = raiz.getIdentifier();
            String type = this.tipo; 
            int location = raiz.getLocation(); 
            int scope = this.bloques.peek(); 

            
            if(this.tabla.containsSimbolScope(identifier, scope)){
                System.out.println("Doble declaracion de variables: " + identifier);
                this.tablaErrores.addError(id, identifier, scope, location);
            }
            else{
                this.tabla.addSimbol(id, identifier, type, scope, location, 0, 0);
            }
        }
        
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
            }
        }

        



        ArrayList<Nodo> nodos = raiz.getHijos();

        // busqueda recursiva
        for (Nodo i : nodos){
            if(i!=null){
                recurrer(i); 
            }
        }

        return 1;
    }
}






class Simbolo{
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> identifier = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<Integer> scope = new ArrayList<Integer>();
    ArrayList<Integer> location = new ArrayList<Integer>();

    ArrayList<Integer> method = new ArrayList<Integer>();
    ArrayList<Integer> size = new ArrayList<Integer>();
    
    /*
     * addSimbol 
     * Añade un nuevo simbolo a la tabla de simbolos
     */
    public void addSimbol(int idS, String identifierS, String typeS, int scopeS, int location2, int method, int size){
        this.id.add(idS); 
        this.identifier.add(identifierS); 
        this.type.add(typeS); 
        this.scope.add(scopeS); 
        this.location.add(location2);

        this.method.add(method); 
        this.size.add(size); 
    }


    /*
     * containsSimbol 
     * Verifica si un simbolo ya existe en la tabla de simbolos 
     * @return un valor booleano indicando si existe o no
     */
    public boolean containsSimbol(String identifierS){
        
        return this.identifier.contains(identifierS);
    }


    /*
     * containsSimbol 
     * Verifica si un simbolo ya existe en la tabla de simbolos 
     * @return un valor booleano indicando si existe o no
     */
    public boolean containsSimbolScope(String identifierS, int idScope){
        Boolean bandera = false; 
        int tamano = this.id.size() - 1; 

        for (int i = tamano; i >=0; i--){
            if(this.scope.get(i) != idScope){
                break; 
            }
            if(this.identifier.get(i).equals(identifierS)){
                bandera = true; 
                break; 
            }
        }

        return bandera;
    }


    /*
     * printTable 
     * Verifica imprime toda la tabla de simbolos
     */
    public void printTable(){
        int end = this.id.size(); 
        System.out.println("");
        System.out.println("TABLA DE SIMBOLOS");
        System.out.format("%3s %15s %10s %10s %10s %10s %10s\n",
                     "ID", "Idenfifier", "Type", "Scope", "Location", "Method", "Size");
        System.out.println("---------------------------------------------------------------------------");
        
        for (int i = 0; i < end; i++){
            System.out.format("%3d %15s %10s %10d %10s %10d %10s\n",
                            this.id.get(i), this.identifier.get(i), 
                            this.type.get(i), this.scope.get(i),
                            this.location.get(i), this.method.get(i),
                            this.size.get(i));
        }
        System.out.println("");
    }


    /*
     * popScope 
     * elimina los simbolos que pertenecen al scopeId dado
     */
    public void popScope(int scopeId){
        int end = this.scope.size(); 
        
        for (int i = 0; i < end; i++){
            
            if(this.scope.get(i) == scopeId){
                this.id.remove(i); 
                this.identifier.remove(i); 
                this.type.remove(i); 
                this.scope.remove(i); 
                this.location.remove(i);
                this.method.remove(i);
                this.size.remove(i);

                i += -1;
                end += -1; 
            }
        }
    }
}














class ErrorR{
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> identifier = new ArrayList<String>();
    ArrayList<Integer> scope = new ArrayList<Integer>();
    ArrayList<Integer> location = new ArrayList<Integer>();

    /*
     * addError 
     * Añade un error a la lista. 
     * Un error sucede cuando se intenta usar una varibale
     * que no ha sido declarada.  
     */
    public void addError(int idS, String identifierS, int scopeS, int location2){
        this.id.add(idS); 
        this.identifier.add(identifierS); 
        this.scope.add(scopeS); 
        this.location.add(location2); 
    }

    /*
     * printTable 
     * Imprime la tabla de errores. 
     */
    public void printTable(){
        int end = this.identifier.size(); 
        System.out.println("");
        System.out.println("TABLA DE ERRORES");
        System.out.format("%5s %10s %10s %10s\n",
                     "Id","Identifier", "Scope", "Location");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < end; i++){
            System.out.format("%5s %10s %10d %10s\n",
                            this.id.get(i),
                            this.identifier.get(i), 
                            this.scope.get(i),
                            this.location.get(i));
        }
        System.out.println("");
    }
}
