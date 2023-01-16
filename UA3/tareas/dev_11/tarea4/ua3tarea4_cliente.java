import java.util.Scanner;
import java.io.*;
import java.net.*;

public class ua3tarea4Cliente{

    public static void main(String[] args) throws IOException{

        Scanner entrada = new Scanner(System.in);
        Socket cliente = new Socket("localhost", 2000);

        boolean asterisco = false;
        String caracter = "";
        String msjEnviado = "";
        String msj = "";

        System.out.println("Escribe tu mensaje:");

        while(!caracter.equals("*")){

            caracter = entrada.next();
            if(!caracter.equals("*")){
                msj += caracter;
            }
        }

        PrintWriter enviar = new PrintWriter(cliente.getOutputStream(), true);
        enviar.println(msj);

        System.out.println("Mensaje enviado");

        BufferedReader recibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String msjRecibido = recibir.readLine();

        System.out.println("El mensaje recibido es: " + msjRecibido);
        cliente.close();

    }
}
