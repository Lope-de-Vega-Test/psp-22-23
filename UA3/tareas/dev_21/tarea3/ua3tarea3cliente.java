import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ua3tarea3cliente {
    byte[] buffer;
    DatagramPacket packet;
    DatagramSocket socket;

    public ua3tarea3cliente(int selfport, String destinationAddress, int destinationPort, String msg) {
        try {
            System.out.println("Soy el cliente");
            socket = new DatagramSocket(selfport);
            // Recogemos los datos del destino
            InetAddress destination = InetAddress.getByName(destinationAddress);
            // Indico el tamaño del datagrama a enviar
            buffer = new byte[1024];
            // Almacenamos el valor en bytes del mensaje en el buffer
            buffer = msg.getBytes();
            // Construyo el datagrama
            packet = new DatagramPacket(buffer, buffer.length, destination, destinationPort);
            // Envio el datagrama
            System.out.println("Estoy enviando el datagrama. Contenido: ");
            System.out.println("> " + msg);
            socket.send(packet);
            // Cierro la conexion
            socket.close();
            // Abro una nueva conexion
            socket = new DatagramSocket(selfport);
            // Espero el mensaje devuelto...
            // Indico el tamaño del datagrama a recibir
            buffer = new byte[1024];
            // Construyo el datagrama a recibir
            packet = new DatagramPacket(buffer, buffer.length);
            // Espero hasta recibir el datagrama
            System.out.println("Esperando respuesta del servidor...");
            socket.receive(packet);
            System.out.println("Datagrama recibido. Contenido:");
            // Obtengo el mensaje del datagrama y lo imprimo
            System.out.println("> " + getPacketMsg());
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
        new ua3tarea3cliente(6001, "localhost", 6000, "hola servidor, soy el cliente");
    }


}