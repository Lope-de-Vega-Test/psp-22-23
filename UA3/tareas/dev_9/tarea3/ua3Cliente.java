import java.util.Scanner;
import java.io.*;
import java.net.*;
public class ua3Cliente {
    public static void main(String[] args) throws IOException {
        Scanner entrada=new Scanner(System.in);
        Socket socketCliente = new Socket("localhost", 100);
        String texto;
        System.out.print("Introduce el texto: ");
        texto=entrada.nextLine();

        PrintWriter enviar=new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(texto);
        System.out.println("El mensaje ha sido mandado");


        BufferedReader recibido=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido=recibido.readLine();
        System.out.println("El mensaje es: "+mensajeRecibido);
        socketCliente.close();
    }
    
}
