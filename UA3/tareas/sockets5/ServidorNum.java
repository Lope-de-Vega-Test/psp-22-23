package sockets5;

import java.io.*;
import java.net.*;

public class ServidorNum {
	static long numero;
	static long cuadrado;
	static long cubo;

	/**
	 * Metodo main
	 * 
	 * @param arg
	 * @throws IOException
	 */
	public static void main(String[] arg) throws IOException {

		/**
		 * PUERTO USADO PARA COMUNICARNOS
		 */
		int Puerto = 6000;
		String cuadradoADevolver;
		String cuboADevolver;
		try {
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
			 * CREAR FLUJOS DE DATOS PARA RECIBIR MENSAJE (DESCOMENTAR PARA USAR MENSAJERIA
			 * VIA IP)
			 */
			DataInputStream flujo_entrada = new DataInputStream(cliente1.getInputStream());
			String mensaje = flujo_entrada.readUTF();
			System.out.println("Numero a operar: " + mensaje);

			numero = Long.parseLong(mensaje);

			System.out.println("\n Conexion realizada con cliente 1");

			Servidor.close();
		} catch (Exception e) {
			System.out.println("Fallo cosa recibir");
		}
		cuadrado = numero * numero;
		cubo = numero * numero * numero;
		cuadradoADevolver = Long.toString(cuadrado);
		cuboADevolver = Long.toString(cubo);
		/**
		 * IP REMOTA/IP PROPIA O LOCALHOST(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
//		String Host = "10.2.1.148";
		String Host = "localhost";
		try {
			/**
			 * ABRIR EL CANAL DE COMUNICACION CON LA IP Y CON EL PUERTO
			 */
			Socket mensajeVuelta = new Socket(Host, Puerto);
//			System.out.println("prueba1");
			/**
			 * CREAR FLUJOS DE DATOS PARA MANDAR MENSAJE POR ARGUMENTOS INCLUIDOS EN EL CMD
			 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
			 */
			DataOutputStream flujo_salida = new DataOutputStream(mensajeVuelta.getOutputStream());
//			System.out.println("flujo salida");
			flujo_salida.writeUTF(cuadradoADevolver);
			flujo_salida.writeUTF(cuboADevolver);
			flujo_salida.close();

			/**
			 * CERRAR EL CANAL
			 */
			mensajeVuelta.close();
		} catch (Exception e1) {
			System.out.println("Fallo cosa enviar");
		}
	}

}
