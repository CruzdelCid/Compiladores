package semantic;
import java.util.ArrayList;



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