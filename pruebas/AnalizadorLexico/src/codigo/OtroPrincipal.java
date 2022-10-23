package codigo;
import java.io.BufferedReader;
//import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.PrintWriter;
import java.io.Reader;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class OtroPrincipal {
    public static void main(String[] args) {
        
        try {
            Reader lector = new BufferedReader(new FileReader("archivo.txt"));
            Lexer lexer = new Lexer(lector);
            

            System.out.println(lexer);
            System.out.println(lexer.yylex()+ ": " + lexer.yytext());

            while (true) {
                Tokens tokens = lexer.yylex();
                if (tokens == null) {
                    System.out.println("FIN");
                    return;
                }
                System.out.println(tokens + ": " + lexer.yytext());
            }

            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());
            //System.out.println(lexer.yylex()+ ": " + lexer.yytext());

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
