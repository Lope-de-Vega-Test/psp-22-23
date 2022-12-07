/*
Crea un programa en Java que admita desde la línea de comandos una URL y 
visualice información sobre esta. Modifica el programa para que admita 
continuamente nuevas IP o URL y muestre la información hasta que el usuario 
inserte "localhost".
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
