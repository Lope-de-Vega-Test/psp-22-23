import java.io.*;
import java.net.*;

public class ua3tarea5Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(10000);

        System.out.println("Escuchando en el puerto 10000");

        Socket socketCliente = serverSocket.accept();

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        int numeroBase = Integer.parseInt(recibir.readLine());
        System.out.println("Numero recibido: " + numeroBase);

        long cuadrado, cubo;
        cuadrado = numeroBase * numeroBase;
        cubo = cuadrado * numeroBase;

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(cuadrado + " " + cubo);

        System.out.println("Cuadrado del numero: " + cuadrado);
        System.out.println("Cubo del numero: " + cubo);

        socketCliente.close();

    }

}
