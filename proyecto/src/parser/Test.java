package parser;

import semantic.Semantic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
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

    public static void graficar(Nodo raiz) throws InterruptedException{
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
            Runtime.getRuntime().exec(cmd).waitFor();     
            
            mover(); 

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

    public static void mover() throws IOException{
        Path path = Paths.get("");
        String rutaM = path.toAbsolutePath().toString();

        Path rutaSym = Paths.get(rutaM + "/src/ast/arbol.dot");
        if (Files.exists(rutaSym)) {
            Files.delete(rutaSym);
        }
        Files.move(
                Paths.get(rutaM + "/arbol.dot"), 
                Paths.get(rutaM + "/src/ast/arbol.dot")
        );
        Path rutaSin = Paths.get(rutaM + "/src/ast/arbol.png");
        if (Files.exists(rutaSin)) {
            Files.delete(rutaSin);
        }
        Files.move(
                Paths.get(rutaM + "/arbol.png"), 
                Paths.get(rutaM + "/src/ast/arbol.png")
        );
    }

    public static void parsear(String filename) throws IOException {
        System.out.println(filename);

        String ST = "";
        Path path = Paths.get("");
        String ruta = path.toAbsolutePath().toString() + "/" + filename; // Es necesario cambiar ruta desde CMD
        char caracter = (char) 92; 
        ruta = ruta.replace(caracter, '/');
        System.out.println("Ruta de Ejecucion:"+ruta);

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


        System.out.println("\nInput Program:\n" + ST);

        parser.ScannerCup Scannerr = new parser.ScannerCup(new StringReader(ST)); 

        Parser s = new Parser(Scannerr);
        
        try {
            s.parse();
            System.out.println("Analisis realizado correctamente");
            graficar(s.padre);

            // Declaramos el objeto semantic 
            Semantic semanticAnalizer = new Semantic(s.padre); 

            System.out.println("\n\nANALISIS SEMANTICO\n");

            semanticAnalizer.createSimbolTable(); //Ejercutamos el recorrido
            semanticAnalizer.printErrores();
            semanticAnalizer.printParametros();
            // huebo errores y sí 
            // imprime los errores
            // exception, para la ejecucion 

        } catch (Exception ex) {
            Symbol sym = s.getS();
            System.out.println("Error de sintaxis. Linea: " + (sym.right) + " Columna: " + (sym.left) + ", Texto: \"" + sym.value + "\"");
        }
    }

    public void run(String filename){
        try {
            parsear("programa3.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }

    public void debug(String filename){
        try {
            parsear("programa3.txt");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) throws IOException {
        parsear("programa3.txt");
    }
}
