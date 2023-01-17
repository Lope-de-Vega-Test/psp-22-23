import java.io.*;
import java.net.*;

public class Servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);

        System.out.println("Escuchando en el puerto: 3000 ");

        Socket socketCliente = serverSocket.accept();

        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibirMensaje.readLine();
        System.out.println("Mensaje recibido: "+mensajeRecibido);

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        String mensajeEnviar = mensajeRecibido.toUpperCase();
        enviar.println(mensajeEnviar);

        System.out.println("El mensaje es enviado: "+mensajeEnviar);

        socketCliente.close();
    }
}
