import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        //Iniciamos el servidor en el puerto 8888
        DatagramSocket socket = new DatagramSocket(8888);
        while (true) {
            //creamos una variable byte[] para almacenar datos bytes. Usamos 1024, que es algo moderado para enviar mensajes.
            byte[] receiveData = new byte[1024];
            //recibimos datos del cliente
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            //los guardamos en la variable input, quitando los espacios del inicio y final
            String input = new String(receivePacket.getData()).trim();
            //si es igual a * paramos el programa
            if (input.equals("*")) {
                break;
            }
            //en la varible sendData de tipo byte[], guardamos el mensaje enviado por el cliente, convertido
            //a mayuscula y pasado a bytes.
            byte[] sendData = input.toUpperCase().getBytes();
            InetAddress address = receivePacket.getAddress();
            int port = receivePacket.getPort();
            //enviamos el mensaje de vueta al cliente, obteniendo su direccion (localhost) y su puerto.
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
        }
        socket.close();
    }
}
