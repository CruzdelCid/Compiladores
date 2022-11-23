package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files

public class ParserRunner {
    private Nodo padre;
    private String file; 
    private Boolean errores = false; 

    public ParserRunner(String filename){
        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');
        // System.out.println("Ruta de Ejecucion:"+ruta);

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

        this.file = ST; 
    }

    public void run(){
        parser.ScannerCup Scannerr = new parser.ScannerCup(new StringReader(this.file)); 

        Parser sintax = new Parser(Scannerr);
        
        try {
            sintax.parse();
            System.out.println("Analisis realizado correctamente");

            // GUARDAMOS EL PADRE EN LA CLASE 
            this.padre = sintax.padre; 

            /*
            // GRAFICACION DEL NODO
            Ast graph = new Ast(sintax.padre); 
            graph.run(); 
            

            // ANÁLISIS SEMANTICO 
            Semantic semanticAnalizer = new Semantic(sintax.padre); 
            System.out.println("\n\nANALISIS SEMANTICO\n");
            semanticAnalizer.createSimbolTable(); //Ejercutamos el recorrido
            semanticAnalizer.printErrores();
            semanticAnalizer.printParametros();
             */
  

        } catch (Exception ex) {
            Symbol sym = sintax.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
            this.errores = true; 
        }
    }

    public void debug(){
        this.run(); 
    }

    public Nodo getPadre(){
        return this.padre; 
    }

    public Boolean errorHandler(){
        return this.errores; 
    }
}