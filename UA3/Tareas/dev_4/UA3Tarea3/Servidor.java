import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1000);

        System.out.println("Escuchando en el puerto: 1000");

        Socket socketCliente = serverSocket.accept();
        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibirMensaje.readLine();
        System.out.println("El mensaje recibido: "+mensajeRecibido);
        PrintWriter enviarMensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        String mensajeEnviar = mensajeRecibido.toUpperCase();
        enviarMensaje.println(mensajeEnviar);
        System.out.println("Mensaje enviado: "+mensajeEnviar);

        socketCliente.close();
    }
}
