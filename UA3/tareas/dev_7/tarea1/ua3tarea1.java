import java.net.*;
import java.util.Scanner;

public class ua3tarea1{
	  public static void main(String[] args) {
           Scanner sca = new Scanner(System.in);
	   InetAddress dir = null;
           String direccion ="";
           while(!direccion.equals("localhost")){
	   System.out.println("========================================================");
	   try {
                System.out.println("Direccion a buscar: ");
                direccion=sca.nextLine();
		dir = InetAddress.getByName(direccion);
		pruebaMetodos(dir);	
                System.out.println("========================================================");
	  } catch (UnknownHostException e1) {e1.printStackTrace();}
           }
	}// main

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
