package sockets3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
	
	/**
	 * CONSTRUCTOR/GETTER/SETTER
	 */
	private String mensaje;

	public Cliente() {
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
		Socket Cliente1 = new Socket(Host, Puerto);
		
		/**
		 * PEDIR MENSAJE 
		 */
		Scanner scan =new Scanner(System.in);
		System.out.println("Mensaje a enviar: ");
		String miMensaje=scan.nextLine();
		
		/**
		 * CREAR FLUJOS DE DATOS PARA MANDAR MENSAJE POR ARGUMENTOS INCLUIDOS EN EL CMD
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */		
		DataOutputStream flujo_salida = new DataOutputStream(Cliente1.getOutputStream());
		Cliente mio = new Cliente();
		mio.setMensaje(miMensaje);
		flujo_salida.writeUTF(mio.getMensaje());
		flujo_salida.close();
		
		
		
		/**
		 * CERRAR EL CANAL
		 */
		Cliente1.close();
		
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
		 * CREAR FLUJOS DE DATOS PARA RECIBIR MENSAJE
		 * (DESCOMENTAR PARA USAR MENSAJERIA VIA IP)
		 */
    DataInputStream flujo_entrada=new DataInputStream(cliente1Vuelta.getInputStream());
    String mensaje=flujo_entrada.readUTF();
    System.out.println(mensaje);
    
    System.out.println("\n Conexion realizada con cliente de vuelta");

    ServidorVuelta.close();
	}

}