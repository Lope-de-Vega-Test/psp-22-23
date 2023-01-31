import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

/*
 https://sites.google.com/site/nikos3194rfcindex/home/old-school/ftp
 ftp.uv.es
 ftp.unavarra.es
 ftp.rediris.es
 ftp.uma.es
 ftp.udc.es
 ftp.dit.upm.es
 ftp.freenet.de
 
 */
public class ua4ej1 {
	public static void main(String[] args) throws SocketException, IOException {
		Scanner entrada=new Scanner(System.in);
		FTPClient cliente = new FTPClient();
		System.out.println("Introduce el servidor al que quiera acceder");
		String url=entrada.nextLine();
		String servFTP = url; // servidor FTP
		System.out.println("Nos conectamos a: " + servFTP);
		cliente.connect(servFTP);

		// respuesta del servidor FTP
		System.out.print(cliente.getReplyString());
		// c�digo de respuesta
		int respuesta = cliente.getReplyCode();

		System.out.println("Respuesta: "+respuesta);
		
		// comprobaci�n del c�digo de respuesta
		if (!FTPReply.isPositiveCompletion(respuesta)) {
			cliente.disconnect();
			System.out.println("Conexión rechazada: " + respuesta);
			System.exit(0);
		}
		// desconexi�n del servidor FTP
		cliente.disconnect();
		System.out.println("Conexión finalizada.");
	}

}
