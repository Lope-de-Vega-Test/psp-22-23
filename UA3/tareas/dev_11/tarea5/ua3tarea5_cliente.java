package paquete1;
import java.util.Scanner;
import java.io.*;
import java.net.*;

public class ua3tarea5_cliente {

    public static void main(String[] args) throws IOException {

        numeros varNumeros;
        Socket cliente = new Socket("localhost", 2000);
        Scanner entrada = new Scanner(System.in);

        System.out.println("Introduzca un numero: ");
        int base = entrada.nextInt();
        long cuadrado = 0;
        long cubo = 0;

        PrintWriter enviar = new PrintWriter(cliente.getOutputStream(), true);
        enviar.println(base);
        System.out.println("Enviado");

        String msjRecibido;
        String[] aux = new String[2];

        BufferedReader recibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        msjRecibido = recibir.readLine();

        aux = msjRecibido.split(" ");
        cuadrado = Long.parseLong(aux[0]);
        cubo = Long.parseLong(aux[1]);
        
        varNumeros = new numeros(base, cuadrado, cubo);     
        System.out.println(varNumeros.toString());

        cliente.close();
    }
    
}
