import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.text.Utilities;

public class ua3tarea3_cliente {
    public static void main(String[] args) throws IOException{
        
        Scanner sca = new Scanner(System.in);
        Socket cliente = new Socket("localhost", 2000);

        System.out.println("Escriba el mensaje: ");
        String msj = sca.nextLine();

        PrintWriter auxEnviar = new PrintWriter(cliente.getOutputStream(), true);
        auxEnviar.println(msj);
        System.out.println("Se ha enviado el mensaje");

        BufferedReader auxRecibir = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String msjRecibido = auxRecibir.readLine();
        System.out.println("Mensaje: " + msjRecibido);

        cliente.close();
    }
}
