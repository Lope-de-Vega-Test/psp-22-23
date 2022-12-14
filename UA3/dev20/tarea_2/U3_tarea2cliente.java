package u3_tarea2cliente;
import java.io.*;
import java.net.*;

/**
 *
 * @author Lucía Luna
 */

public class U3_tarea2cliente {
    public static void main(String[] arg) throws IOException {
     
        String Host = "localhost";
            int Puerto = 6000;
    
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
