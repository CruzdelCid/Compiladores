import scanner.Scanner1;
import parser.Sintax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files


public class Compiler2 {
    public static void scanear(String filename, Boolean debug) throws FileNotFoundException {
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/compiler/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');
        Reader lector = new BufferedReader(new FileReader(ruta));

        Scanner1 lexer = new Scanner1(lector);
        
        while (true) {
            String tokens;
            try {
                tokens = lexer.yylex().toString();
            } catch (Exception e) {
                System.out.println("FIN");
                return;
            }
            if(debug == true){
                if(tokens.equals("ERROR")){
                    System.out.println(tokens + ": " + lexer.lexeme);
                    System.out.println("Linea: " + lexer.line + " Columna: " + lexer.column);
                    System.out.println(" ");
                }
            }
        }
    }

    public static void parsear(String filename) throws IOException {
        //System.out.println(filename);

        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/compiler/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');

        try {
            File archivo = new File(ruta);
            Scanner myReader = new Scanner(archivo);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              ST = ST + "\n" + data;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println(ST);

        Sintax s = new Sintax(new parser.ScannerCup(new StringReader(ST)));
        
        try {
            s.parse();
            // System.out.println();
            System.out.println("Analisis realizado correctamente");

        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
        }
    }



    public static void main(String[] args) throws IOException {
        //String str = "archivo.txt -debug scan";
        args = new String[] {"archivo.txt", "-target"};

        int n = args.length;

        if(n != 3){
            // imprimir sinopsis de ayuda
            String msg = "\n" + 
                        "-h forzado\n" + 
                        "    <filename> -o <outname>\n"+
                        "    <filename> -target <stage>\n"+
                        "    <filename> -opt <opt_stage>\n"+
                        "    <filename> -debug <stage>\n\n" +
                        "More arguments expected"; 

            throw new RuntimeException(msg);
        }

        String filename = args[0];
        String opt = args[1]; 
        String target = args[2];

        System.out.println(target);

        switch(opt) {
            case "-o":
                break;

            case "-target":
                break;

            case "-debug":
                break;
        }


        if(n == 3) {

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
                    scanear(filename, false);
                }
                else if (args[2].equals("parse")) {
                    // llamamos una instancia del parser
                    System.out.println("stage: scanning\n");
                    scanear(filename,false);

                    System.out.println("stage: parsing\n");
                    parsear(filename);
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
                        Scanner1 lexer = new Scanner1(lector);
            
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
                else if (args[2].equals("parse")) {
                    // llamamos una instancia del scan 
                    System.out.println("debugging: scan\n");

                    try {
                        Path path = Paths.get("");
                        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
                        char caracter = (char) 92; 
                        ruta = ruta.replace(caracter, '/');
                        System.out.println(ruta);
                        Reader lector = new BufferedReader(new FileReader(ruta));
                        Scanner1 lexer = new Scanner1(lector);
            
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
                    System.out.println("debugging: parse\n");
                    // Aqui va el parser
                    
                    parsear(filename);

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