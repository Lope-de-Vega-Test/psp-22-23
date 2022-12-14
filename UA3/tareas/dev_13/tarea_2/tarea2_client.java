import java.io.*;
import java.net.*;

public class tarea2_client {
  public static void main(String[] arg) throws IOException {
    

    Socket Cliente = new Socket("localhost", 6000);
    InetAddress i = Cliente.getInetAddress();
    System.out.println("Puerto Local: " + Cliente.getLocalPort());
    System.out.println("Puerto Remoto: " + Cliente.getPort());
    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    Cliente.close();
  }

}
