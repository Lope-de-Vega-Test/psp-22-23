import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {

        Socket socketCliente = new Socket("localhost", 1000);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe el mensaje:");
        String mensaje = entrada.nextLine();

        PrintWriter enviarMensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarMensaje.println(mensaje);
        System.out.println("El mensaje se esta enviando");
        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibirMensaje.readLine();
        System.out.println("El mensaje escrito es: "+mensajeRecibido);

        socketCliente.close();
    }
}
