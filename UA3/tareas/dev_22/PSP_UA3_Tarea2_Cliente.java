/**
 * @mainpage                    UA3 Ejercicio 2 Client Side
 *                                          
 */
package psp_ua3_tarea2_cliente;

import java.util.Scanner;
import java.io.*;
import java.net.*;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea2_Cliente {

    public static void main(String[] args) throws IOException {   
        Scanner entrada = new Scanner(System.in);
        String Host = "";
	int Puerto = 6000;/**< Puerto de conexión */
        
        System.out.println("Introduce la dirección del host");
        Host = entrada.nextLine();/**< Pedimos url del servidor, en casos de prueba sería localhost */

	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);/**< Conexión al servidor por el puerto indicado */

	
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());/**< Flujo de salida del usuario */

	
        System.out.println("Puerto local: "+Cliente.getLocalPort()+"\nPuerto remoto: "+Cliente.getPort()+"\nURL de conexion: "+Host);/**< Esto se muestra en el lado del usuario */
	flujoSalida.writeUTF("Puerto local: "+Cliente.getLocalPort()+"\nPuerto remoto: "+Cliente.getPort());/**< Esto se envía al servidor y se mostrará allí */

	
	flujoSalida.close();/**< Cerramos flujo de salida de datos y desconectamos al cliente */
	Cliente.close();
    }
    
}
