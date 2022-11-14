import scanner.ScannerRunner;
public class Compiler {
    public static void main(String[] args) {
        // verificamos que la instruccion esté bien escrita



        // asignamos un valor numerico a cada target
        // asignar un valor numerico a la instruccion
        

        int n = 1; 
        int instruction = 0; 
        String filename = "programa3.txt"; 
        
        if(instruction == 0 && instruction == 1){
            // prosigue 
        }
        else{
            // corta le ejecucion 
        }



        // EJECUCION DE INSTRUCCIONES 
        if(n >= 0){
            System.out.println("staging:scanning");
            ScannerRunner scanner = new ScannerRunner(filename); 

            if(instruction == 0){
                // run
                scanner.run();
            }
            else if(instruction == 1){
                // debug
                scanner.debug();
            }
            if(scanner.errorHandler()){
                System.out.println("PARAR TODA LA COMPILACION");
            }
        }

        if(n >= 1){
            // ejecutamos el parser
        }

        if(n >= 2){
            // ejecutamos el ast  (grafica del arbol)
        }

        if(n >= 3){
            // ejecutamos el semactic 
        }

        if(n >= 4){
            // ejecutamos el ist 
        }

        if(n >= 5){
            // generamos codigo 
        }

        

        

        //ScannerRunner scann = New ScannerRunner(filename); 
        //scann.run(); 
        //scann.printErrores(); 

        //ParserRuner paserr = New ParseRunner(Filename); 
        //parserr.run(); 
        
        //Nodo ast = parserr.getAst(); 
        //Semantic sem = new Semantic(ast); 
        //sem.run(); 
        //sem.printErrores(); 
        //sem.printDoble()
    }
    // parser()
}
