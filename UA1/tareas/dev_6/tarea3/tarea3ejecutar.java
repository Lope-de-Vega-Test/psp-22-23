package tarea3;
import java.io.File;
import java.io.IOException;

public class tarea3ejecutar {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        File directorio = new File(dir);
        ProcessBuilder pb = new ProcessBuilder("java", "tarea3.java");
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
