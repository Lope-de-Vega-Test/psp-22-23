// El siguiente ejemplo crea un socket de servidor y lo enlaza al puerto 6000.
// Visualiza el puerto por el que se esperan las conexiones y espera que ser conecten 2 clientes.

import java.io.*;
import java.net.*;

public class ejem7_serverSocket {
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