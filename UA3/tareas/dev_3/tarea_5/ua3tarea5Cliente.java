package ua3tarea5cliente;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ua3tarea5Cliente {

    public static void main(String[] args) throws IOException {
        
        numeros Numeros;

        Socket socketCliente = new Socket("localhost", 10000);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un numero:");
        int numeroBase = entrada.nextInt();
        long cuadrado=0, cubo=0;

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(numeroBase);

        System.out.println("El mensaje ha sido enviado...");

        String mensajeRecibido;
        String[] array = new String[2];

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        mensajeRecibido = recibir.readLine();

        array = mensajeRecibido.split(" ");

        cuadrado = Long.parseLong(array[0]);
        cubo = Long.parseLong(array[1]);
        
        Numeros = new numeros(numeroBase,cuadrado,cubo);
        
        System.out.println(Numeros.toString());

        socketCliente.close();

    }
    
}
