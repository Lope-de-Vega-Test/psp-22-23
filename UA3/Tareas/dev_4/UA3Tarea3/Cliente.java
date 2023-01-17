import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente{

    public static void main(String[] args) throws IOException{
        Socket socketCliente = new Socket("localhost", 3000);
        Scanner entrada = new Scanner(System.in);
        System.out.println("Mensaje para enviar:");
        boolean simbolo = false;
        String letra = "";
        String mensajeEnviado = "";
        String texto = "";

        while(!letra.equals("*"))
        {
            letra = entrada.next();
            if(!letra.equals("*"))
            {
                texto+=letra;
            }
        }
        PrintWriter enviarMensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarMensaje.println(texto);
        System.out.println("Mensaje recibido");
        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibirMensaje.readLine();
        System.out.println("El mensaje recibido es: "+mensajeRecibido);
        socketCliente.close();
    }
}
