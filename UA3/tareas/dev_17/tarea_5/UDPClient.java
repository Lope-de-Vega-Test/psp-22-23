import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Introduce el número: ");
            int num = Integer.parseInt(in.readLine());

            if (num < 0) {
                System.out.println("El número debe ser mayor o igual a 0.");
                return;
            }

            Numeros numeros = new Numeros(num);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(numeros);
            byte[] sendData = baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 8888);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.setSoTimeout(3000);
            try {
                socket.receive(receivePacket);
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros result = (Numeros) ois.readObject();

                System.out.println("Número: " + result.getEntero());
                System.out.println("Cuadrado: " + result.getCuadrado());
                System.out.println("Cubo: " + result.getCubo());
            } catch (SocketTimeoutException e) {
                System.out.println("Paquete perdido");
            }
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
