import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.*;

public class ej1socket {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		URL url;
		InetAddress dir = null;
		String direccion;
		try {
			do {
				System.out.println("introduce URL o direccion IP");
				direccion = scan.nextLine();

				if (Comprobante(direccion)) {
					url = new URL("http://" + direccion);
					Metodos(url);
				} else {
					dir = InetAddress.getByName(direccion);
					pruebaMetodos(dir);
				}
			}while (!direccion.equals("localhost"));
			
		} catch (MalformedURLException e) {      /**Error por url*/
			System.out.println(e);
		} catch (UnknownHostException e1) {      /**Error por ip*/
			e1.printStackTrace();
		}
	}

	private static void Metodos(URL url) {

		System.out.println("\tURL completa: " + url.toString());
		System.out.println("\tgetUserInfo(): " + url.getUserInfo());
		System.out.println("\tgetProtocol(): " + url.getProtocol());
		System.out.println("\tgetHost(): " + url.getHost());
		System.out.println("\tgetAuthority(): " + url.getAuthority());
		System.out.println("\tgetPort(): " + url.getPort());
		System.out.println("\tgetDefaultPort(): " + url.getDefaultPort());
		System.out.println("\tgetFile(): " + url.getFile());
		System.out.println("\tgetPath(): " + url.getPath());
		System.out.println("\tgetQuery(): " + url.getQuery());
		System.out.println("------------------------------------------------");
	}


	private static void pruebaMetodos(InetAddress dir) {
		System.out.println("\tMetodo getByName():  " + dir);
		InetAddress dir2;
		try {
			dir2 = InetAddress.getLocalHost();
			System.out.println("\tMetodo getLocalHost(): " + dir2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		System.out.println("\tMetodo getHostName(): " + dir.getHostName());
		System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
		System.out.println("\tMetodo toString(): " + dir.toString());
		System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
		System.out.println("==================================================");
	}

	public static boolean Comprobante(String url) {
		try {
			new URL(url).toURI();
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}