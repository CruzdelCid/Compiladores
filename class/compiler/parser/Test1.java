package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files


public class Test1 {
    public static void parsear(String filename) throws IOException {
        System.out.println(filename);

        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/compiler/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');
        System.out.println(ruta);

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
            System.out.println("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
        }
    }

    public static void main(String[] args) throws IOException {
        parsear("archivo.txt");
        
    }
}
