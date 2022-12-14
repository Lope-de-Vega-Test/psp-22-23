package u3_tarea2server;
import java.io.*;
import java.net.*;

/**
 *
 * @author Lucía Luna
 */

public class U3_tarea2server {
  public static void main(String[] arg) throws IOException {
   
    int Puerto = 6000;

    InetAddress direccion = InetAddress.getByName("10.2.0.11");
	ServerSocket Servidor = new ServerSocket(Puerto,50,direccion);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    //acciones con cliente 1
    Socket cliente1 = Servidor.accept();
    System.out.println("Cliente 1 escuchado");
    
    //acciones con cliente 2
    Socket cliente2 = Servidor.accept();
    System.out.println("Cliente 2 escuchado");
    Servidor.close();
    
  }

}
