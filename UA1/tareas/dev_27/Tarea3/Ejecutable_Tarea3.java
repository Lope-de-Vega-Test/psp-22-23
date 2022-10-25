import java.io.File;
import java.io.IOException;

public class Ejecutable_Tarea3 {
    
    public static void main(String args[]) throws IOException{
		// Declaracion de la direccion del ejecutable del programa a ejecutar	
        final String dir= System.getProperty("user.dir");
		File directorio = new File(dir);
		
		// Localizamos el ejecutable con el processbuilder
		ProcessBuilder pb = new ProcessBuilder("java", "App.java","argumento");	
		pb.directory(directorio);
				
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();
                        System.out.println("Proceso iniciado"); 
			// Debemos esperar a la finalizacion del programa/proceso lanzado.
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			//Control de errores
			e1.printStackTrace();
		}
	}
}


