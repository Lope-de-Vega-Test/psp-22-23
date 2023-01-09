import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ua3tarea3Cliente {

    public static void main(String[] args) throws IOException {

        Socket socketCliente = new Socket("localhost", 10000);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe tu mensaje:");
        String mensaje = entrada.nextLine();

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(mensaje);

        System.out.println("El mensaje ha sido enviado...");

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibir.readLine();

        System.out.println("El mensaje recibido es: " + mensajeRecibido);

        socketCliente.close();

    }

}
