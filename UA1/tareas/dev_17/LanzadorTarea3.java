import java.io.*;
import java.util.Scanner;

public class LanzadorTarea3 {

	public static void main(String[] args) throws IOException {
        String texto;
        String fin = "\n";
        //si no agregamos ningun argumento inicializamos texto com oun string vacio, si no asignamos
        //el args a la variable texto
        if(args.length==0){
            texto="";
        }else{
            texto = args[0];
        }
        ProcessBuilder pb;


		String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
		File directorio = new File(dir);
        //si args esta vacio en Lanzar, ejecutamos el programa sin añadirle argumentos
        //si no estña vacio, lo ejecutamos con los arguentos tecogidos en la variable texto.
        if(args.length==0){
		    pb = new ProcessBuilder("java","Tarea3.java");
        }else{
            pb = new ProcessBuilder("java","Tarea3.java",""+texto);
        }
        
		pb.directory(directorio);


		// se ejecuta el proceso
		Process p = pb.start();

		// escritura -- envia entrada 
		OutputStream os = p.getOutputStream();
                System.out.println(texto);
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