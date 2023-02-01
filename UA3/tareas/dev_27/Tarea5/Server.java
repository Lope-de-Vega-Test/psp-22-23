
import java.io.*;
import java.net.*;

public class Server {
  /**
   *
   * @author avilnec
   */
  public static void main(String[] args) throws IOException {

    ServerSocket serversocket = new ServerSocket(9000);

    System.out.println("Escuchando en el puerto 9000");

    Socket socketcliente = serversocket.accept();

    BufferedReader recibir = new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
    int base = Integer.parseInt(recibir.readLine());
    System.out.println("Numero recibido: " + base);

    long cuadrado, cubo;

    cuadrado = base * base;
    cubo = cuadrado * base;

    PrintWriter enviar = new PrintWriter(socketcliente.getOutputStream(), true);
    enviar.println(cuadrado + " " + cubo);

    System.out.println("Cuadrado del numero: " + cuadrado);
    System.out.println("Cubo del numero: " + cubo);

    socketcliente.close();

  }

}