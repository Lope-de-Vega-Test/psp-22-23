import java.net.ServerSocket;
import java.net.Socket;

public class ua3tarea2servidor {
    ServerSocket server;
    Socket cliente;

    public ua3tarea2servidor(int port) {
        try{
            server = new ServerSocket(port);
            System.out.println("Servidor en marcha!");
            System.out.println("Estoy escuchando");
            cliente = server.accept();
            printClientInfo();
            System.out.println("Estoy escuchando");
            cliente = server.accept();
            printClientInfo();
            System.out.println("Dejo de escuchar");
            server.close();
            System.out.println("Fin del programa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printClientInfo() {
        System.out.println("Cliente conectado!");
        System.out.println("\t- Nombre: " + cliente.getInetAddress().getHostName());
        System.out.println("\t- Direccion IP: " + cliente.getInetAddress().getHostAddress());
        System.out.println("\t- Puerto mediante el cual se conecta: " + cliente.getPort());
        System.out.println("\t- Puerto al que se conecta: " + cliente.getLocalPort());
        System.out.println();
    }

    public static void main(String[] args) {
        new ua3tarea2servidor(6000);
    }
}