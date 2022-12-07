/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea3;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author RafaelRomero
 */
public class client {
    static String mensaje;
   
    
public static void main(String[] arg) throws IOException {
     Scanner entrada = new Scanner(System.in);
    String Host = "localhost";
    int Puerto = 6000;// Puerto
    
    System.out.println("Dime el mensaje que quieres enviar");
    mensaje=entrada.nextLine();
    
    Socket Cliente = new Socket(Host, Puerto);
    DataOutputStream flujo_salida = new DataOutputStream(Cliente.getOutputStream());
    flujo_salida.writeUTF(mensaje);
    flujo_salida.close();
    
    InetAddress i = Cliente.getInetAddress();
    System.out.println("Puerto Local: " + Cliente.getLocalPort());
    System.out.println("Puerto Remoto: " + Cliente.getPort());
    System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
    System.out.println("Host Remoto: " + i.getHostName().toString());
    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    Cliente.close();
  }

}

