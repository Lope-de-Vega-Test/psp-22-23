import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.util.Scanner;

public class ua3tarea4servidor {
    // Attributes
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] data;

    InetAddress originAddress;
    int originPort;

    // Constructor
    public ua3tarea4servidor(int selfPort) {
        try{
            socket = new DatagramSocket(selfPort);
        } catch (SocketException e1) {
            System.out.println("El puerto " +selfPort + " ya esta en uso.");
            System.exit(1);
        }
    }

    // Methods
    // Receiving
    private void prepareData() {
        data = new byte[1024];
    }
    private void prepareDatagramPacket() {
        prepareData();
        packet = new DatagramPacket(data, data.length, originAddress, originPort);
    }

    // Returning
    private void prepareData(String msg) {
        data = new byte[1024];
        data = msg.getBytes();
    }

    private void prepareDatagramPacket(String msg) {
        prepareData(msg);
        packet = new DatagramPacket(data, data.length, originAddress, originPort);
    }

    // Step 1 - Get packet message
    public String getPacketMsg() {
        return new String(packet.getData());
    }
    // Step 2 - Transform packet message to UPPER CASE
    public String transformMsg(String msg) {
        return getPacketMsg().toUpperCase();
    }
    // Step 3 - Return the transformed packet message to the client
    public void returnMsg(String msg) {
        prepareDatagramPacket(msg);
        try{
            socket.send(packet); // Envio datagrama
        } catch (PortUnreachableException e2) {
            System.out.println("El datagrama no pudo ser enviado. Motivo: el cliente termino la conexion");
        } catch (IOException e1) {
            e1.printStackTrace();

        }
    }

    public void printClientInfo() {
        System.out.println("Datagrama recibido");
        System.out.println("\t - Direccion: " + String.valueOf(packet.getAddress()));
        System.out.println("\t - Puerto: " + String.valueOf(packet.getPort()));
        System.out.println("\t - Contenido: " + getPacketMsg());
    }

    public void myService() {
        try{
            prepareDatagramPacket();
            System.out.println("Atendiendo clientes...");
            socket.receive(packet);
            printClientInfo();
            System.out.println();
            originAddress = packet.getAddress();
            originPort = packet.getPort();
            System.out.println("Devolviendo datagrama transformado a mayusculas...");
            returnMsg(transformMsg(getPacketMsg()));
            System.out.println("Datagrama devuelto");
            System.out.println("\n");
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("El datagrama se perdio. Motivo: " + e1.getCause().toString());
            
        }
    }

    

    // Main program
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int selfPort = 0;
        // Pido al usuario el puerto donde quiere conectarme
        // Pido hasta que introduzca un numero superior a 5999
        do {
            System.out.print("Introduce un numero de puerto: ");
            selfPort = sca.nextInt();
            sca.nextLine();
            if (selfPort < 6000) {
                System.out.println("El puerto debe ser igual o superior a 6000");
            }
        } while(selfPort < 6000);
        // Me configuro para conectarme en mi puerto
        ua3tarea4servidor server = new ua3tarea4servidor(selfPort);
        
        // Escucho cualquier cliente indefinidamente
        while (true) {
            server.myService(); // Atiendo al cliente y realizo el servicio
        }
    }
}
