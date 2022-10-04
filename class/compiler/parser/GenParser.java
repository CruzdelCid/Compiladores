package parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.internal_error;


public class GenParser {
    public static void main(String[] args) throws internal_error, IOException, Exception { 
        Path path = Paths.get("");
		String ruta1 = path.toAbsolutePath().toString();
        String[] rutas = {"-parser", "Sintax", ruta1 + "/compiler/parser/Sintax.cup"};

        generarLexer(rutas);
    }
    
    public static void generarLexer(String[] ruta) throws internal_error, IOException, Exception{
        java_cup.Main.main(ruta);


        Path path = Paths.get("");
        String rutaM = path.toAbsolutePath().toString();

        Path rutaSym = Paths.get(rutaM + "/compiler/parser/sym.java");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(rutaM + "/sym.java"), 
                Paths.get(rutaM + "/compiler/parser/sym.java")
        );
        Path rutaSin = Paths.get(rutaM + "/compiler/parser/Sintax.java");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(rutaM + "/Sintax.java"), 
                Paths.get(rutaM + "/compiler/parser/Sintax.java")
        );
    }
}
