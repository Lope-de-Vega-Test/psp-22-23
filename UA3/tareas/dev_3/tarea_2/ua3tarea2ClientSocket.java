import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ua3tarea2ClientSocket {
  public static void main(String[] arg) throws IOException {
    Scanner entrada = new Scanner(System.in);

    String Host = "";

    System.out.println("-------------------------");
    System.out.println("Introduce la direccion IP del socket servidor");
    System.out.println("Ej. XXXX.XXXX.XXXX.XXXX");
    System.out.println();
    System.out.print("IP: "); Host = entrada.nextLine();

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

