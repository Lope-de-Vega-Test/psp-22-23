package muchoSockets;


import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author grupo con nombre por definir, si eso.
 *
 */
public class HiloSocket extends Thread {

	int id;
	int port;
	boolean token;
	boolean last;

	/**
	 * CONSTRUCTOR
	 * 
	 * @param id=identificador hilo
	 * @param port=puerto      de escucha y puerto de envio
	 * @param token=dato       para saber quien esta enviando señal
	 * @param last=indica      quien es el ultimo
	 */
	HiloSocket(int id, int port, boolean token, boolean last) {
		this.id = id;
		this.port = port;
		this.token = token;
		this.last = last;

	}

	/**
	 * METODO RUN DEL HILO LO QUE SE EJECUTARA CUANDO SE LE LLAME
	 */
	public synchronized void run() {

		try {
			/**
			 * TEMPORIZADOR PARA MANTENER ORDEN DE ENTRADA
			 */
			Thread.sleep(id / 1000);
			/**
			 * INFORMACION DEL HILO QUE APARECE
			 */
			System.out.println(id + " " + port + " " + token + " " + last);
			/**
			 * LOOP INFINITO
			 */
			while (true) {
				/**
				 * COMPROBAR SI EL MIEMBRO TIENE EL TOKEN
				 */
				if (token) {
					/**
					 * ENVIAR SEÑAL
					 */

					try {
						Socket sender;
						/**
						 * COMPROBAR SI EL MIEMBRO ES EL ÚLTIMO DE LA CADENA
						 */
						if (last) {
							/**
							 * PUERTO PRIMERO ES 10001
							 */
							sender = new Socket("localhost", 10001);
						} else {
							sender = new Socket("localhost", port + 1);
						}
						/**
						 * INFORMACIÓN PUERTOS 
						 */
//						System.out.println("Puerto Local: " + sender.getLocalPort());
						System.out.println(id+" Puerto al que envio: " + sender.getPort());

						token = false;
						/**
						 * ESPERA UN TIEMPO DETERMINADO
						 */
						Thread.sleep(id * 1000);
						System.out.println(id + " fin dormir");
						sender.close();

					} catch (Exception e1) {
						System.out.print(".");

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}
					}
				} else {
					/**
					 * ESCUCHAR SEÑAL
					 */
					try {
						ServerSocket reciever = new ServerSocket(port);
						System.out.println(id+" Escuchando en " + reciever.getLocalPort());
						Socket senderMember = reciever.accept();
						token = true;

						reciever.close();
					} catch (Exception e2) {
						System.out.println("El socket no puede escuchar ninguna señal");
					}
				}
			}

		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	}
}
