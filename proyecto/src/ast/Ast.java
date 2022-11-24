package ast;
import parser.Nodo; 
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ast {
    private Nodo padre;
    private Boolean errores; 

    public Ast(Nodo raiz){
        this.padre = raiz;
    }

    public void run(){
        try{
            this.graficar(this.padre);
            System.out.println("\nSUCCESS: Arbol creado correctamente.\n");
        } catch (Exception e) {
            System.out.println("\nERROR: No fue posible graficar el arbol.\n");
        }
    }

    public void debug(){
        this.run(); 
    }

    public void graficar(Nodo raiz) throws InterruptedException{
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
    
    
    public String graficarNodo(Nodo nodo){
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

    public void mover() throws IOException{
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
}
