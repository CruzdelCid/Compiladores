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


    //tabla de errores 
    private TablaParametros tablaParams;


    //Tipo de declaracion
    private String tipo; 


    //Declaracion de Variable
    private Boolean declaracion; 
    private Boolean field_dcl; 
    private Boolean method_dcl; 
    private Boolean method_dclared;
    private Integer last; 


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

    public void printParametros(){
        tablaParams.printParametros();
    }

    
    //METODOS//

    /*
     * createSimbolTable manda el nodo padre al metodo recorrer
     */
    public void createSimbolTable(){
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

        this.recurrer(this.padre); 
    }


    /*
     * recurrer recorrer los nodos y los manda al scanerNodos
     */
    private int recurrer(Nodo raiz){

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
            this.tablaParams.getParameters(last); // se imprimen los parametros de esa funcion
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
            }
        }

        // Eliminacion de un Scope 
        else if(nombre.equals("RightKey")){
            
            int scope = this.bloques.peek(); 
            this.declaracion = false;

            System.out.println("Vista Scope: " + scope);
            this.tabla.printTable();

            this.tabla.popScope(scope);
            this.bloques.pop();

        }

        return 0; 
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
     * toMethodVector 
     * Verifica si un simbolo ya existe en la tabla de simbolos 
     * @return nada
     */
    public void toMethodVector(int idS, int methodS, int sizeS){
        Boolean bandera = false; 
        int tamano = this.id.size() - 1; 
        int i;

        for (i = tamano; i >=0; i--){
            if(this.id.get(i) == idS){
                bandera = true; 
                break; 
            }
        }

        if(bandera){
            this.method.set(i, methodS);
            this.size.set(i, sizeS); 
        }
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





class Fila{
    public Integer id; 
    public Integer id_method;
    public String identifier; 
    public String type; 

    public Fila(Integer idN, Integer id_methodN, String identifierN, String typeF){
        this.id = idN; 
        this.id_method = id_methodN; 
        this.identifier = identifierN; 
        this.type = typeF; 
    }
}

class TablaParametros{
    ArrayList<Fila> filas = new ArrayList<Fila>();


    public void newFila(Integer idN, Integer id_methodN, String identifierN, String typeF){
        Fila nueva = new Fila(idN, id_methodN, identifierN, typeF); 
        
        this.filas.add(nueva);
    }

    public void printParametros(){
        int end = this.filas.size(); 
        System.out.println("");
        System.out.println("TABLA DE PARAMETROS");
        System.out.format("%5s %10s %10s %10s\n",
                     "Id","Id_Method", "Identifier", "TypeF");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < end; i++){
            System.out.format("%5d %10d %10s %10s\n",
                            this.filas.get(i).id,
                            this.filas.get(i).id_method,
                            this.filas.get(i).identifier,
                            this.filas.get(i).type
            );
        }
        System.out.println("");
    }

    public void getParameters(Integer id_method){
        int end = this.filas.size(); 
        System.out.println("");
        System.out.println("TABLA DE PARAMETROS del Metodo: " + id_method.toString());
        System.out.format("%5s %10s %10s %10s\n",
                     "Id","Id_Method", "Identifier", "TypeF");
        System.out.println("--------------------------------------");
        
        for (int i = 0; i < end; i++){
            
            if(this.filas.get(i).id_method == id_method){
                System.out.format("%5d %10d %10s %10s\n",
                            this.filas.get(i).id,
                            this.filas.get(i).id_method,
                            this.filas.get(i).identifier,
                            this.filas.get(i).type
                );
            }            
        }
        System.out.println("ESOS SON TODOS LOS PARAMETROS");
    }
}