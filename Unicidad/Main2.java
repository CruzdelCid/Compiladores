import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.core.JsonParseException;  
import com.fasterxml.jackson.databind.JsonMappingException;  
import com.fasterxml.jackson.databind.ObjectMapper;  
// create class FirstApplication to understand the implementation of Jackson  

public class Main2 {
    // Id en la tabla de simbolos 
    public static int idNumber = 1; 

    // Numero de Scope
    public static int scopeNumber = 1; 

    // Tabla simbolica  
    public static Simbolo tabla = new Simbolo();

    // Lista de duplicidad  
    public static Simbolo tablaDuplicidad = new Simbolo();

    // Lista de errores
    // variables no declaradas pero sí usadas
    public static ErrorR errores = new ErrorR(); 

    // 
    public static int createTable(Block bloque){
        int blockScope = scopeNumber;

        System.out.println("Vista_Scope: " + blockScope + "");

        ArrayList<VarDeclaration> declaraciones = bloque.varDeclaration;
        ArrayList<VarUsage> usos = bloque.varUsage;
        ArrayList<Block> scopes = bloque.block;
        
        //lista de variables del scope
        ArrayList<String> variables = new ArrayList<String>();  

        // Recorrido de varDeclaration
        for (VarDeclaration i : declaraciones) {

            if(variables.contains(i.name)){
                //System.out.println("La variable " + i.name + " ya fue declarada.");
                tablaDuplicidad.addSimbol(idNumber, i.name, i.type, scopeNumber, i.location);
                tabla.addSimbol(idNumber, i.name, i.type, scopeNumber, i.location);
                variables.add(i.name); // Añade la variable a lista del scope
                idNumber += 1; 
            }
            else{
                //System.out.println("Tipo: " + i.type + " Nombre: "+ i.name + " location: "+ i.location);
                tabla.addSimbol(idNumber, i.name, i.type, scopeNumber, i.location);
                variables.add(i.name); // Añade la variable a lista del scope
                idNumber += 1; 
            }            
        }


        // Recorrido de varUsage
        for (VarUsage i : usos) {
            if(tabla.containsSimbol(i.name)){
                //System.out.println("Nombre: " + i.name + " Valor: "+ i.value + " location: "+ i.location);
            }
            else{
                //System.out.println("Nombre: " + i.name + " Valor: "+ i.value + " location: "+ i.location);
                errores.addError(i.name, blockScope, i.location);
            }
            
        }
        
        tabla.printTable();
        // Busqueda recursiva 
        for (Block i : scopes) {
            scopeNumber += 1;
            createTable(i); 
        }

        // imprime la tabla

        //Elimina el scope actual
        tabla.popScope(blockScope);

        return 1000;
    }


    public static void main(String args[]){       

        ObjectMapper mapper = new ObjectMapper();  
        String empString = ""; 

        try{
            // Creacion de la clase Root
            Root ruta = mapper.readValue(new File("prueba.json"), Root.class);  
            empString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ruta);  
            System.out.println(empString);

            // Creacion de la tabla
            System.out.println("CREACION TABLA DE SIMBOLOS");
            createTable(ruta.block);

            // Impresion de tabla de duplicidad
            System.out.println("\nTABLA DE DUPLICIDAD DE DECLARACIONES");
            tablaDuplicidad.printTable();

            // Impresion de tabla de errores, 
            // variables no declaradas antes de ser declaradas
            System.out.println("\nTABLA DE ERRORES");
            errores.printTable();

        }catch (JsonParseException e) {  
            e.printStackTrace();  
        }catch (JsonMappingException e) {  
            e.printStackTrace();   
        }catch (IOException e) {  
            e.printStackTrace();   
        }  
    }  
}  




class Block{
    public ArrayList<VarDeclaration> varDeclaration;
    public ArrayList<VarUsage> varUsage;
    public ArrayList<Block> block;
}


class Root{
    public Block block;
}


class VarDeclaration{
    public String type;
    public String name;
    public String location; 
}


class VarUsage{
    public String name;
    public String value;
    public String location; 
}


class Simbolo{
    ArrayList<Integer> id = new ArrayList<Integer>();
    ArrayList<String> identifier = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    ArrayList<Integer> scope = new ArrayList<Integer>();
    ArrayList<String> location = new ArrayList<String>();
    
    /*
     * addSimbol 
     * Añade un nuevo simbolo a la tabla de simbolos
     */
    public void addSimbol(int idS, String identifierS, String typeS, int scopeS, String location2){
        this.id.add(idS); 
        this.identifier.add(identifierS); 
        this.type.add(typeS); 
        this.scope.add(scopeS); 
        this.location.add(location2); 
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
     * printTable 
     * Verifica imprime toda la tabla de simbolos
     */
    public void printTable(){
        int end = this.id.size(); 
        System.out.println("");
        System.out.format("%3s %15s %10s %10s %10s\n",
                     "ID", "Idenfifier", "Type", "Scope", "Location");
        System.out.println("-----------------------------------------------------");
        
        for (int i = 0; i < end; i++){
            System.out.format("%3d %15s %10s %10d %10s\n",
                            this.id.get(i), this.identifier.get(i), 
                            this.type.get(i), this.scope.get(i),
                            this.location.get(i));
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

                i += -1;
                end += -1; 
            }
        }
    }
}



class ErrorR{
    ArrayList<String> identifier = new ArrayList<String>();
    ArrayList<Integer> scope = new ArrayList<Integer>();
    ArrayList<String> location = new ArrayList<String>();

    /*
     * addError 
     * Añade un error a la lista. 
     * Un error sucede cuando se intenta usar una varibale
     * que no ha sido declarada.  
     */
    public void addError(String identifierS, int scopeS, String location2){
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
        System.out.format("%10s %10s %10s\n",
                     "Idenfifier", "Scope", "Location");
        System.out.println("----------------------------------");
        
        for (int i = 0; i < end; i++){
            System.out.format("%10s %10d %10s\n",
                            this.identifier.get(i), 
                            this.scope.get(i),
                            this.location.get(i));
        }
        System.out.println("");
    }
}



/* 
class Block{
    public ArrayList<VarDeclaration> varDeclaration;
    public ArrayList<VarUsage> varUsage;
}

class Root{
    public Block block;
}

class VarDeclaration{
    public String type;
    public String name;
}

class VarUsage{
    public String name;
    public String value;
}
*/