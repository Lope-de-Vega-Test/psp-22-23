package practica2servicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class ProcesoMain {

	public static void main(String[] args) {
		Path currentRelativePath = Paths.get("bin");
		String ruta = currentRelativePath.toAbsolutePath().toString();
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("java", "practica2servicios.Lectura").directory(new File(ruta));
		pb.redirectErrorStream(true);
		pb.inheritIO();
		try {
			Process p = pb.start();
			int errorCode = p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
