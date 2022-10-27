/*
 * @author Alfonso Alcaraz
 */

package procesos2java;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExeIndex {

	public static void main(String[] args) {

		Path currentRelativePath = Paths.get("");
		String ruta = currentRelativePath.toAbsolutePath().toString();
		/*
		 * Si el número de argumentos es < 1 debe devolver 1
		 * Si el argumento es una cadena debe devolver 2
		 * Si el argumento es un número entero menor que 0 debe devolver 3
 		 * En cualquier otro caso debe devolver 0
		 */
		ProcessBuilder pb = new ProcessBuilder("java", "procesos2java.Index"/*ARGUMENTO AQUI*/);
		pb.directory(new File(ruta));

		try {
			pb.redirectErrorStream(true);
			pb.inheritIO();

			Process p = pb.start();

			p.waitFor();

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

}
