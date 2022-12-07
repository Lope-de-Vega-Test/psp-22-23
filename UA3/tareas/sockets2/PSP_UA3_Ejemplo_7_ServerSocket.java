package sockets2;

import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ServerSocket {
	
	/**
	 * Metodo main
	 * @param arg
	 * @throws IOException
	 */
	public static void main(String[] arg) throws IOException {
		
		/**
		 * PUERTO USADO PARA COMUNICARNOS
		 */
		int Puerto = 6000;
		
		/**
		 * ABRIR CANAL DE SERVIDOR CON EL PUERTO USADO
		 */
		ServerSocket Servidor = new ServerSocket(Puerto);
		System.out.println("Escuchando en " + Servidor.getLocalPort());
		
		/**
		 * ABRIR EL CANAL A LA ESPERA DE ESCUCHAR CON CLIENTE 1
		 */
		Socket cliente1 = Servidor.accept();
		
		/**
		 * CREAR FLUJOS DE DATOS PARA RECIBIR MENSAJE
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
//    DataInputStream flujo_entrada=new DataInputStream(cliente1.getInputStream());
//    String mensaje=flujo_entrada.readUTF();
//    System.out.println(mensaje);
		
		System.out.println("\n Conexion realizada con cliente 1");
		
		/**
		 * DATOS INFORMATIVOS
		 */
		InetAddress i = cliente1.getInetAddress();
		System.out.println("Puerto Local: " + cliente1.getLocalPort());
		System.out.println("Puerto Remoto: " + cliente1.getPort());//SEGÚN DISPONIBILIDAD
		System.out.println("Nombre Host/IP: " + cliente1.getInetAddress());
		System.out.println("Host Remoto: " + i.getHostName().toString());
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

		System.out.println("\n Escuchando en " + cliente1.getLocalPort());
		
		/**
		 * ABRIR EL CANAL A LA ESPERA DE ESCUCHAR CON CLIENTE 2
		 */
		Socket cliente2 = Servidor.accept();
		
		System.out.println("\n Conexion realizada con cliente 2");
		
		/**
		 * DATOS INFORMATIVOS
		 */
		InetAddress i2 = cliente2.getInetAddress();
		System.out.println("Puerto Local: " + cliente2.getLocalPort());
		System.out.println("Puerto Remoto: " + cliente2.getPort());//SEGÚN DISPONIBILIDAD
		System.out.println("Nombre Host/IP: " + cliente2.getInetAddress());
		System.out.println("Host Remoto: " + i2.getHostName().toString());
		System.out.println("IP Host Remoto: " + i2.getHostAddress().toString());

		System.out.println("Cerrando flujo");
		
		/**
		 * CERRAR EL CANAL DEL SERVIDOR
		 */
		Servidor.close();
	}

}
