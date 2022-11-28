package scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    public static void main(String[] args) {
        System.out.println("dkbLKDBWLDJBAWELKJBAKWJBEKJ");
        try {
            Path path = Paths.get("");
		    String ruta = path.toAbsolutePath().toString() + "/programa3e.txt";
            char n = (char) 92; 
            ruta = ruta.replace(n, '/');
            Reader lector = new BufferedReader(new FileReader(ruta));
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

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR 1");
            System.out.println(ex);
            //Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            System.out.println("ERROR 2");
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
