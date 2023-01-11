import java.io.*;
import java.net.*;
import javax.swing.text.Utilities;
import java.util.Scanner;


public class Ua3Tarea3Cliente {
  
    public static void main(String[] args) throws IOException{
        
        Scanner escaner = new Scanner(System.in);
        Socket cliente = new Socket("localhost", 2000);

      
        System.out.println("Escriba el mensaje : ");
        String mensaje = escaner.nextLine();

        PrintWriter auxEnviar = new PrintWriter(cliente.getOutputStream(), true);
        auxEnviar.println(mensaje);
        System.out.println("Se ha conseguido enviar el mensaje");

      
        BufferedReader auxRecibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String mensajeRecibido = auxRecibir.readLine();
        System.out.println("Mensaje recibido : " + mensajeRecibido);

      
        cliente.close();
    }
}
