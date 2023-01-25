package UA3.tareas.dev_8.tarea5;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {

        Socket socketCliente = new Socket("localhost", 10000);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce un numero:");
        int numeroBase = entrada.nextInt();
        long cuadrado = 0, cubo = 0;

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

        Numeros numeros = new Numeros(numeroBase, cuadrado, cubo);

        System.out.println(numeros.toString());

        socketCliente.close();

    }

}
