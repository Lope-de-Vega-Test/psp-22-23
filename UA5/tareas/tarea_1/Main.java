
//package com.victoraljama;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.configuration2.INIConfiguration;


public class Main {
    
    static String helpText = new StringBuilder()
            .append("\n Este programa proporciona la funcionalidad basica de una API \n")
            .append("\n Comandos basicos:")
            .append("\n --help \t\t abrir el menu de ayuda del programa")
            .append("\n -p, --port \t\t mostrar el puerto en el que trabaja la API")
            .append("\n -h, --hostname \t\t mostrar el host o la IP en el que trabaja la API")
            .append("\n -v, --version \t\t mostrar la version actual de la API")
            .append("\n -c, --conf \t\t carga la configuracion de un fichero .ini")
            .toString();
    
    static String version = "1.0.0";
    static String configFileName = "";
    
    public static void main(String[] args) {
        Option opt1 = Option.builder(null).argName("help")
                .longOpt("help")
                .hasArg(false)
                .required(false)
                .desc("Muestra un menu de ayuda sobre la API")
                .build();
        
        Option opt2 = Option.builder("p").argName("port")
                .longOpt("port")
                .hasArg(true)
                .required(false)
                .desc("Asigna a la API el puerto en el que debe escuchar")
                .build();
        
        Option opt3 = Option.builder("v").argName("version")
                .longOpt("version")
                .hasArg(false)
                .required(false)
                .desc("Muestra la version actual de la API")
                .build();
        
        Option opt4 = Option.builder("h").argName("hostname")
                .longOpt("hostname")
                .hasArg(true)
                .required(false)
                .desc("Asigna a la API su direccion")
                .build();
        
        Option opt5 = Option.builder("c").argName("conf")
                .longOpt("conf")
                .hasArg(true)
                .required(false)
                .desc("Cambia la configuracion estandar de la API")
                .build();
        
        
        Options options = new Options();
        options.addOption(opt1).addOption(opt2).addOption(opt3).addOption(opt4).addOption(opt5);
        CommandLineParser parser = new DefaultParser();
        
        try{
            CommandLine line = parser.parse(options, args);
            Option[] arrayOptions = line.getOptions();
            Map<String, Object> arguments = new HashMap<String, Object>();
            
            for(Option opt : arrayOptions){
                arguments.put(opt.getArgName(), opt.getValue());
            }
            
            if(arguments.containsKey("help")){
                System.out.println(helpText);
                System.exit(0);
            }
            
            else if(arguments.containsKey("v") || arguments.containsKey("version")){
                System.out.println("API version: " + version);
                System.exit(0);
            }
            
            Api api = new Api();
            
            if(arguments.containsKey("h") || arguments.containsKey("hostname")){
                api.setHostName(String.valueOf(arguments.get("hostname")));
            }
            
            if(arguments.containsKey("p") || arguments.containsKey("port")){
                api.setPort(Integer.parseInt(String.valueOf(arguments.get("port"))));
            }
            
            if(arguments.containsKey("c") || arguments.containsKey("conf")){
               
                try{
                    INIConfiguration iniConfiguration = new INIConfiguration();
                    try (FileReader fileReader = new FileReader("conf" + arguments.get("conf") + ".ini")) {
                        iniConfiguration.read(fileReader);
                    }
                    
                    api.setHostName(iniConfiguration.getSection("api")
                    .getProperty("hostname")
                    .toString()); 
                    
                    api.setPort(Integer.parseInt(iniConfiguration.getSection("api")
                    .getProperty("port")
                    .toString())); 
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            System.out.println(api.getHostName() + ":" + api.getPort());
            
            api.crearApi();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
