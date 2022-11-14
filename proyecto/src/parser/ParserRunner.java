package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

//Librerias extra agregadas (creo que las tendre que borrar)
import semantic.Semantic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files


public class ParserRunner {
    private Reader lector; 
    private Boolean errores; 

    public ParserRunner(String filename){
        this.errores = false;

        try {
            Path path = Paths.get("");
            String ruta = path.toAbsolutePath().toString() + "/program.txt";
            char n = (char) 92; 
            ruta = ruta.replace(n, '/');
            this.lector = new BufferedReader(new FileReader(ruta));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void run(){
        try {
            Parser parser = new Parser(lector);

            try {
                parser.parse();
                System.out.println("Analisis realizado correctamente");
                graficar(parser.padre);
    
                // Declaramos el objeto semantic 
                Semantic semanticAnalizer = new Semantic(parser.padre); 
    
                System.out.println("\n\nANALISIS SEMANTICO\n");
    
                semanticAnalizer.createSimbolTable(); //Ejercutamos el recorrido
                semanticAnalizer.printErrores();
                // huebo errores y sí 
                // imprime los errores
                // exception, para la ejecucion 
    
            } catch (Exception ex) {
                Symbol sym = parser.getS();
                System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
            }
        } catch (Exception ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void debug(){
        try {
            Scan lexer = new Scan(lector);

            while (true) {
                String tokens;
                try {
                    tokens = lexer.yylex().toString();

                } catch (Exception e) {
                    System.out.println("FIN");
                    return;
                }
                System.out.println(tokens + ": " + lexer.yytext());
            }
        } catch (Exception ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean errorHandler(){
        return this.errores; 
    }
}
