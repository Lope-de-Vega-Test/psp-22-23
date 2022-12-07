/*
Crea un cliente que pida un mensaje al usuario por pantalla y lo mande al 
servidor. El servidor deberá recibir el mensaje, convertirlo todo a mayúsculas 
y devolvérselo al cliente, que lo imprimirá por pantalla.
 */

package pkg1.dani.socket.tarea3;
import java.io.*;
import java.net.*;

/**
 *
 * @author RafaelRomero
 */
public class DaniSocketTarea3 {
public static void main(String[] arg) throws IOException {
	int Puerto = 6000;// Puerto
        
    ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("Escuchando en " + Servidor.getLocalPort());
    

    Socket cliente = Servidor.accept();
    DataInputStream flujo_entrada=new DataInputStream(cliente.getInputStream());
    String mensaje=flujo_entrada.readUTF();
    System.out.println(mensaje.toUpperCase());
    System.out.println("-------------------");
    System.out.println("Cliente 1 escuchado");
    
    // Realizar Acciones con cliente 1

    Socket cliente2 = Servidor.accept();
    System.out.println("Cliente 2 escuchado");
    // Realizar Acciones con cliente 2

    Servidor.close();
  }

}
