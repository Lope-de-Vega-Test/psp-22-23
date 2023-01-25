import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) throws Exception {
        new Servidor();
    }
    // Propiedades
    DatagramPacket datagram;
    byte[] data;
    DatagramSocket con;

    public Servidor() throws Exception {
        data = new byte[1024]; // Inicializo datagrama
        con = new DatagramSocket(6001); // Escucho por el puerto 6001

        datagram = new DatagramPacket(data, data.length); // Indico que datagrama voy a recibir
        System.out.println("Soy el servidor, Esperando datagrama...");
        
        con.receive(datagram);// Recibir datagrama

        System.out.println("Soy el servidor, Datagrama recibido");

        int bytesLength = datagram.getLength();// Obtengo numero de bytes recibidos
        String msg = new String(datagram.getData());// Convierto los bytes recibidos a String

        data = new byte[1024];
        data = msg.trim().toUpperCase().getBytes();

        System.out.println("Soy el servidor, Transformo el mensaje recibido a mayusculas");

        int origin = datagram.getPort();

        con.close();
        
        System.out.println("Soy el servidor, Devuelvo el mensaje transformado al cliente");

        datagram = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), origin);
        con = new DatagramSocket(6001);
        con.send(datagram);
        con.close();


        /*
        
        

        // Imprimo informacion
        System.out.println("Numero de bytes recibidos: " + bytesLength);
        System.out.println("Mensaje recibido: " + msg.trim());
        System.out.println("Puerto de origen del mensaje: " + datagram.getPort());
        System.out.println("IP de origen: " + datagram.getAddress().getHostAddress());
        System.out.println("Puerto destino del mensaje: " + con.getLocalPort());
         */


    }
}