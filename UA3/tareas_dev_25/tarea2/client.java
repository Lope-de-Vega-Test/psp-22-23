/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea1;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author RafaelRomero
 */

public class client {
public static void main(String[] arg) throws IOException {
    String Host = "localhost";
	  int Puerto = 6000;// Puerto

    Socket Cliente = new Socket(Host, Puerto);
    InetAddress i = Cliente.getInetAddress();
    System.out.println("Puerto Local: " + Cliente.getLocalPort());
    System.out.println("Puerto Remoto: " + Cliente.getPort());
    System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
    System.out.println("Host Remoto: " + i.getHostName().toString());
    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    Cliente.close();
  }

}
