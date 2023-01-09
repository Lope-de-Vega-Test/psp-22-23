/*

Crea un programa cliente usando sockets UDP que envíe el texto escrito desde la 
entrada estándar al servidor. El servidor devolverá la cadena en mayúsculas. 
El proceso de entrada de datos finalizará cuando el cliente introduzca un 
astrerisco. El servidor se encargará de procesar las cadenas de caracteres 
hasta recibir un asterisco.

Deberás establecer un tiempo de espera de varios segundos para que el método 
receive() del cliente se bloquee. Controlas las excepciones de IO e indica 
si los paquetes se han perdido. Haz varias pruebas de la aplicación sin 
ejecutar el servidor y ejecutando varios clientes a la vez.

 */
package pkg1.dani.socket.tarea4;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author RafaelRomero
 */

public class DaniSocketTarea4 {
public static void main(String[] arg) throws IOException {

                int Puerto = 6000; //Puerto
                
                //Abrir canal de servidor  
                ServerSocket Servidor = new ServerSocket(Puerto);
                System.out.println("Escuchando en " + Servidor.getLocalPort());
                
                //Abrir canal a la espera de cliente
                Socket cliente1 = Servidor.accept();
                
                //Flujos para recibir mensajes
                DataInputStream flujo_entrada=new DataInputStream(cliente1.getInputStream());
                String mensaje=flujo_entrada.readUTF();
                mensaje=mensaje.toUpperCase();
                System.out.println(mensaje);
                System.out.println("\n Conexion realizada con cliente");
                
                //Cerramos servidor
                Servidor.close();
        }

}