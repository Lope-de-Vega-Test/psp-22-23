package paquete1;
import java.io.*;
import java.net.*;

public class ua3tarea5_server {
    public static void main(String[] args) throws IOException{
        
        long cuadrado, cubo;

        ServerSocket server = new ServerSocket(2000);
        System.out.println("Estoy esperando...");
        Socket cliente = server.accept();

        BufferedReader auxRecibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        int base = Integer.parseInt(auxRecibir.readLine());

        System.out.println();
        System.out.println("Recibido: " + base);

        cuadrado = base*base;
        cubo = cuadrado*base;

        PrintWriter auxEnviar = new PrintWriter(cliente.getOutputStream(), true);
        auxEnviar.println(cuadrado + " " + cubo);

        System.out.println("Cuadrado: " + cuadrado);
        System.out.println("Cubo: " + cubo);
        
        cliente.close();
    }
}
