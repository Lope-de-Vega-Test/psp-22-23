import java.io.*;
import java.net.*;

public class Servidor 
{

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);

        System.out.println("Escuchando en el puerto 1000");

        Socket socketCliente= serverSocket.accept();

        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        int numeroBase = Integer.parseInt(recibirMensaje.readLine());
        System.out.println("Numero recibido: "+numeroBase);

        long cuadrado;
        long cubo;

        cuadrado= numeroBase*numeroBase;
        cubo= cuadrado*numeroBase;

        PrintWriter enviarNumero = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarNumero.println(cuadrado+""+cubo);

        System.out.println("Cuadrado del numero: "+cuadrado);
        System.out.println("Cubo del numero: "+cubo);

        socketCliente.close();

    }

}
