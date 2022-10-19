
import java.io.File;
import java.io.IOException;


public class Actividad2PSP {
    
public static void main(String[] args) {
        File directorio=new File("build\\classes\\");
        ProcessBuilder pb = new ProcessBuilder("java", "Actividad2PSPEjecutable");
                    pb.directory(directorio);	
		try {
			
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);			
			Process p = pb.start();
			
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}
    }
