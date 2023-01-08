/**
 * @mainpage                    UA3 Ejercicio 3 (Cliente)
 *                                          
 */
package psp_ua3_tarea3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea3 {

    public static void main(String[] args) throws IOException {   
        Scanner entrada = new Scanner(System.in);
        String Host = "", mensaje = "";
	int Puerto = 6000;/**< Puerto de conexión */
        
        System.out.println("Introduce el mensaje");
        mensaje = entrada.nextLine();/**< Aquí tomamos el mensaje a convertir */
        
        System.out.println("Introduce la dirección del host");
        Host = entrada.nextLine();/**< Pedimos url del servidor, en casos de prueba sería localhost */

	System.out.println("PROGRAMA CLIENTE INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);/**< Conexión al servidor por el puerto indicado */

	DataInputStream flujoEntrada = new DataInputStream(Cliente.getInputStream());/**< Flujo de entrada del usuario */
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());/**< Flujo de salida del usuario */

	flujoSalida.writeUTF(mensaje);/**< Esto se envía al servidor */
        System.out.println(flujoEntrada.readUTF());/**< Esto se muestra en el lado del usuario, aquí recibimos y mostramos el mensaje ya convertido */
	

	
	flujoSalida.close();/**< Cerramos flujo de salida de datos y desconectamos al cliente */
	Cliente.close();
    }
    
}
