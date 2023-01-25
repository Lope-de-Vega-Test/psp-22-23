
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class ua3ej5frcliente {
    
    public static void main(String[] args) throws IOException {
        Scanner scan=new Scanner(System.in);
        numeros Numeros;
        Socket socketCliente = new Socket("localhost", 1000);

        int base = scan.nextInt();
        long cuadrado=0;
        long cubo=0;

        PrintWriter enviarmensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarmensaje.println(base);
        System.out.println("Mensaje enviado");

        String mensajerecibido;
        String[] array = new String[2];

        BufferedReader recibirmensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        mensajerecibido = recibirmensaje.readLine();

        array = mensajerecibido.split(" ");

        cuadrado = Long.parseLong(array[0]);
        cubo = Long.parseLong(array[1]);
        
        Numeros = new numeros(base,cuadrado,cubo);
        
        System.out.println(Numeros.toString());

        socketCliente.close();
        
    }
    
}