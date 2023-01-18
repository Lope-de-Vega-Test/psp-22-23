import java.io.*;
import java.net.*;

public class ua3ej4frserver {

    public static void main(String[] args) throws IOException {

        ServerSocket nuevoserversocket = new ServerSocket(1000); // Se crea el socket con un puerto que queramos coincidiendo con el cliente
        Socket nuevosocket = nuevoserversocket.accept();

        BufferedReader recibirmsj = new BufferedReader(new InputStreamReader(nuevosocket.getInputStream())); // Recibimos el mensaje del cliente
        String msjCliente = recibirmsj.readLine();

        System.out.println("Recibimos el mensaje del cliente: " + msjCliente); // Se muestra por pantalla el mensaje del cliente

        PrintWriter enviarmsj = new PrintWriter(nuevosocket.getOutputStream(), true);
        String msjServidor = msjCliente.toUpperCase();
        enviarmsj.println(msjServidor);

        System.out.println("Mostrando el mensaje del servidor: " + msjServidor); // Se imprime por pantalla la respuesta en mayusculas

        nuevosocket.close(); // Cerramos el socket
    }

}