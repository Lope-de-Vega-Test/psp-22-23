import java.io.*;
import java.net.*;

public class Server {
  /**
   *
   * @author avilnec
   */
  public static void main(String[] args) throws IOException {

    int port = 9000;
    ServerSocket servidor = new ServerSocket(port);
    System.out.println("Se esta escuchando en " + servidor.getLocalPort());

    Socket cliente = servidor.accept();

    DataInputStream flujo_entrada = new DataInputStream(cliente.getInputStream());
    String texto = flujo_entrada.readUTF();
    texto = texto.toUpperCase();
    System.out.println("Cadena completada: " + texto);

    System.out.println("\n Conexion Terminada");

    servidor.close();

  }
}