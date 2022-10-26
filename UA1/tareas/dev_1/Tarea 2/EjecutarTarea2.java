
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Irene Alba Posadas
 */
public class EjecutarTarea2 {
    
    public static void main(String[] args) {
        
        File directorio = new File("build\\classes\\");
        ProcessBuilder pb = new ProcessBuilder("java", "Tarea2");
        pb.directory(directorio);
        
        try{
            pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            
            Process p = pb.start();
                    
            try{
                p.waitFor();   
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }catch(IOException e1){
            e1.printStackTrace();
        }

    }
            
}
