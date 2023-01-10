import java.io.*;
import java.net.*;

public class ua3tarea4Servidor {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(10000);

        System.out.println("Escuchando en el puerto 10000");

        Socket socketCliente = serverSocket.accept();

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibir.readLine();
        System.out.println("Mensaje recibido: " + mensajeRecibido);

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        String mensajeEnviar = mensajeRecibido.toUpperCase();
        enviar.println(mensajeEnviar);

        System.out.println("Mensjae enviado: " + mensajeEnviar);

        socketCliente.close();

    }

}
