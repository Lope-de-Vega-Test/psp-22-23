import java.io.*;
import java.net.*;

public class ua3tarea3_server {
    public static void main(String[] args) throws IOException{
        
        ServerSocket server = new ServerSocket(2000);
        System.out.println("Estoy esperando...");
        Socket cliente = server.accept();

        BufferedReader auxRecibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String msjRecibido = auxRecibir.readLine();
        System.out.println("Mensaje: " + msjRecibido);

        PrintWriter auxEnviar = new PrintWriter(cliente.getOutputStream(), true);
        String msjEnviar = msjRecibido.toUpperCase();
        auxEnviar.println(msjEnviar);
        System.out.println("Se ha enviado el mensaje");

        cliente.close();
    }
}
