
package tarea2.programacion.de.procesos.de.java;

import java.io.*;
import java.io.IOException;


public class Tarea21ProgramacionDeProcesosDeJava {

    
    public static void main(String[] args) throws IOException {
        
        File directorio= new File("build\\classes\\");
        
        ProcessBuilder pb= new ProcessBuilder("java", "tarea2.programacion.de.procesos.de.java.Tarea2ProgramacionDeProcesosDeJava");
        pb.directory(directorio);
                
        try 
        {
            pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            
            Process p= pb.start();
            
            try
            {
                p.waitFor();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }
       
        
    }
    
}
