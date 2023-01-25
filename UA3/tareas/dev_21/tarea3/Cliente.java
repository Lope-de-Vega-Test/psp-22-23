import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        new Cliente(args[0]);
    }
    DatagramPacket datagram;
    byte[] data;
    DatagramSocket con;

    public Cliente(String msg) throws Exception {
        data = new byte[1024];
        data = msg.getBytes();
        datagram = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 6001);
        con = new DatagramSocket(6000);
        
        System.out.println("Soy el cliente, Enviando datagrama: " + msg);

        con.send(datagram);
        
        data = new byte[1024];

        datagram = new DatagramPacket(data, data.length);
        System.out.println("Soy el cliente, Esperando datagrama...");

        con.receive(datagram);
        System.out.println("Soy el cliente, Datagrama recibido: ");
        System.out.println((new String(datagram.getData())).trim());

        con.close();
    }
}