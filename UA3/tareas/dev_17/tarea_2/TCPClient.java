import java.io.IOException;
import java.net.Socket;
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // Creamos un objeto Socket para conectarnos al servidor en la dirección IP localhost y en el puerto 8080
        Socket socket = new Socket("localhost", 8080);

        // Imprimimos la información del puerto local, puerto remoto y dirección IP de la máquina remota
        System.out.println("Información del socket:");
        System.out.println("Puerto local: " + socket.getLocalPort());
        System.out.println("Puerto remoto: " + socket.getPort());
        System.out.println("Dirección IP de la máquina remota: " + socket.getInetAddress().getHostAddress());

        // Cerramos el socket
        socket.close();
    }
}