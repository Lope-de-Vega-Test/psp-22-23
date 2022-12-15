import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ClientSocket {
  public static void main(String[] arg) throws IOException {
    String Host = "10.2.1.151";
	  int Puerto = 6000;// Puerto

    Socket Cliente = new Socket(Host, Puerto);
    InetAddress i = Cliente.getInetAddress();
    System.out.println("Puerto Local: " + Cliente.getLocalPort());
    System.out.println("Puerto Remoto: " + Cliente.getPort());
    System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
    System.out.println("Host Remoto: " + i.getHostName().toString());
    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    Cliente.close();
  }

}
