// El siguiente ejemplo crea un socket de servidor y lo enlaza al puerto 6000.
// Visualiza el puerto por el que se esperan las conexiones y espera que ser conecten 2 clientes.

import java.io.*;
import java.net.*;

public class tarea2_server {
  public static void main(String[] arg) throws IOException {
	int Puerto = 6000;// Puerto
	ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
      System.out.println("Bienvenido cliente 1");
      System.out.println("Puerto remoto: "+cliente1.getPort());
      System.out.println("Puerto local: "+cliente1.getLocalPort()+"\n");
      

    Socket cliente2 = Servidor.accept();
      System.out.println("Bienvenido cliente 2");
      System.out.println("Puerto remoto: "+cliente2.getPort());
      System.out.println("Puerto local: "+cliente2.getLocalPort());

    Servidor.close();
  }

}
