//import scanner.*;

public class Compiler {

    public static void main(String[] args) {
        
        int n = args.length;
        System.out.println(n);

        if(n == 3) {
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
                System.out.println("Opcion -target");
                System.out.println("<stage> " + args[2]);

                if (args[2].equals("scan")) {
                    System.out.println("Valid <target>");
                    // llamamos una instancia del scan 
                    

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
                System.out.println("Opcion -debug");
                System.out.println("<stage> " + args[2]);

                // split de args[2], se debe separar por

                // es como un target, pero se debuguean las opciones que aparecen 

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