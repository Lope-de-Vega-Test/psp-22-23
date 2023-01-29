import java.net.Socket;

public class ua2cliente {
    Socket conn;

    public ua2cliente(String address, int port) {
        try{
            conn = new Socket(address, port);
            printConnectionInfo();
            System.out.println("Finalizo la conexion");
            conn.close();
            System.out.println("Fin del programa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printConnectionInfo() {
        System.out.println("Me he conectado!");
        System.out.println("Nombre: " + conn.getInetAddress().getHostName());
        System.out.println("Direccion IP: " + conn.getInetAddress().getHostAddress());
        System.out.println("Puerto local: " + conn.getLocalPort());
        System.out.println("Puerto remoto: " + conn.getPort());
        System.out.println();
    }

    public static void main(String[] args) {
        new ua2cliente("localhost", 6000);
    }
}
