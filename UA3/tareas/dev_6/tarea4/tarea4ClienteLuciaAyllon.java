import java.io.*;
import java.net.*;
import java.util.Scanner;


public class tarea4ClienteLuciaAyllon {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        
        Socket socket = new Socket("localhost", 9397);
        
        boolean finalizar = false;
        String Letra = "";
        String MsjEnviado = "";
        String MsjCliente = "";
        
        System.out.println("Introducir un mensaje:");
        
        while(!Letra.equals("*")){
            Letra = sca.next();
            if(!Letra.equals("*")){
                MsjCliente += Letra;
            }
        }

        PrintWriter envio = new PrintWriter(socket.getOutputStream(), true);
        envio.println(MsjCliente);
        System.out.println("Enviado");

        BufferedReader recibo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String MsjServidor = recibo.readLine();

        System.out.println("Mensaje de Servidor: " + MsjServidor);

        socket.close();
    }
}
