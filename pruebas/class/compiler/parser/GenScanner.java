package parser;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;


public class GenScanner {
    public static void main(String[] args) { 
        Path path = Paths.get("");
		String ruta1 = path.toAbsolutePath().toString();
        String ruta = ruta1 + "/compiler/parser/LexerCup.flex"; 

        generarLexer(ruta);
    }
    public static void generarLexer(String ruta){
        File archivo = new File(ruta);
        System.out.println(archivo);
        JFlex.Main.generate(archivo);
    }
}
