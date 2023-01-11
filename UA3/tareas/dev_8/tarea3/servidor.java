package UA3.tareas.dev_8.tarea3;

import java.io.*;
import java.net.*;

public class servidor {
    
    public static void main(String[] args) throws IOException{
        
        ServerSocket server = new ServerSocket(2000);
        System.out.println("Esperando...");
        Socket cliente = server.accept();

        BufferedReader auxRecibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String mensajeRecibido = auxRecibir.readLine();
        System.out.println("Mensaje recibido: " + mensajeRecibido);

        PrintWriter auxEnviar = new PrintWriter(cliente.getOutputStream(), true);
        String mensajeEnviar = mensajeRecibido.toUpperCase();
      
        auxEnviar.println(mensajeEnviar);
        System.out.println("Se ha enviado el mensaje");

        cliente.close();
    }
    
}
