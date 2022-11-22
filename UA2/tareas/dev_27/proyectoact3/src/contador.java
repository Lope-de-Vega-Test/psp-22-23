import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class contador {

	public synchronized String rutadirectorio(String args) {
		// Creamos una ruta añadiendo antes el directorio actual
		String nombreFichero;
		Path currentRelativePath = Paths.get("");
		String ruta = currentRelativePath.toAbsolutePath().toString();
		nombreFichero = ruta.concat("/" + args);
		return nombreFichero;
	}

	public synchronized int abrir_contador(String file) {
		int contador = 0;
		FileReader fr = null;
		BufferedReader br = null;

		try {

			fr = new FileReader(file);
			br = new BufferedReader(fr);
			int caract = br.read();
			// Creamos un bucle que seguirá leyendo caracteres hasta que termine el texto
			while (caract != -1) {
				contador++;
				caract = br.read();
			}

		} catch (FileNotFoundException e) {
			// Con el catch indicamos que no se ha podido encontrar el fichero en el caso de
			// que la ruta no sea
			// correcta
			System.out.println("Fichero no encontrado");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// En el caso de que se encuentre el fichero, pero no se pueda leer mostraremos
			// este error
			System.out.println("No se puede leer");
			System.out.println(e.getMessage());
		} finally {
			// Si no ha ocurrido ningun error ejecutamos el codigo final
			try {
				// Cerramos el archivo y el bufer
				if (fr != null) {
					br.close();
					fr.close();
				}
			} catch (Exception e) {
				// Mostramos este error en el caso de no poder cerrar el fichero correctamente
				System.out.println("No se puede cerrar el fichero");
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
}