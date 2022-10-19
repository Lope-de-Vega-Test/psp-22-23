import java.io.*;
import java.util.Scanner;

public class LanzadorTarea2 {

	public static void main(String[] args) throws IOException {
        Scanner entrada = new Scanner(System.in);
		String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
		File directorio = new File(dir);
		ProcessBuilder pb = new ProcessBuilder("java","Tarea2.java");
		pb.directory(directorio);

		

		// se ejecuta el proceso
		Process p = pb.start();

		// escritura -- envia entrada 
		OutputStream os = p.getOutputStream();
                String texto;
                String fin = "\n";
                System.out.println("Escribe una frase: ");
                texto = entrada.nextLine();
                texto=texto+fin;
		os.write(texto.getBytes());
		os.flush(); // vac�a el buffer de salida

		// lectura -- obtiene la salida
		InputStream is = p.getInputStream();
		int c;
		while ((c = is.read()) != -1)
			System.out.print((char) c);
		is.close();

		// COMPROBACION DE ERROR - 0 bien - 1 mal
		int exitVal;
		try {
			exitVal = p.waitFor();
			System.out.println("Valor de Salida: " + exitVal);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			InputStream er = p.getErrorStream();
			BufferedReader brer = new BufferedReader(new InputStreamReader(er));
			String liner = null;
			while ((liner = brer.readLine()) != null)
				System.out.println("ERROR >" + liner);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}