package semantic;
import java.util.ArrayList;
// import java.util.Stack;

public class Simbolo{
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
     * getTypeSimbol  
     * @return el tipo de un simbolo a partir de su id 
     */
    public String getTypeSimbol(String identifierS){
        int tamano = this.id.size() - 1; 

        for (int i = tamano; i >=0; i--){
            if(this.identifier.get(i).equals(identifierS)){
                return this.type.get(i); 
            }
        }
        return "";
    }


    /*
     * isFunction 
     * @return retorna se un simbolo es función no  
     */
    public int isFunction(String identifierS){
        int tamano = this.id.size() - 1; 

        for (int i = tamano; i >=0; i--){
            if(this.identifier.get(i).equals(identifierS)){
                return this.method.get(i); 
            }
        }
        return 3;
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


