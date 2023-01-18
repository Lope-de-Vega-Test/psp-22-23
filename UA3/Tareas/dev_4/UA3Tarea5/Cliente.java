import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente 
{

    public static void main(String[] args) throws IOException {
        Numeros Numeros;
        long cuadrado=0;
        long cubo=0;
        Socket socketCliente= new Socket("localhost", 1000);
        
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un numero: ");
        int numeroBase = entrada.nextInt();

        PrintWriter enviarMensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarMensaje.println(numeroBase);
        System.out.println("El mensaje es enviado ");

        String mensajeRecibido;
        String[] array= new String[2];

        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        mensajeRecibido= recibirMensaje.readLine();

        array= mensajeRecibido.split("");

        cuadrado= Long.parseLong(array[0]);
        cubo= Long.parseLong(array[1]);
        Numeros= new Numeros(numeroBase, cuadrado, cubo);
        System.out.println(Numeros.toString());

        socketCliente.close();
    }
}
