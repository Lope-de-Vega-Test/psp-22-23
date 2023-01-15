import java.io.*;
import java.net.*;

public class Server {
  /**
   *
   * @author avilnec
   */
  public static void main(String[] args) throws IOException {

    ServerSocket serversocket = new ServerSocket(10000);
    Socket socket = serversocket.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    String cadena = in.readLine();
    String send = cadena.toUpperCase();
    out.println(send);
    socket.close();
  }

}