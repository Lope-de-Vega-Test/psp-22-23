import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ua3tarea3servidor {
    DatagramSocket socket;
    byte[] buffer;
    DatagramPacket packet;

    public ua3tarea3servidor(int selfport) {
        try{
            System.out.println("Soy el servidor");
            // Me configuro para escuchar
            socket = new DatagramSocket(selfport);
            // Indico el tamaño del datagrama a recibir
            buffer = new byte[1024];
            
            packet = new DatagramPacket(buffer, buffer.length);
            // Escucho hasta recibir el datagrama
            System.out.println("Estoy esperando el datagrama del cliente");
            socket.receive(packet);
            System.out.println("Datagrama recibido. Contenido: ");
            System.out.println("> " + new String(packet.getData()));
            // Recuerdo los datos del remitente
            int originPort = packet.getPort();
            InetAddress originAddress = packet.getAddress();
            // Cierro la conexion
            socket.close();
            // Obtengo el mensaje del datagrama y lo transformo a mayusculas
            System.out.println("Transformando mensaje a mayusculas");
            String msg = getPacketMsg().toUpperCase();
            // Preparo el tamaño e informacion del datagrama
            buffer = new byte[1024];
            buffer = msg.getBytes();
            // Me configuro para devolver el datagrama
            socket = new DatagramSocket(selfport);
            // Construyo el datagrama
            packet = new DatagramPacket(buffer, buffer.length, originAddress, originPort);
            // Devuelvo el datagrama
            System.out.println("Enviando mensaje");
            socket.send(packet);
            // Cierro la conexion
            socket.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPacketMsg() {
        return new String(packet.getData());
    }

    public static void main(String[] args) {
        new ua3tarea3servidor(6000);
    }

}