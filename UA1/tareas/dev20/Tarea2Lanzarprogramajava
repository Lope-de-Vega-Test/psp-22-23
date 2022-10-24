package lanzarprogramajava;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Lucía Luna
 */
public class Lanzarprogramajava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
		
        System.out.println("Hello world 1");

// Declaracion de la direccion del ejecutable del programa a ejecutar		
		File directorio = new File("build\\classes\\");
		
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java", "lanzarprogramajava.Programajava");	// Estudiar como hacerlo con paquetes: com.ceslopedevega....
		// Esta sentencia debe estar cuadrada con la ruta en la que se encuentran los ejecutables compilados: .class, .jar o lo que sea
		pb.directory(directorio);
				
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();

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
