package sockets1;
/*
 * @author AlfonsoAlcaraz
 * */
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

public class Socket1 {
	/**
	 * Metodo main
	 * @param args
	 */
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);/**entrada de datos*/
		URL url;/**direccion url*/
		InetAddress dir = null;/**direccion ip*/
		String direccion;/**variable de entrada de texto*/
		try {
			/**
			 * Repetir hasta ingresar "localhost" 
			 */
			do {

				System.out.println("introduce una URL o IP");
				direccion = entrada.nextLine();

				if (isValid(direccion)) {
					url = new URL("http://" + direccion);
					Visualizar(url);
				} else {
					dir = InetAddress.getByName(direccion);
					pruebaMetodos(dir);

				}
			} while (!direccion.equals("localhost"));
			
		} catch (MalformedURLException e) {/**si falla la entrada por url*/
			System.out.println(e);
		} catch (UnknownHostException e1) {/**si falla la entrada por ip*/
			e1.printStackTrace();
		}
	}
/**
 * Visualiza informacion de la url que le pasamos
 * @param url
 */
	private static void Visualizar(URL url) {
		/**
		 * Metodos de la clase URL
		 */
		System.out.println("\tURL completa: " + url.toString());
		System.out.println("\tgetProtocol(): " + url.getProtocol());
		System.out.println("\tgetHost(): " + url.getHost());
		System.out.println("\tgetPort(): " + url.getPort());
		System.out.println("\tgetFile(): " + url.getFile());
		System.out.println("\tgetUserInfo(): " + url.getUserInfo());
		System.out.println("\tgetPath(): " + url.getPath());
		System.out.println("\tgetAuthority(): " + url.getAuthority());
		System.out.println("\tgetQuery(): " + url.getQuery());
		System.out.println("\tgetDefaultPort(): " + url.getDefaultPort());
		System.out.println("==================================================");
	}
/**
 * Visualiza informacion de la ip que le pasamos
 * @param dir
 */
	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName():  " + dir);
		InetAddress dir2;/**objeto ip temporal*/
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {/**si falla la entrada por ip*/
			e.printStackTrace();
		}

		/**
		 * Metodos de la clase InetAddress(IP)
		 */
		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
		System.out.println("==================================================");
	}
/**
 * Comprueba si la url entregada es valida true/false
 * @param url
 * @return
 */
	public static boolean isValid(String url) {
		/**Intenta crear una irl valida */
		try {
			new URL(url).toURI();
			return true;
		}

		
		catch (Exception e) {/**Si no es valida devuelv false*/
			return false;
		}
	}
}
