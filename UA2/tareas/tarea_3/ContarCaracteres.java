package tarea_3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContarCaracteres {

	public synchronized String crearRuta(String args) {
		String nombreFichero;
		Path currentRelativePath = Paths.get("");
		String ruta = currentRelativePath.toAbsolutePath().toString();

		// RUTA CREADA A PARTIR DEL ARGUMENTO DADO 
		//EL ARCHIVO A CONTAR SE SUPONE DENTRO DEL MISMO PAQUETE QUE EL .CLASS
		nombreFichero = ruta.concat("\\tarea_3\\" + args);
		return nombreFichero;
	}

	public synchronized int abrirYContar(String file) {
		int contador = 0;
		FileReader fr = null;
		BufferedReader br = null;
		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			// LEER PRIMER CARACTER QUE DEVOLVERA EL ENTERO CORRESPONDIENTE QUE DEFINE AL
			// CARACTER

			int caract = br.read();
			/***************** BUCLE DE LECTURA *****************************/

			// MIENTRAS HAYA CARACTERES CONTARA LAS VECES QUE HACE EL BUCLE
			while (caract != -1) {
				contador++;
				// MOSTRAR CARACTERES
//       		 System.out.print((char)caract);
				// LEE EL SIGUIENTE CARACTER
				caract = br.read();
			}
		} catch (FileNotFoundException e) {
			// NO ENCUENTRA FICHERO
			System.out.println("Error: Fichero no encontrado");
			// MENSAJE DE ERROR
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// ERROR GENERAL
			System.out.println("Error de lectura del fichero");
			System.out.println(e.getMessage());
		} finally {
			// EJECUCION FINAL SI O SI TRAS EL TRY/CATCH
			try {
				// CERRAR ARCHIVO Y BUFFER
				if (fr != null) {
					br.close();
					fr.close();
				}
			} catch (Exception e) {
				// ERROR AL CERRAR EL FICHERO
				System.out.println("Error al cerrar el fichero");
				System.out.println(e.getMessage());
			}
		}

		return contador;
	}

	public synchronized String mostrarCaracteres(int contador) {
		String mensaje;
		mensaje = " " + contador + " caracteres";
		System.out.println("");

		return mensaje;
	}
//////////////////////////////////////////////////////////////////////
	/********************************
	 * PRUEBA SIN HILOS
	 ************************************************/
//	public static void main(String[] args) {
//		/*****************
//		 * CREACION RUTA DEL ARCHIVO A CONTAR
//		 *****************************/
//		String nombreFichero=crearRuta(args[0]);
//		/*****************
//			 * ABRIR Y GUARDAR EN BUFFER EL ARCHIVO
//			 *****************************/
//		int contador=abrirYContar(nombreFichero);
//		/*****************
//		 * MOSTRAR LAS VECES QUE HABIA CARACTERES DISPONIBLES
//		 *****************************/
//		
//			System.out.println(mostrarCaracteres(contador));
//		
//	}
}
