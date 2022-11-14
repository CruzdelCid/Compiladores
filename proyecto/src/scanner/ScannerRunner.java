package scanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ScannerRunner {
    private Reader lector; 
    private Boolean errores; 

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
