import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
  /**
   *
   * @author avilnec
   */

  public static void main(String[] args) throws IOException {

    Socket socket = new Socket("localhost", 10000);
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    Scanner entrada = new Scanner(System.in);
    String cadena;
    String send;
    System.out.println("Escriba texto a modificar: ");
    cadena = entrada.nextLine();
    out.println(cadena);
    send = in.readLine();
    System.out.println(send);
    socket.close();

  }

}