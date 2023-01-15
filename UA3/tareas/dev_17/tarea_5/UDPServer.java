import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(8888);
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numeros = (Numeros) ois.readObject();

                if (numeros.getEntero() < 0) {
                    System.out.println("El número debe ser mayor o igual a 0.");
                    continue;
                }

                long cuadrado = numeros.getEntero() * numeros.getEntero();
                long cubo = numeros.getEntero() * numeros.getEntero() * numeros.getEntero();
                numeros.setCuadrado(cuadrado);
                numeros.setCubo(cubo);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                byte[] sendData = baos.toByteArray();
                InetAddress address = receivePacket.getAddress();
                int port = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                socket.send(sendPacket);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
