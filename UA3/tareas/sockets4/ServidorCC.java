package sockets4;

import java.io.*;
import java.net.*;


public class ServidorCC {

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
		 * ABRIR EL CANAL A LA ESPERA DE ESCUCHAR CON CLIENTE
		 */
		Socket cliente1 = Servidor.accept();
		
		/**
		 * CREAR FLUJOS DE DATOS PARA RECIBIR MENSAJE
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
    DataInputStream flujo_entrada=new DataInputStream(cliente1.getInputStream());
    String mensaje=flujo_entrada.readUTF();
    mensaje=mensaje.toUpperCase();
    System.out.println(mensaje);
		
		System.out.println("\n Conexion realizada con cliente");
		
		
		
		
		
		/**
		 * CERRAR EL CANAL DEL SERVIDOR
		 */
		Servidor.close();
	}

}