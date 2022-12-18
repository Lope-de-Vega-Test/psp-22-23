import java.io.*;
import java.net.*;

public class Server {
  public static void main(String[] arg) throws IOException {
	int Puerto = 10000;// Puerto
  InetAddress addr = InetAddress.getByName("127.0.0.1");
  ServerSocket sock = new ServerSocket(Puerto, 50, addr);


	ServerSocket Servidor = new ServerSocket(Puerto);
  System.out.println("Escuchando en "+ Servidor.getLocalPort());
    Socket cliente1 = Servidor.accept();
    // Realizar Acciones con cliente 1
    System.out.println("Cliente 1 terminado. Escuchando en "+ Servidor.getLocalPort());

    Socket cliente2 = Servidor.accept();
    // Realizar Acciones con cliente 2
    System.out.println("Cliente 2 terminado, cerrando programa.");
    Servidor.close();
  }

}