
package tarea22;
import java.io.IOException;


public class Tarea22 {
    //funcion main del archivo que previene de errores
    public static void main(String[] args) throws IOException {
        
        //creamos la variable que contrandrá al proceso del primer ejercicio
        ProcessBuilder pb = new ProcessBuilder("EXPLORER.exe");
        
        //inicializa el proceso anterior
        pb.start();
    }
    
}
