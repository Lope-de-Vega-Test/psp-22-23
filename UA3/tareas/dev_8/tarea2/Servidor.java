package UA3.tareas.dev_8.tarea2;

import java.io.*;
import java.net.*;

public class Servidor {
  public static void main(String[] arg) throws IOException {
    int Puerto = 6000;// Puerto
    ServerSocket Servidor = new ServerSocket(Puerto);
    System.out.println("Escuchando en " + Servidor.getLocalPort());

    // Realizar Acciones con cliente 1
    Socket cliente1 = Servidor.accept();
    if (cliente1.isConnected()) {
      InetAddress i = cliente1.getInetAddress();
      System.out.println("\n Conexion realizada con cliente 1");
      System.out.println("\n Escuchando en " + cliente1.getLocalPort());
      System.out.println("Se ha contectado el cliente: " + cliente1.getInetAddress());
      System.out.println("Puerto Local: " + cliente1.getLocalPort());
      System.out.println("Puerto Remoto: " + cliente1.getPort());
      System.out.println("Nombre Host/IP: " + cliente1.getInetAddress());
      System.out.println("Host Remoto: " + i.getHostName().toString());
      System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    }

    // Realizar Acciones con cliente 2
    Socket cliente2 = Servidor.accept();
    if (cliente2.isConnected()) {
      InetAddress i = cliente2.getInetAddress();
      System.out.println("\n Conexion realizada con cliente 1");
      System.out.println("\n Escuchando en " + cliente2.getLocalPort());
      System.out.println("Se ha contectado el cliente: " + cliente2.getInetAddress());
      System.out.println("Puerto Local: " + cliente2.getLocalPort());
      System.out.println("Puerto Remoto: " + cliente2.getPort());
      System.out.println("Nombre Host/IP: " + cliente2.getInetAddress());
      System.out.println("Host Remoto: " + i.getHostName().toString());
      System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

    }

    Servidor.close();
  }

}