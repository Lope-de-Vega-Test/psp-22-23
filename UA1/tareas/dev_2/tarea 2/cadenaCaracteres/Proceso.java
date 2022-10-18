package cadenaCaracteres;

import java.io.IOException;
import java.nio.file.*;
import java.io.*;

public class Proceso {

	public static void main(String[] args) {
		try {

			Path currentRelativePath = Paths.get("");
			String ruta = currentRelativePath.toAbsolutePath().toString();

			System.out.println("La ruta desde donde ejecutas tu comando es: " + ruta);

			ProcessBuilder pb = new ProcessBuilder();
			pb.command("java", "cadenaCaracteres.Index").directory(new File(ruta));
			pb.redirectErrorStream(true);
			pb.inheritIO();

			Process ps = pb.start();

			int errCode = ps.waitFor();
			System.out.println("Error al ejecutar el comando? " + (errCode == 0 ? "No" : "Sí"));

			ps.destroy();

		} catch (IOException e) {
			System.err.println("Error de lectoescritura");
		} catch (InterruptedException ex) {
			System.err.println("Problema con los hilos");
		}

	}

}
