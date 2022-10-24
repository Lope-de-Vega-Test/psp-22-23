/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3.pkg2;

import java.io.IOException;

/**
 *
 * @author Javier
 */
public class Tarea32 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // ProcessBuilder que contiene el archivo a ejecutar
		ProcessBuilder pb = new ProcessBuilder("java","-jar", "C:\\Users\\Javier\\Desktop\\2DAM\\Tarea3-1\\dist\\Tarea3-1.jar","9");
		
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();
                        

			// Debemos esperar a la finalizacion del programa/proceso lanzado.
			try {
				p.waitFor();
                                //Muestra por pantalla lo que el programa abierto devuelve con System.exit()
                                System.out.println("El programa devuelve: "+p.exitValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        }
    }
    

