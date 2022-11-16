
package tarea22;
import java.io.IOException;
/**
 *
 * @author Javier
 */
public class Tarea22 {
    //funcion main del archivo que previene de errores
    public static void main(String[] args) throws IOException, InterruptedException {
		
		// ProcessBuilder que contiene el archivo a ejecutar
		ProcessBuilder pb = new ProcessBuilder("java","-jar", "C:\\Users\\Javier\\Desktop\\2DAM\\Tarea 2-1\\dist\\Tarea_2-1.jar");
		
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
			e1.printStackTrace();
		}
        }
    
}
