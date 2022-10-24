/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3psp2;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
public class Tarea3psp2 {



    public static void main(String[] args) throws IOException {
        
 	// Declaracion de la direccion del ejecutable del programa a ejecutar		
		//File directorio = new File(".\\bin");
		
		// DefiniciAn del ProcessBuilder encargado de localizar el ejecutable
		ProcessBuilder pb = new ProcessBuilder("java","-jar", "C:\\Users\\lucia\\OneDrive\\Escritorio\\DAM2\\programacion de procesos y servicios\\1º trimestre\\Tarea3psp\\dist\\tarea3psp.jar","hola");	
                // Estudiar como hacerlo con paquetes: com.ceslopedevega....
		// Esta sentencia debe estar cuadrada con la ruta en la que se encuentran los ejecutables compilados: .class, .jar o lo que sea
		//pb.directory(directorio);
				
		try {
			// Redireccion de la entrada y salida del proceso lanzado a mi mismo
			pb.redirectInput(ProcessBuilder.Redirect.INHERIT);
			pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
			
			// El proceso se ejecuta			
			Process p = pb.start();
                             
                        // Debemos esperar a la finalizacion del programa/proceso lanzado.
			try {
				p.waitFor();
                                System.out.println("Devuelve: "+p.exitValue());
			} catch (InterruptedException e) {
                       
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
    } 
}