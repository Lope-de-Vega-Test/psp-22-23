package ua4tarea1_joseramon;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author JRC
 */
public class Ua4tarea1_JoseRamon {


	public static void main(String[] args) throws SocketException, IOException {

        Scanner sca=new Scanner(System.in);

		FTPClient cliente = new FTPClient();
        System.out.println("Diga a que servidor quiere conectarse");
		String servFTP=sca.nextLine(); // servidor FTP
		System.out.println("Vamos a conectarnos a: " + servFTP);
		cliente.connect(servFTP);

		// Vemos la respuesta que nos da el servidor FTP
		System.out.print(cliente.getReplyString());
		// codigo de respuesta
		int resp = cliente.getReplyCode();

		System.out.println("La respuesta es: "+resp);
		
		// Aquí comprobamos el código de respuesta
		if (!FTPReply.isPositiveCompletion(resp)) {
			cliente.disconnect();
			System.out.println("Conexion rechazada: " + resp);
			System.exit(0);
		}
                
                //Aquí hacemos una desconexion del servidor
		cliente.disconnect();
		System.out.println("Se ha finalizado la conexión");
	}

}
    

