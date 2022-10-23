package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import java_cup.runtime.Symbol;
import java.util.Scanner; // Import the Scanner class to read text files


public class Test {
    public static int verificar(Nodo raiz){

        System.out.println(raiz.getNombre());
        System.out.println(raiz.getNumNodo());
        System.out.println(raiz.getValor());

        return 1; 
    }

    public static void graficar(Nodo raiz){
        FileWriter archivo = null;
        PrintWriter pw = null;
        String cadena = graficarNodo(raiz);

        
        try{
            archivo = new FileWriter("arbol.dot");
            pw = new PrintWriter(archivo);
            pw.println("digraph G {node[shape=box, style=filled, color=blanchedalmond]; edge[color=chocolate3];rankdir=UD \n");
            pw.println(cadena);
            pw.println("\n}");
            archivo.close();
        }catch (Exception e) {
            System.out.println(e +" 1");
        }
        
        try {
            String cmd = "cmd /c dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe +" 2");
        }
        
    }
    
    public static String graficarNodo(Nodo nodo){
        String cadena = "";
        
        for(Nodo hijos : nodo.getHijos())
        {
            try {
                cadena += "\"" + nodo.getNumNodo() + "_" + nodo.getNombre() + " -> " + nodo.getValor() + "\"->\"" + hijos.getNumNodo() + "_" + hijos.getNombre() + " -> " + hijos.getValor() + "\"\n";
                cadena += graficarNodo(hijos);
            } catch (Exception e) {
                // System.out.println(e);
            }
            
        }
        
        return cadena;
    }


    public static void parsear(String filename) throws IOException {
        System.out.println(filename);

        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/src/" + filename; // Es necesario cambiar ruta desde CMD
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

        Parser s = new Parser(new parser.ScannerCup(new StringReader(ST)));
        
        try {
            s.parse();
            System.out.println("Analisis realizado correctamente");
            graficar(s.padre);

        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
        }
    }

    public static void main(String[] args) throws IOException {
        parsear("program.txt");
        
    }
}
