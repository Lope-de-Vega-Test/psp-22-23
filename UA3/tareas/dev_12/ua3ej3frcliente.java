import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ua3ej3frcliente {
    
    private static String msjCliente; //Declaramos el mensaje
    private static String devolucionmsj; //Declaramos la respuesta

    public static void main(String[] args) throws IOException{

        Scanner scan = new Scanner(System.in);
        
        Socket nuevosocket = new Socket("localhost", 1000); //Se crea el socket con un puerto que queramos coincidiendo con el server

        PrintWriter auxsalida = new PrintWriter(nuevosocket.getOutputStream(), true);
        BufferedReader auxentrada = new BufferedReader(new InputStreamReader(nuevosocket.getInputStream()));

        System.out.println("Introduce el texto:");
        String msjCliente = scan.nextLine(); //Escribimos nuestro mensaje
        
        auxsalida.println(msjCliente);
        String devolucionmsj = auxentrada.readLine();
        System.out.println(devolucionmsj); //Se muestra nuestro mensaje por pantalla
        
        nuevosocket.close(); //Cerramos el socket
    }
}   
