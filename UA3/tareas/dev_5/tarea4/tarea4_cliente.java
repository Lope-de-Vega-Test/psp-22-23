import java.io.*; 
import java.net.*; 
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class tarea4_cliente {

    public static void main(String[] args) throws IOException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress host = InetAddress.getLocalHost();
        byte[] sendData;
        byte[] receiveData = new byte[1024];

        System.out.println("Introduce el mensaje o * para salir: ");
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();

        while(!message.equals("*")){
            sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, host, 9876);
            socket.send(packet);

            packet = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(packet);
            String respuesta = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Mensaje recibido del servidor: " + respuesta);

            System.out.println("Introduce el mensaje o * para salir: ");
            message = sc.nextLine();
        }

        sc.close();
        socket.close();
    }
}