import java.net.*;
import java.util.Scanner;

public class ua3tarea1 {
	  public static void main(String[] args) {
		
        String url;
	    InetAddress dir = null;
        Scanner sca = new Scanner(System.in);
    do{
        System.out.println("Introduce una URL o IP: ");
        url = sca.nextLine();
	    try {
	    System.out.println("========================================================");
		System.out.println("SALIDA PARA UNA URL:");
		dir = InetAddress.getByName(url);
		pruebaMetodos(dir);

	     System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
	     InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
	     for (int i = 0; i < direcciones.length; i++)
	          System.out.println("\t\t"+direcciones[i].toString());

		System.out.println("========================================================");

	    }catch (UnknownHostException e1) {System.out.println("Ha ocurrido un error...");}
	}while(!url.equals("localhost"));
};





	private static void pruebaMetodos(InetAddress dir) {
	      System.out.println("\tMetodo getByName():  " + dir);
		InetAddress dir2;
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}

		// USAMOS METODOS DE LA CLASE
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
		}//pruebaMetodos
}//fin