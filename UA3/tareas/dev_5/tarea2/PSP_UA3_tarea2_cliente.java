import java.io.*;

import java.net.*;

public class PSP_UA3_tarea2_cliente {
  public static void main(String[] arg) throws IOException {
    String Host = "localhost";
	  int Puerto = 6000;

    Socket Cliente = new Socket(Host, Puerto);
    InetAddress i = Cliente.getInetAddress();
    System.out.println("AQUI TE MOSTRAMOS LA INFORMACION DEL SOCKET CLIENTE:");
    System.out.println("ESTE ES EL PUERTO LOCAL: " + Cliente.getLocalPort());
    System.out.println("ESTE ES EL PUERTO REMOTO: " + Cliente.getPort());
    System.out.println("ESTE ES EL NOMBRE DEL Host/IP: " + Cliente.getInetAddress());
    System.out.println("ESTE ES EL IP HOST REMOTO: " + i.getHostAddress().toString());

    Cliente.close();
  }

}