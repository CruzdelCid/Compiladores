import scanner.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Compiler {

    public static void main(String[] args) {
        // parametros de prueba
        //String info[] = {"archivo.txt", "-target", "scan"};
        //args = info;
        int n = args.length;

        if(n == 3) {
            String filename = args[0];
            //System.out.println(args[0]);
            //System.out.println(args[1]);
            //System.out.println(args[2]);

            if (args[1].equals("-o")) {
                // Se lee el archivo Makefile
                // definimos el archivo de salida, tal vez en MakeFile 
                System.out.println("Opcion -o");
                System.out.println("<outname> " + args[2]);


            } 
            else if (args[1].equals("-target")) {


                if (args[2].equals("scan")) {
                    // llamamos una instancia del scan 
                    System.out.println("stage: scanning\n");

                    try {
                        Path path = Paths.get("");
                        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
                        char caracter = (char) 92; 
                        ruta = ruta.replace(caracter, '/');
                        System.out.println(ruta);
                        Reader lector = new BufferedReader(new FileReader(ruta));
                        Scanner lexer = new Scanner(lector);
            
                        while (true) {
                            String tokens;
                            try {
                                tokens = lexer.yylex().toString();
            
                            } catch (Exception e) {
                                System.out.println("FIN");
                                return;
                            }
                            if(tokens.equals("ERROR")){
                                System.out.println(tokens + ": " + lexer.lexeme);
                                System.out.println("Linea: " + lexer.line + " Columna: " + lexer.column);
                                System.out.println(" ");
                            }
                        }

                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                        System.out.println("Error a ejecutar el Scanner");
                    }
                }
                else {
                    System.out.println("Invalid <target>");
                }

            }
            else if (args[1].equals("-opt")) {
                System.out.println("Opcion -opt");
                System.out.println("<opt_stage> " + args[2]);

            }
            else if (args[1].equals("-debug")) {

                if (args[2].equals("scan")) {
                    // llamamos una instancia del scan 
                    System.out.println("debugging: scan\n");

                    try {
                        Path path = Paths.get("");
                        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
                        char caracter = (char) 92; 
                        ruta = ruta.replace(caracter, '/');
                        System.out.println(ruta);
                        Reader lector = new BufferedReader(new FileReader(ruta));
                        Scanner lexer = new Scanner(lector);
            
                        while (true) {
                            String tokens;
                            try {
                                tokens = lexer.yylex().toString();
            
                            } catch (Exception e) {
                                System.out.println("FIN");
                                return;
                            }
                            System.out.println(tokens + ": " + lexer.lexeme);
                            System.out.println("Linea: " + lexer.line + " Columna: " + lexer.column);
                            System.out.println(" ");
                        }
                        
                    } catch (FileNotFoundException ex) {
                        System.out.println(ex);
                        System.out.println("Error a ejecutar el Scanner");
                    }
                }
                else {
                    System.out.println("Invalid <target>");
                }
            }
            else {
                System.out.println("Invalid <option>");
            }
        } 
        else if (args.length == 1 && args[0].equals("-h")) {
            // imprimir sinopsis de ayuda
            System.out.println("-h");
            System.out.println("    <filename> -o <outname>");
            System.out.println("    <filename> -target <stage>");
            System.out.println("    <filename> -opt <opt_stage>");
            System.out.println("    <filename> -debug <stage>");
        }
        else {
            // imprimir sinopsis de ayuda
            System.out.println("-h forzado");
            System.out.println("    <filename> -o <outname>");
            System.out.println("    <filename> -target <stage>");
            System.out.println("    <filename> -opt <opt_stage>");
            System.out.println("    <filename> -debug <stage>");


        }    
    }
}