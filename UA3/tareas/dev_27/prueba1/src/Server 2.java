import java.io.*;
import java.net.*;

//@Avilnec

public class Server {
    public static void main(String[] arg) throws IOException {
        int Puerto = 6000;// Puerto

        InetAddress direccion = InetAddress.getByName("10.2.0.11");
        ServerSocket Servidor = new ServerSocket(Puerto, 50, direccion);
        System.out.println("Escuchando en " + Servidor.getLocalPort());

        Socket cliente1 = Servidor.accept();
        // Realizar Acciones con cliente 1
        System.out.println("Cliente escuchado");
        Socket cliente2 = Servidor.accept();
        // Realizar Acciones con cliente 2
        System.out.println("Cliente 2 escuchado");
        Servidor.close();
    }

}