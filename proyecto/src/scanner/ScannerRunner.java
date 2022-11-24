package scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScannerRunner {
    private Reader lector; 
    private Boolean errores = false; 

    public ScannerRunner(String filename){
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
            Scan lexer = new Scan(lector);

            System.out.println("\nIMPRESION DE ERRORES");
            System.out.format("%5s %10s\n",
                     "Type","Value");
            while (true) {
                String tokens;
                String value;

                try {
                    tokens = lexer.yylex().toString();
                    value = lexer.yytext(); 

                } catch (Exception e) {
                    if(this.errores == false){
                        System.out.println("No hubo errores. \n");
                    }
                    return;
                }

                // impresion de los tokens
                if (tokens.equals("ERROR")){
                    System.out.format("%5s %10s\n",
                    tokens, value);
                    this.errores = true;
                }
            }
        } catch (Exception ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void debug(){
        try {
            Scan lexer = new Scan(lector);

            System.out.println("\nIMPRESION DE TOKENS");
            System.out.format("%5s %10s\n",
                     "Type","Value");
            while (true) {
                String tokens;
                String value;

                try {
                    tokens = lexer.yylex().toString();
                    value = lexer.yytext(); 

                } catch (Exception e) {
                    if(this.errores == false){
                        System.out.println("No hubo errores. \n");
                    }
                    return;
                }

                // impresion de los tokens
                if (tokens.equals("ERROR")){
                    this.errores = true;
                }

                System.out.format("%5s %10s\n",
                    tokens, value);
            }
        } catch (Exception ex) {
            // 
        }
    }

    public Boolean errorHandler(){
        return this.errores; 
    }
}
