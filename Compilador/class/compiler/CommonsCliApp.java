import org.apache.commons.cli.*;

import java.io.OutputStream;

/**
 * Ejemplos del uso de la librería "Apache Commons CLI"
 * @author Carlos García. Autentia.
 */
public class CommonsCliApp {

    /**
     * -ip address [-port puerto]  [err|console] [-help | -h]
     */
    public static void main(String[] args) {
        final int DEF_PORT = 60001;     // Puerto por defecto

        int               port    = 0;
        String            ip      = null;
        OutputStream      output  = null;
        CommandLineParser parser  = null;
        CommandLine       cmdLine = null;

        ///////////////////////////////////////////////////////////////////////
        // Fase 1: Configuramos las opciones de validación de entrada.
        ///////////////////////////////////////////////////////////////////////

        Options options = new Options();
        options.addOption("ip",     true,  "IP de destino");
        options.addOption("port",   true,  "Puerto destino");
        options.addOption("h",      "help", false, "Imprime el mensaje de ayuda");

        // No pueden aparecen las dos opciones simultáneamente.
        OptionGroup group =  new OptionGroup();
        group.addOption(new Option("err",     "Salida estándar de errores"));
        group.addOption(new Option("console", "Salida estándar"));
        options.addOptionGroup(group);


        try {

            ///////////////////////////////////////////////////////////////////////
            // Fase 2: Parseamos la entrada con la configuración establecida
            ///////////////////////////////////////////////////////////////////////

            parser  = new BasicParser();
            cmdLine = parser.parse(options, args);

            ///////////////////////////////////////////////////////////////////////
            // Fase 3: Analizamos los resultados y realizamos las tareas pertinentes
            ///////////////////////////////////////////////////////////////////////

            // Si está la opcion de ayuda, la imprimimos y salimos.
            if (cmdLine.hasOption("h")){    // No hace falta preguntar por el parámetro "help". Ambos son sinónimos
                new HelpFormatter().printHelp(CommonsCliApp.class.getCanonicalName(), options );
                return;
            }

            // Si el usuario ha especificado el puerto lo leemos
            if (cmdLine.hasOption("port")){
                port = Integer.parseInt( cmdLine.getOptionValue("port") );
            } else {
                port = DEF_PORT;
            }

            // Leemos la dirección IP. Sino existe generamos un error pues es un parámetro requerido.
            ip =  cmdLine.getOptionValue("ip");
            if (ip == null){
                throw new org.apache.commons.cli.ParseException("La direccion IP es requerida");
            }

            if (cmdLine.hasOption("console")){
                output = System.out;
            } else if (cmdLine.hasOption("err")){
                output = System.err;
            } else {
                output = null;
            }

            // ..............................................................
            // Aquí irían las tareas que tuviera que realizar la aplicación
            // ..............................................................

            System.out.println("OK");

        } catch (org.apache.commons.cli.ParseException ex){
            System.out.println(ex.getMessage());

            new HelpFormatter().printHelp(CommonsCliApp.class.getCanonicalName(), options );    // Error, imprimimos la ayuda
        } catch (java.lang.NumberFormatException ex){
            new HelpFormatter().printHelp(CommonsCliApp.class.getCanonicalName(), options );    // Error, imprimimos la ayuda
        }
    }
}