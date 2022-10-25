package dev_8;

import java.io.File;
import java.io.IOException;

public class ua1tarea2_CrearProceso {
    
    /**
     * @param args
     */
    public static void main(String[] args) throws IOException {


        String dir= System.getProperty("user.dir");
		File directorio = new File(dir);
		
		// Localizamos el ejecutable con el processbuilder
		ProcessBuilder pb = new ProcessBuilder("java", "ua1tarea2.java");	
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    }
}
