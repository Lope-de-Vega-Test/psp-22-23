package u3tarea4servidor;

import java.io.*;
import java.net.*;

/**
 *
 * @author Lucía Luna
 */

public class U3tarea4servidor {
    
    public static void main(String[]args) throws IOException{

        int port = 6000;
        ServerSocket servidor = new ServerSocket(port);
        System.out.println("Se esta escuchando en "+ servidor.getLocalPort());
   
        Socket cliente = servidor.accept();
        DataInputStream flujo_entrada=new DataInputStream(cliente.getInputStream());
        String mensaje=flujo_entrada.readUTF();
        mensaje=mensaje.toUpperCase();
            System.out.println("Mensaje recibido: "+mensaje);
		
	    System.out.println("\n Conexion exitosa");
		
        servidor.close();
    }
    
    
}
