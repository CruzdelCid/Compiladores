package parser;

import ast.Ast;
import semantic.Semantic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files

public class Test {
    public static int verificar(Nodo raiz){

        System.out.println(raiz.getNombre());
        System.out.println(raiz.getNumNodo());
        System.out.println(raiz.getValor());

        return 1; 
    }

    public static void parsear(String filename)  {
        System.out.println(filename);

        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');
        System.out.println("Ruta de Ejecucion:"+ruta);

        try {
            File archivo = new File(ruta);
            Scanner myReader = new Scanner(archivo);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              ST = ST + "\n" + data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe");
            e.printStackTrace();
        }


        //System.out.println("\nInput Program:\n" + ST);

        parser.ScannerCup Scannerr = new parser.ScannerCup(new StringReader(ST)); 

        Parser s = new Parser(Scannerr);
        
        try {
            s.parse();
            System.out.println("Analisis realizado correctamente");
            Ast graph = new Ast(s.padre); 
            graph.run(); 
            
            // graficar(s.padre);

            // Declaramos el objeto semantic 
            Semantic semanticAnalizer = new Semantic(s.padre); 

            System.out.println("\n\nANALISIS SEMANTICO\n");

            semanticAnalizer.createSimbolTable(); //Ejercutamos el recorrido
            semanticAnalizer.printErrores();
            semanticAnalizer.printParametros();
            // huebo errores y sí 
            // imprime los errores
            // exception, para la ejecucion 

        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
        }
    }

    public void run(String filename){
        try {
            parsear("programa3.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public void debug(String filename){
        try {
            parsear("programa3.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws IOException {
        parsear("programa3.txt");
    }
}
