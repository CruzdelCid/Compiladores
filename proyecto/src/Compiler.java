import scanner.ScannerRunner;
import parser.Nodo;
import parser.ParserRunner;
import java.util.HashMap;

import ast.Ast;

import java.io.File;


public class Compiler {
    // asignamos un valor numerico a cada target
    public static HashMap<String, Integer> target = new HashMap<String, Integer>();

    // asignar un valor numerico a la instruccion
    public static HashMap<String, Integer> instructions = new HashMap<String, Integer>();

    static {
        target.put("scan", 1);
        target.put("parse", 2);
        target.put("ast", 3);
        target.put("semantic", 4);
        target.put("irt", 5);
        target.put("codegen", 6);

        instructions.put("-o", 1);
        instructions.put("-opt", 2);
        instructions.put("-h", 3);
        instructions.put("-target", 4);
        instructions.put("-debug", 5);
    }

    // mensaje de error para argumentos
    public static String errorMsg = """
        -h  
            <filename> -o <outname>
            <filename> -target <stage>
            <filename> -opt <opt_stage>
            <filename> -debug <stage>

        fases:
            scan 
            parse
            ast
            semantic
            irt
            codegen
    """;


    public static void main(String[] args) {
        // parametros 
        args = new String[] {"programa3.txt", "-target", "ast"};
        String filename = "";         
        String instruction = ""; 
        String fase = "";
        
        int inst = 0; // intrucción como numero
        int n = 1;    // fase como numero

        // verificacion del filename 
        try{
            filename = args[0];
        } catch (Exception e) {
            System.out.println("No se ingresó ningún parametro al programa" + errorMsg);
            System.exit(1);
        }
        
        
        File archivo = new File(filename); 
        if (!archivo.exists()) {
            System.out.println("El filename no existe en este directorio.");
            System.exit(1);
        }
        


        // verificacion de instruccion 
        try{
            instruction = args[1];
        } catch (Exception e) {
            System.out.println("Faltó el parámetro de instrucción <-debug> o <-target>" + errorMsg);
            System.exit(1);
        }


        try{
            inst = instructions.get(instruction);
        } catch (Exception e) {
            System.out.println("La instrucción no existe"  + errorMsg);
            System.exit(1);
        }


        // verificación fase o stage 
        try{
            fase = args[2]; 
        } catch (Exception e) {
            System.out.println("\nFase no especificadad. Se pondrá scan por defecto.\n");
            fase = "scan"; 
        }

        try{
            n = target.get(fase); 
        } catch (Exception e) {
            System.out.println("La fase ingresada " + fase + " no existe." + errorMsg);
            System.exit(1);
        }

        

        

        // verficacion de instrucciones para parar operacion
        if(inst == 1){
            System.out.println("Instruccion -o");
            System.exit(1);
        }
        else if (inst == 2){
            System.out.println("Instruccion -opt");
            System.exit(1);
        }
        else if (inst == 3){
            System.out.println("Mensaje de ayuda controlado" + errorMsg);
            System.exit(1);
        }


        System.out.println("**********COMPILATION********\n");

        System.out.println("filename: " + filename);
        System.out.println("instruction: " + inst);
        System.out.println("number target: " + n);
        // EJECUCIÓN DE INSTRUCCIONES TARGET Y DEBUG
        // Ejucucion scan 
        if(n >= 1){
            
            ScannerRunner scanner = new ScannerRunner(filename); 

            if(inst == 4){
                System.out.println("\nstaging: scanning");
                scanner.run();
            }
            else if(inst == 5){
                System.out.println("\ndebuging: scanning");
                scanner.debug();
            }
            if(scanner.errorHandler()){
                System.out.println("ERROR: Tokens no reconocidos. \n\nstopping: scanning");
                System.exit(1);
            }
        }

        // Ejucucion parse 
        Nodo padre = new Nodo(fase);
        if(n >= 2){
            
            ParserRunner parser = new ParserRunner(filename); 
            if(inst == 4){
                System.out.println("\nstaging: parsing");
                parser.run();
            }
            else if(inst == 5){
                System.out.println("\ndebuging: parsing");
                parser.debug();
            }
            if(parser.errorHandler()){
                System.out.println("ERROR: El código no cumple con la gramática stablecida. \n\nstopping: parsing");
                System.exit(1);
            }
            padre = parser.getPadre(); 
        }

        // Ejecucion del ast
        if(n >= 3){
            System.out.println("\nstaging: ast graph");
            Ast graph = new Ast(padre); 
            graph.run(); 
        }

        if(n >= 4){
            // ejecutamos el semactic 
        }

        if(n >= 5){
            // ejecutamos el ist 
        }

        if(n >= 6){
            // generamos codigo 
        }
    }
}
