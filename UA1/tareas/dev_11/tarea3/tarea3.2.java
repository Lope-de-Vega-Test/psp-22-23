/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package act3_psp;

import java.io.IOException;

/**
 *
 * @author damik
 */
public class Act3_psp_ejecutar {

    public static void main(String[] args) {
                
        //Process builder con la ruta del archivo
        
        ProcessBuilder pb = new ProcessBuilder("java", "D:\\DAM\\Acceso a datos\\act3_psp\\src\\act3_psp\\Act3_psp.java","PepeViyuela");
		
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
