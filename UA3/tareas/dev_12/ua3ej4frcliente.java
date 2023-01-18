import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ua3ej4frcliente {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        Socket nuevosocket = new Socket("localhost", 1000); // Se crea el socket con un puerto que queramos coincidiendo con el cliente

        String salir = ""; // Declaramos la salida
        String msjCliente = ""; // Declaramos el mensaje del cliente

        System.out.println("Escribe el texto y pulsa * para salir"); // Escribimos nuestro mensaje

        while (!salir.equals("*")) {
            salir = scan.next();
            if (!salir.equals("*")) {
                msjCliente += salir;
            }
        }

        PrintWriter enviarmsj = new PrintWriter(nuevosocket.getOutputStream(), true); // Enviamos el mensaje
        enviarmsj.println(msjCliente);

        System.out.println("Se ha enviado el mensaje");

        BufferedReader recibirmsj = new BufferedReader(new InputStreamReader(nuevosocket.getInputStream())); // Recibimos el mensaje
        String msjServidor = recibirmsj.readLine();

        System.out.println("Mensaje de Servidor: " + msjServidor); // Se muestra nuestro mensaje por pantalla

        nuevosocket.close(); // Cerramos el socket
    }

}