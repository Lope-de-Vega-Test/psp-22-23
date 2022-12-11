import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ClientSocket {
    
	public static void main(String[] arg) throws IOException {
		//Host del cliente
		String Host = "localhost";
		
		//Puerto al cual el cliente se conectara
		int Puerto = 6000;
		
    //Canal de comunicacion entre el host y el puerto
		Socket Cliente = new Socket(Host, Puerto);
		
        //Informacion del cliente
		InetAddress i = Cliente.getInetAddress();
		System.out.println("Puerto Local: " + Cliente.getLocalPort());
		System.out.println("Puerto Remoto: " + Cliente.getPort());
		System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
		System.out.println("Host Remoto: " + i.getHostName().toString());
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
		System.out.println();
		
        //Se cierra la conexion
		Cliente.close();
	} //Main

}
