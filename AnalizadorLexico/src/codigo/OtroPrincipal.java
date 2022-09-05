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
            
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());
            System.out.println(lexer.yylex()+ ": " + lexer.yytext() + " " + lexer.yyincio() + " " + lexer.yyfinal());



            System.out.println("\n\n");

            String resultado = "";
            while (true) {
                Tokens tokens = lexer.yylex();
                if (tokens == null) {
                    resultado += "FIN";
                    System.out.println(resultado);
                    // txtResultado.setText(resultado);
                    return;
                }
                switch (tokens) {
                    case ERROR:
                        resultado += "Simbolo no definido\n";
                        break;
                    case Identificador: case Numero: case Reservadas:
                        resultado += lexer.lexeme + ": Es un " + tokens + "\n";
                        break;
                    default:
                        resultado += "Token: " + tokens + "\n";
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            // Logger.getLogger(FrmPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
