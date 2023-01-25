package UA3.tareas.dev_8.tarea4;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class cliente {

    public static void main(String[] args) throws IOException {

        Socket socketCliente = new Socket("localhost", 10000);

        Scanner entrada = new Scanner(System.in);
        System.out.println("Escribe tu mensaje:");
        boolean asterisco = false;
        String caracter = "";
        String mensajeEnviado = "";
        String mensaje = "";

        while (!caracter.equals("*")) {

            caracter = entrada.next();
            if (!caracter.equals("*")) {
                mensaje += caracter;
            }
        }

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(mensaje);

        System.out.println("El mensaje ha sido enviado...");

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido = recibir.readLine();

        System.out.println("El mensaje recibido es: " + mensajeRecibido);

        socketCliente.close();

    }
}
