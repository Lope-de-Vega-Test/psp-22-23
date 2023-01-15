import java.io.*;
import java.net.*;
import java.util.*;

public class Cliente {
  /**
   *
   * @author avilnec
   */
  private String mensaje;

  public Cliente() {

    mensaje = "";
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public static void main(String[] args) throws Exception {

    String host = "localhost";

    int port = 9000;
    Socket cliente = new Socket(host, port);

    DataOutputStream flujo_salida = new DataOutputStream(cliente.getOutputStream());
    Cliente c = new Cliente();
    c.setMensaje(enviomensaje());
    flujo_salida.writeUTF(c.getMensaje());
    flujo_salida.close();

    cliente.close();
  }

  public static String enviomensaje() {
    Scanner entrada = new Scanner(System.in);
    String mensaje = "";
    System.out.println("Escriba aqui: ");
    while (!mensaje.endsWith("*")) {
      mensaje = mensaje + entrada.nextLine();

    }

    if (mensaje.endsWith("*")) {
      mensaje = mensaje.substring(0, (mensaje.length() - 1));
    }
    return mensaje;
  }
}
