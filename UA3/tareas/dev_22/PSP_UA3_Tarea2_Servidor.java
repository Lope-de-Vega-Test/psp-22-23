/**
 * @mainpage                    UA3 Ejercicio 2 Server Side
 *                                          
 */
package psp_ua3_tarea2_servidor;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea2_Servidor {

    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6000;/**< Puerto de conexión */
	ServerSocket servidor = new ServerSocket(numeroPuerto);/**< Relacionamos el puerto con el servidor */
	Socket clienteConectado = null;/**< Uno por cada usuario */
        Socket clienteConectado2 = null;
	System.out.println("Esperando al cliente.....");/**< Muestra esto hasta conexión de usuario */
	clienteConectado = servidor.accept();/**< Usuario conecta */

	
	InputStream entrada = null;
	entrada = clienteConectado.getInputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);/**< Flujo de entrada de datos del cliente */

	
	System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada.readUTF());/**< Usuario manda datos */

	
	entrada.close();
	flujoEntrada.close();
	clienteConectado.close();/**< Cerramos conexión */
        
        System.out.println("Esperando al cliente.....");/**< Esperamos al segundo cliente y vuelta a empezar */
        clienteConectado2 = servidor.accept();
        
        InputStream entrada2 = null;
	entrada2 = clienteConectado2.getInputStream();
	DataInputStream flujoEntrada2 = new DataInputStream(entrada2);

	
	System.out.println("Recibiendo del CLIENTE: \n\t" + flujoEntrada2.readUTF());

	
	entrada2.close();
	flujoEntrada2.close();
	clienteConectado2.close();
        
	servidor.close();/**< Cerramos el servidor */
    }
    
}
