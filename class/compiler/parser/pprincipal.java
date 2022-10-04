package parser;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.runtime.Symbol;
import javax.swing.JFileChooser;
import java.util.Scanner; // Import the Scanner class to read text files


public class pprincipal {
    
    public static void main(String[] args) throws IOException {
    
        String ST = "";
        

        try {
            File archivo = new File("C:/Users/danie/Desktop/Compiladores/class/compiler/archivo.txt");
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
            System.out.println(s.parse());
            System.out.println("Analisis realizado correctamente");

        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right + 1) + " Columna: " + (sym.left + 1) + ", Texto: \"" + sym.value + "\"");
        }
    }
}
