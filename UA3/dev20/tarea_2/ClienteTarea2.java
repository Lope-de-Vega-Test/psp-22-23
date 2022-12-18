package clientetarea2;
 import java.io.*;
 import java.net.*;
/**
 *
 * @author Lucía Luna
 */
public class ClienteTarea2 {

    /**
     * @param args the command line arguments
     */
      public static void main(String[] args) throws IOException{
        int Puerto = 6000;

        ServerSocket Servidor = new ServerSocket(Puerto);
        Socket cliente1 = null;
        Socket cliente2 = null;
        System.out.println("Esperando al cliente ");

             cliente1 = Servidor.accept();
               System.out.println("El cliente 1 conectado");
               System.out.println("Puerto local: " + cliente1.getLocalPort());
               System.out.println("Puerto remoto: " + cliente1.getLocalPort());

            cliente2 = Servidor.accept();
              System.out.println("El cliente 2 conectado");
              System.out.println("Puerto local: " + cliente2.getLocalPort());
              System.out.println("Puerto remoto: " + cliente2.getPort());

        
        cliente1.close();
        cliente2.close();
        Servidor.close();
    }
 }
