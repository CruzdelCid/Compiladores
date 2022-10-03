package parser;

import java.io.File;
import java.io.IOException;
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
    }
}
