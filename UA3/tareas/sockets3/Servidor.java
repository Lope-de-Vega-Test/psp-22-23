package sockets3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
	
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
    DataInputStream flujo_entrada=new DataInputStream(cliente1.getInputStream());
    String mensaje=flujo_entrada.readUTF();
    System.out.println(mensaje);
		
    String mensajeADevolver=mensaje.toUpperCase();
    
		System.out.println("\n Conexion realizada con cliente 1");

		Servidor.close();
		
		/**
		 * IP REMOTA/IP PROPIA O LOCALHOST(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
//		String Host = "10.2.1.148";
		String Host = "localhost";
		
		/**
		 * ABRIR EL CANAL DE COMUNICACION CON LA IP Y CON EL PUERTO
		 */
		Socket mensajeVuelta = new Socket(Host, Puerto);
		
		/**
		 * CREAR FLUJOS DE DATOS PARA MANDAR MENSAJE POR ARGUMENTOS INCLUIDOS EN EL CMD
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */		
		DataOutputStream flujo_salida = new DataOutputStream(mensajeVuelta.getOutputStream());
//		Cliente mio = new Cliente();
//		mio.setMensaje(mensajeADevolver);
		flujo_salida.writeUTF(mensajeADevolver);
		flujo_salida.close();
		
		/**
		 * CERRAR EL CANAL
		 */
		mensajeVuelta.close();
	}

}
