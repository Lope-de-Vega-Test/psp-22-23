/*
Realiza un programa servidor TCP que acepte dos clientes. Muestra por cada 
cliente conectado sus puertos local y remoto.

Crea también el programa cliente que se conecte a dicho servidor. 
Muestra los puertos locales y remotos a los que está conectado su socket y la 
dirección IP de la máquina remota a la que se conecta.
 */

package pkg1.dani.socket.tarea1;
import java.io.*;
import java.net.*;

/**
 *
 * @author RafaelRomero
 */

public class DaniSocketTarea1 {
public static void main(String[] arg) throws IOException {
	int Puerto = 6000;// Puerto
	ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
    System.out.println("Cliente 1 escuchado");
    // Realizar Acciones con cliente 1

    Socket cliente2 = Servidor.accept();
    System.out.println("Cliente 2 escuchado");
    // Realizar Acciones con cliente 2

    Servidor.close();
  }

}
