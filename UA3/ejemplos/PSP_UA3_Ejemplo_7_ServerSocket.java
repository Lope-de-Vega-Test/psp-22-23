import java.io.*;
import java.net.*;

public class PSP_UA3_Ejemplo_7_ServerSocket {
  public static void main(String[] arg) throws IOException {
	int Puerto = 6001;// Puerto
	ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
    // Realizar Acciones con cliente 1

    Socket cliente2 = Servidor.accept();
    // Realizar Acciones con cliente 2

    Servidor.close();
  }

}

