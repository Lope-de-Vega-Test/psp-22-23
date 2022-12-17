import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.transform.Source;

public class MiembroToken {

  public static void main(String[] args) throws Exception {
     int id = Integer.parseInt(args[0]);
     int puerto = Integer.parseInt(args[1]);
     Boolean tokinicio = Boolean.parseBoolean(args[2]);
     Boolean  ultimo = Boolean.parseBoolean(args[3]);
    for(int i=0; i<3;i++){
        try {
            if(args[i].isEmpty()){
                System.out.println("Faltan parametros");
            }
            
        } catch (Exception e) {
            System.out.println("Faltan parametros");
            System.exit(1);
        }
    
}
    
    byte[] bufer = new byte[1024];//para recibir el datagrama
    int port = puerto; 
    ServerSocket socketRecibir = new ServerSocket(port);
    Socket socketEnviar = new Socket();
    do{
        if(ultimo){
     
    //construyo datagrama a recibir
    System.out.println("Esperando Datagrama .......... ");
    socketRecibir.accept();
    String paquete= new String(recibo.getData());//obtengo String
    System.out.println("----------------------------------");
    System.out.println("Token: " + paquete.trim());
    System.out.println("Puerto origen del mensaje: " + recibo.getPort());
    System.out.println("IP de origen: " + recibo.getAddress().getHostAddress());
    System.out.println("Puerto destino del mensaje: " + socket.getLocalPort());
    System.out.println("----------------------------------");
    port++;
    socketRecibir.close();
    InetAddress destino = InetAddress.getLocalHost();
              byte[] mensaje = new byte[1024]; //matriz de bytes
              String Saludo = "" +id;
              mensaje = Saludo.getBytes();  //codificarlo a bytes para enviarlo
              //construyo el datagrama a enviar
              DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
              socketEnviar.send(envio);//envio datagrama a destino y port
}

if(tokinicio){
    InetAddress destino = InetAddress.getLocalHost();
   
              byte[] mensaje = new byte[1024]; //matriz de bytes
              String Saludo = "" +id;
              mensaje = Saludo.getBytes();  //codificarlo a bytes para enviarlo
              //construyo el datagrama a enviar
              DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);
              socket.send(envio);//envio datagrama a destino y port
            }
  }while(port!=10003);
  }
}
