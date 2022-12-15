import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Creamos un objeto ServerSocket para escuchar las conexiones entrantes en el puerto 8080
        ServerSocket serverSocket = new ServerSocket(8080);

        // Aceptamos la conexión de los dos clientes
        Socket client1 = serverSocket.accept();

        // Imprimimos la información de los puertos locales y remotos de cada cliente
        System.out.println("Información del cliente 1:");
        System.out.println("Puerto local: " + client1.getLocalPort());
        System.out.println("Puerto remoto: " + client1.getPort());
        System.out.println("Cliente 1 escuchando");

        Socket client2 = serverSocket.accept();
        System.out.println("Información del cliente 2:");
        System.out.println("Puerto local: " + client2.getLocalPort());
        System.out.println("Puerto remoto: " + client2.getPort());
        System.out.println("Cliente 2 escuchando");

        // Cerramos los sockets
        client1.close();
        client2.close();
        serverSocket.close();
    }

}
