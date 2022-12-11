import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ServerSocket {
	public static void main(String[] arg) throws IOException {
		
		//Puerto al cual el server se conectará 
		int Puerto = 6000;
		
        //Canal cual el servidor se comunicará con el puerto
		ServerSocket Servidor = new ServerSocket(Puerto);
		System.out.println("Escuchando en " + Servidor.getLocalPort());
		
        //Primer cliente al cual el servidor permitira 
		Socket cliente1 = Servidor.accept();
		
		System.out.println("Cliente 1 conexion realizada exitosamente");
		
    //Informacion del cliente 1
		InetAddress i = cliente1.getInetAddress();
		System.out.println("Puerto Local: " + cliente1.getLocalPort());
		System.out.println("Puerto Remoto: " + cliente1.getPort());
		System.out.println("Nombre Host/IP: " + cliente1.getInetAddress());
		System.out.println("Host Remoto: " + i.getHostName().toString());
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

		System.out.println("\n Escuchando en " + cliente1.getLocalPort());
		
        //Segundo cliente
		Socket cliente2 = Servidor.accept();
		
		System.out.println("Cliente 2 conexion realizada exitosamente");
		
    //Informacion del cliente 2
		InetAddress i2 = cliente2.getInetAddress();
		System.out.println("Puerto Local: " + cliente2.getLocalPort());
		System.out.println("Puerto Remoto: " + cliente2.getPort());
		System.out.println("Nombre Host/IP: " + cliente2.getInetAddress());
		System.out.println("Host Remoto: " + i2.getHostName().toString());
		System.out.println("IP Host Remoto: " + i2.getHostAddress().toString());

		System.out.println("Cerrando servidor");
		
    //Se cierra el servidor una vez se hayan metido los dos clientes remotos
		Servidor.close();
	} //Main

}
