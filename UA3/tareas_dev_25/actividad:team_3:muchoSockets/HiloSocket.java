package muchoSockets;

import java.util.*;
/**
 * 
 * @author grupo con nombre por definir, si eso.
 *PIDE UN NUMERO DE SOCKET, EL PRIMERO ENVIARA UNA SEÑAL AL SEGUNDO
 *Y ASI HASTA EL ULTIMO QUE LO DEVOLVERA AL PRIMERO EN BUCLE INFINTO
 */
public class IndexSocket {
	
	/**
	 * METODO MAIN
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * port=puerto escucha/envio
		 * token=indica el primero
		 * last=indica el ultimo
		 */
		int port=10000;
		boolean token, last;
		/**
		 * PIDE NUMERO DE SOCKET/HILOS A EJECUTAR
		 */
		Scanner scan =new Scanner(System.in);
		System.out.println("Cuantos Socket quieres: ");
		int num=Integer.parseInt(scan.nextLine());
		/**
		 * INCIALIZAMOS EL ULTIMO PUERTO PARA EMPEZAR A ESCUCHAR
		 */
		port=10000+num;
		/**
		 * EMPEZAMOS EN EL ULTIMO HILO HASTA EL PRIMERO
		 */
		for(int i=num;i>0;i--) {
			/**
			 * ES EL ULTIMO
			 */
			if(i==num) {
				token=false;
				last=true;
				/**
				 * EL EL PRIMERO
				 */
			}else if(i==1) {
				token=true;
				last=false;
				/**
				 * ES INTERMEDIO
				 */
			}else {
				token=false;
				last=false;
			}
			/**
			 * CREA EL SOCKET/HILO
			 */
			HiloSocket hilo=new HiloSocket(i,port,token,last);
			/**
			 * RESTA UNA POSICIÓN AL PUERTO HASTA LLEGAR AL PRIMERO
			 */
			port--;
			/**
			 * INICIALIZA EL HILO CON SUS RESPECTIVOS PARAMETROS
			 */
			hilo.start();
			
		}
	}
}