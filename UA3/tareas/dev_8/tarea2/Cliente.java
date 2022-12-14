package UA3.tareas.dev_8.tarea2;

import java.io.*;
import java.net.*;

public class Cliente {
  public static void main(String[] arg) throws IOException {

    int Puerto = 6000;// Puerto
    String Host = "localhost";

    //Socket cliente = Servidor.accept();
    Socket Cliente = new Socket(Host, Puerto);
    // Realizar Acciones con cliente 1
    if (Cliente.isConnected()) {
      System.out.println("Se ha contectado el cliente: " + Cliente.getInetAddress());
    }


    InetAddress i = Cliente.getInetAddress();
		System.out.println("Puerto Local: " + Cliente.getLocalPort());
		System.out.println("Puerto Remoto: " + Cliente.getPort());
		System.out.println("Nombre Host/IP: " + Cliente.getInetAddress());
		System.out.println("Host Remoto: " + i.getHostName().toString());
		System.out.println("IP Host Remoto: " + i.getHostAddress().toString());
		System.out.println();


    Cliente.close();


  }

}