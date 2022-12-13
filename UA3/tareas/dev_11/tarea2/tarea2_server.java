import java.io.*;
import java.net.*;

public class tarea2_server {
  public static void main(String[] arg) throws IOException {
    
	  int Puerto = 6000;// Puerto
    int backlog = 50;//Backlog
    InetAddress bindAddr = InetAddress.getByName("10.2.1.151");
    
	ServerSocket Servidor = new ServerSocket( Puerto , backlog , bindAddr );
    System.out.println("Escuchando en A" + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
    // Realizar Acciones con cliente 1
    System.out.println("Escuchando en B" + Servidor.getLocalPort());
    Socket cliente2 = Servidor.accept();
    // Realizar Acciones con cliente 2

    Servidor.close();
  }

}
