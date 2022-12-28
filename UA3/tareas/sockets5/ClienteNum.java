package sockets5;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClienteNum {

	/**
	 * Metodo main
	 * 
	 * @param arg USAMOS LOS ARGUMENTOS PARA MANDAR MENSAJES
	 * @throws IOException
	 */
	public static void main(String[] arg) throws IOException {
		Numeros num = new Numeros();
		/**
		 * IP REMOTA/IP PROPIA O LOCALHOST(DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
//		String Host = "10.2.1.148";
		String Host = "localhost";

		/**
		 * PUERTO USADO PARA COMUNICARNOS
		 */
		int Puerto = 6000;
		try {
		/**
		 * ABRIR EL CANAL DE COMUNICACION CON LA IP Y CON EL PUERTO
		 */
		Socket Cliente1 = new Socket(Host, Puerto);

		/**
		 * PEDIR MENSAJE
		 */
		Scanner scan = new Scanner(System.in);
		System.out.println("Introduce un numero: ");
		String miMensaje = scan.nextLine();

		/**
		 * CONTROL DE ERRORES NUMERO MENOR 0
		 */
		while(Integer.parseInt(miMensaje)<0) {
			System.out.println("Número incorrecto");
			System.out.println("Introduce un numero: ");
			 miMensaje = scan.nextLine();
		}
		
		
		/**
		 * ALMACENAR EN CLASE NUMEROS
		 */
		
		num.setNumero(Integer.parseInt(miMensaje));
//		System.out.println("prueba1");
		/**
		 * CREAR FLUJOS DE DATOS PARA MANDAR MENSAJE POR ARGUMENTOS INCLUIDOS EN EL CMD
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
		DataOutputStream flujo_salida = new DataOutputStream(Cliente1.getOutputStream());
//		System.out.println("prueba2");

		flujo_salida.writeUTF(miMensaje);
//		System.out.println("prueba3");
		flujo_salida.close();
//		System.out.println("prueba4");

		/**
		 * CERRAR EL CANAL
		 */
		Cliente1.close();
//		System.out.println("prueba5");
		}catch(Exception e) {
			System.out.println("Fallo cosa enviar");
		}
		try {
		/**
		 * ABRIR CANAL DE SERVIDOR CON EL PUERTO USADO
		 */
		ServerSocket ServidorVuelta = new ServerSocket(Puerto);
		System.out.println("Escuchando en " + ServidorVuelta.getLocalPort());

		/**
		 * ABRIR EL CANAL A LA ESPERA DE ESCUCHAR CON CLIENTE 1
		 */
		Socket cliente1Vuelta = ServidorVuelta.accept();

		/**
		 * CREAR FLUJOS DE DATOS PARA RECIBIR MENSAJE (DESCOMENTAR PARA USAR MENSAJERIA
		 * VIA IP)
		 */
		DataInputStream flujo_entrada = new DataInputStream(cliente1Vuelta.getInputStream());
		String mensaje = flujo_entrada.readUTF();
		System.out.println("Cuadrado del numero: " + mensaje);
		String mensaje1 = flujo_entrada.readUTF();
		System.out.println("Cubo del numero: " + mensaje1);

		System.out.println("\n Conexion realizada con cliente de vuelta");
		/**
		 * ALMACENAR EN CLASE NUMEROS
		 */
		num.setCuadrado(Long.parseLong(mensaje));
		num.setCubo(Long.parseLong(mensaje1));

		System.out.println(num.toString());
		ServidorVuelta.close();

		}catch(Exception e1) {
			System.out.println("Fallo cosa recibir");
		}
	}

}
