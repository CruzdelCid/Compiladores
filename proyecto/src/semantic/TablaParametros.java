package semantic;
import java.util.ArrayList;
// import java.util.Stack;
import java.util.Stack;


public class TablaParametros{
    ArrayList<Fila> filas = new ArrayList<Fila>();


    public void newFila(Integer idN, Integer id_methodN, String identifierN, String typeF){
        Fila nueva = new Fila(idN, id_methodN, identifierN, typeF); 
        this.filas.add(nueva);
    }

    // imprime todos los parametros
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

    public void printGetParameters(Integer id_method){
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

    public void ponerParams(Stack<String> operacion, Stack<String> type, Integer id_funcion){
        int end = this.filas.size() - 1;

        for (int i = end; i >= 0; i--){
            Fila fila = this.filas.get(i);

            if (fila.id_method.equals(id_funcion)){
                type.add(fila.type); 
                operacion.add("Function"); 
            }

        }
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