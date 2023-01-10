import java.io.*;
import java.net.*;

public class servidor {
 public static void main(String[] args) throws IOException
 {
    int puerto = 8989;
    ServerSocket server = new ServerSocket(puerto);
    Socket socket = server.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
    String mensaje= in.readLine();
    String mensajeMOD = mensaje.toUpperCase();
    out.println(mensajeMOD);
    socket.close();
 }    

}