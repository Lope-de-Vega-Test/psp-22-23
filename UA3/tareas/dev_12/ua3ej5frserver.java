
import java.io.*;
import java.net.*;

public class ua3ej5frserver {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);
        Socket socketCliente = serverSocket.accept();

        BufferedReader recibido = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        int base = Integer.parseInt(recibido.readLine());
        System.out.println("Numero Clientee: " + base);

        long cuadrado;
        long cubo;
        cuadrado = base * base;
        cubo = cuadrado * base;
        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(cuadrado + " " + cubo);

        System.out.println("Cuadrado del numero del cliente: " + cuadrado);
        System.out.println("Cubo del numero del cliente: " + cubo);
        socketCliente.close();
    }
    
}
