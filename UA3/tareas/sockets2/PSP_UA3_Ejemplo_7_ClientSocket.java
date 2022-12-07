package sockets2;

import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ClientSocket {
	
	/**
	 * CONSTRUCTOR/GETTER/SETTER(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
	 */
//	private String mensaje;

//	public PSP_UA3_Ejemplo_7_ClientSocket() {
//		mensaje = "";
//	}
//
//	public String getMensaje() {
//		return mensaje;
//	}
//
//	public void setMensaje(String mensaje) {
//		this.mensaje = mensaje;
//	}
	
	/**
	 * Metodo main
	 * 
	 * @param arg USAMOS LOS ARGUMENTOS PARA MANDAR MENSAJES
	 * @throws IOException
	 */
	public static void main(String[] arg) throws IOException {
		
		/**
		 * IP REMOTA/IP PROPIA O LOCALHOST(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
//		String Host = "10.2.1.148";
		String Host = "localhost";
		
		/**
		 * PUERTO USADO PARA COMUNICARNOS
		 */
		int Puerto = 6000;
		
		/**
		 * ABRIR EL CANAL DE COMUNICACION CON LA IP Y CON EL PUERTO
		 */
		Socket Cliente = new Socket(Host, Puerto);
		
		/**
		 * CREAR FLUJOS DE DATOS PARA MANDAR MENSAJE POR ARGUMENTOS INCLUIDOS EN EL CMD
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */		
//		DataOutputStream flujo_salida = new DataOutputStream(Cliente.getOutputStream());
//		PSP_UA3_Ejemplo_7_ClientSocket mio = new PSP_UA3_Ejemplo_7_ClientSocket();
//		mio.setMensaje(arg[0]);
//		flujo_salida.writeUTF(mio.getMensaje());
//		flujo_salida.close();
		
		/**
		 * DATOS INFORMATIVOS
		 */
		InetAddress i = Cliente.getInetAddress();
		System.out.println("Puerto Local: " + Cliente.getLocalPort());
		System.out.println("Puerto Remoto: " + Cliente.getPort());
		System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
		System.out.println("Host Remoto: " + i.getHostName().toString());
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
		System.out.println();
		
		/**
		 * CERRAR EL CANAL
		 */
		Cliente.close();
	}

}
