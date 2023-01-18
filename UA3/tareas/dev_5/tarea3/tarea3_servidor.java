import java.io.*;
import java.net.*;

public class tarea3_servidor {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(9999)) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String msg = in.readLine();
            String respuesta = msg.toUpperCase();
            out.println(respuesta);
            socket.close();
        }
    }
}