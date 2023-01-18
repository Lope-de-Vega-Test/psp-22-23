import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PSP_UA3_tarea2_servidor{
public static void main(String[] arg) throws IOException {
	int Puerto = 6000;
	ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("ESTOY ESCUCHANDO EN: " + Servidor.getLocalPort());

    Socket cliente1 = Servidor.accept();
   System.out.println("HOLA SOY ELENA");

    Socket cliente2 = Servidor.accept();
    System.out.println("ADIOS MAESTRO, ESTO FUNCIONA CORRECTAMENTE.");

    Servidor.close();
  }
}