import java.io.IOException;

/**
 *
 * @author damik
 */
public class tarea3_2 {

    public static void main(String[] args) {
                
        //Process builder con la ruta del archivo
        
        ProcessBuilder pb = new ProcessBuilder("java", "D:\\DAM\\PSP\\tareaPrueba2\\src\\tarea3_1.java","PepeViyuela");
		
	try {
            // Redireccion de la entrada y salida del proceso lanzado a mi mismo
            pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
            // Ejecutamos el proceso	
            Process p = pb.start();

            try {
                // Usamos un wait para que el programa espere a que se ejecute el otro y no de problemas
		p.waitFor();
                
                //Mostramos lo que el otro programa genera
                System.out.println("Devolucion: "+p.exitValue());
                                
                //Control de errores
		} catch (InterruptedException e1) {
				e1.printStackTrace();
		}
         
        //Control de errores
	} catch (IOException e2) {
			e2.printStackTrace();
	}
    }
}
