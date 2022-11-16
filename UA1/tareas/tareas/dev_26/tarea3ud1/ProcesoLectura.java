
public class ProcesoLectura {

	public static void main(String[] args) {
		
		if(args.length == 0) {
			System.exit(1);
		}
		//Creación del proceso
		ProcessBuilder pb = new ProcessBuilder("java","Argumentos",args[0]);
		
		try {
			//Comenzamos el proceso
			Process p = pb.start();
			//Esperamos a que termine el proceso
			p.waitFor();
			//Devolvemos el valor del proceso al terminar
			System.out.println(p.exitValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}