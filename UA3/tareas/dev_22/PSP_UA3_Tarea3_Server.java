/**
 * @mainpage                    UA3 Ejercicio 3 (Server)
 *                                          
 */
package psp_ua3_tarea3_server;
import java.io.*;
import java.net.*;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea3_Server {

    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6000;/**< Puerto de conexión */
        String mensajeMayuscula = "";/**< Mensaje que recibiremos */
        
	ServerSocket servidor = new ServerSocket(numeroPuerto);/**< Relacionamos el puerto con el servidor */
	Socket clienteConectado = null;/**< Socket cliente */
	System.out.println("Esperando al cliente.....");/**< Muestra esto hasta conexión de usuario */
	clienteConectado = servidor.accept();/**< Usuario conecta */
        
	
	InputStream entrada = null;
        OutputStream salida = null;
	entrada = clienteConectado.getInputStream();
        salida = clienteConectado.getOutputStream();
	DataInputStream flujoEntrada = new DataInputStream(entrada);/**< Flujo de entrada de datos del cliente */
        DataOutputStream flujoSalida = new DataOutputStream(salida);/**< Flujo de salida de datos del cliente */
	
	mensajeMayuscula = flujoEntrada.readUTF().toUpperCase();/**< Usuario manda el mensaje, lo recibimos aquí y lo convertimos*/
        flujoSalida.writeUTF(mensajeMayuscula);/**< Esto se envía al cliente y se mostrará allí */
	
	entrada.close();
	flujoEntrada.close();
	clienteConectado.close();/**< Cerramos conexión y flujo de datos */
        
    }
    
    
}
