import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
  /**
   *
   * @author avilnec
   */
  public static void main(String[] args) throws IOException {
    Scanner entrada = new Scanner(System.in);
    ClaseNumeros numero;
    int base;
    long cuadrado, cubo;
    Socket socketcliente = new Socket("localhost", 9000);

    System.out.println("Escribe un numero: ");
    base = entrada.nextInt();

    PrintWriter enviar = new PrintWriter(socketcliente.getOutputStream(), true);
    enviar.println(base);
    System.out.println("Mensaje enviado");

    String recibido;
    String[] array = new String[2];

    BufferedReader recibir = new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
    recibido = recibir.readLine();
    array = recibido.split(" ");
    cuadrado = Long.parseLong(array[0]);
    cubo = Long.parseLong(array[1]);

    numero = new ClaseNumeros(base, cuadrado, cubo);

    System.out.println(numero.toString());

    socketcliente.close();

  }
}