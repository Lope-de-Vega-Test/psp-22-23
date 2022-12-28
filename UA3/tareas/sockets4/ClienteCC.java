package sockets4;

import java.io.*;
import java.net.*;
import java.util.*;


public class ClienteCC {


	/**
	 * CONSTRUCTOR/GETTER/SETTER(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
	 */
	private String mensaje;

	public ClienteCC() {
		mensaje = "";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
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
		DataOutputStream flujo_salida = new DataOutputStream(Cliente.getOutputStream());
		ClienteCC mio = new ClienteCC();
		mio.setMensaje(escribir());
		flujo_salida.writeUTF(mio.getMensaje());
		flujo_salida.close();
		
		
		
		/**
		 * CERRAR EL CANAL
		 */
		Cliente.close();
	}
	static public String escribir() {
		Scanner scan=new Scanner(System.in);
		String mensaje="";
		System.out.println("Comienza a escribir");
		System.out.println("Para finalizar escribe * y pulse ENTER");
		/**
		 * MIENTRAS NO SE INTRODUZCA EL CARACTER * AL FINAL SE REPITE
		 */
		while (!mensaje.endsWith("*")) {
			mensaje = mensaje + scan.nextLine();

		}
		/**
		 * SI * ESTA AL FINAL SE DEVUELVE EL STRING SIN EL ULTIMO CARACTER
		 */
		if (mensaje.endsWith("*")) {
			mensaje = mensaje.substring(0, (mensaje.length() - 1));
		}
		return mensaje;
	}

}
