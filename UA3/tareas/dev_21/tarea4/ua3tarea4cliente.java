import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ua3tarea4cliente {
    // Attributes
    DatagramSocket socket;
    DatagramPacket packet;
    byte[] data;

    InetAddress destinationAddress;
    int destinationPort;
    
    // Constructor
    public ua3tarea4cliente(int selfPort) {
        try{
            socket = new DatagramSocket(selfPort);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Setters
    public void setDestinationAddress(String address) {
        try{
            destinationAddress = InetAddress.getByName(address);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void setDestinationPort(int port) {
        this.destinationPort = port;
    }
    
    // Methods
    // Receiving
    private void prepareData() { // Preparacion tamaño basico de 1024 bits
        data = new byte[1024];
    }

    private void prepareDatagramPacket() {
        prepareData();
        packet = new DatagramPacket(data, data.length, destinationAddress, destinationPort);
    }

    public String getPacketMsg() {
        return new String(packet.getData());
    }

    public void receive() {
        try {
            prepareDatagramPacket();
            socket.receive(packet);
            System.out.println("Paquete transformado retornado. Contenido: ");
            System.out.println("> " + getPacketMsg());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // Sending
    // Step 1 - Prepare Data
    private void prepareData(String msg) {
        data = new byte[1024]; // Indico tamaño del datagrama
        data = msg.getBytes(); // Transformo el mensaje en bytes
    }
    // Step 2 - Prepare DatagramPacket
    private void prepareDatagramPacket(String msg) {
        prepareData(msg);
        packet = new DatagramPacket(data, data.length, destinationAddress, destinationPort);
    }
    // Step 3 - Send DatagramPacket
    public void sendDatagramPacket(String msg) {
        prepareDatagramPacket(msg);
        try{
            socket.send(packet); // Envio datagrama
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    

    public void close() {
        socket.close();
    }

    
    // Main program
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        int selfPort = 0;
        int destinationPort = 0;
        String destinationAddress = "";
        String msg = "";
        
        // Pido al usuario el puerto donde quiere conectarme
        // Pido hasta que introduzca un numero superior a 5999
        do {
            System.out.print("Introduce un numero de puerto (cliente): ");
            selfPort = sca.nextInt();
            sca.nextLine();
            if (selfPort < 6000) {
                System.out.println("El puerto debe ser igual o superior a 6000");
            }
        } while(selfPort < 6000);
        // Me configuro para conectarme en mi puerto
        ua3tarea4cliente client = new ua3tarea4cliente(selfPort);
        
        // Pido al usuario la direccion del servidor
        System.out.print("Introduce la direccion del servidor: ");
        destinationAddress = sca.nextLine();
        client.setDestinationAddress(destinationAddress);
        // Pido al usuario el puerto al que se conecta el servidor
        // Pido hasta que introduzca un numero superior a 5999
        do {
            System.out.print("Introduce un numero de puerto (servidor): ");
            destinationPort = sca.nextInt();
            sca.nextLine();
            if (destinationPort< 6000) {
                System.out.println("El puerto debe ser igual o superior a 6000");
            }
        } while(destinationPort < 6000);
        client.setDestinationPort(destinationPort);
        // Pido al usuario el mensaje a enviar
        // Cuando el usuario introduzca un asterisco dejo de pedir mensaje
        do {
            System.out.print("Introduce el mensaje a enviar: ");
            msg = sca.nextLine();
            if(!msg.equals("*")) {
                System.out.println("Enviando Datagrama al servidor");
                client.sendDatagramPacket(msg);
                client.receive();
            }
        } while (!msg.equals("*"));
        // Cierro conexion
        client.close();
        System.out.println("Fin del programa");
    }
}