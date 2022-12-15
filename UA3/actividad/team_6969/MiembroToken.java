import java.net.*;
import java.io.*;

public class MiembroToken {
    private static int id;
    private static int puerto;
    private static boolean token;
    private static boolean soyElUltimo;

    public static void main(String[] args) throws IOException {
        id = Integer.parseInt(args[0]);
        puerto = Integer.parseInt(args[1]);
        if (args[2].equals("1")) {
            token = true;
        } else {
            token = false;
        }

        if (args[3].equals("1")) {
            soyElUltimo = true;
        } else {
            soyElUltimo = false;
        }

        // Espera a recibir el token
        do {
            
            if (token) {
                System.out.println("Miembro " + id + " recibió el token y comenzará su tarea");
                // Aquí el miembro realizaría su tarea
                ejecutarTarea();
                // Envío el token al siguiente miembro
                enviarToken();
            } else {
                System.out.println("Miembro " + id + " espera el token");
                esperarToken();
            }
        } while (soyElUltimo);
    }

    private static void ejecutarTarea() {
        try {
            Thread.sleep(id * 1000);
            System.out.println("Miembro " + id + " terminó su tarea");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void enviarToken() throws IOException {
        int puertoSiguiente;
        if (soyElUltimo) {
            puertoSiguiente = 10000;
        } else {
            puertoSiguiente = puerto + 1;
        }

        System.out.println("Miembro " + id + " envía el token al puerto " + puertoSiguiente);
        DatagramSocket socket = new DatagramSocket();
        byte[] buf = new byte[256];
        buf = Boolean.toString(token).getBytes();
        InetAddress address = InetAddress.getByName("localhost");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, puertoSiguiente);
        socket.send(packet);
        socket.close();
    }

    private static void esperarToken() throws IOException {
        DatagramSocket socket = new DatagramSocket(puerto);
        byte[] buf = new byte[256];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        token = Boolean.parseBoolean(received);
        socket.close();
        if (token) {
            System.out.println("Miembro " + id + " recibió el token");
            ejecutarTarea();
            enviarToken();
        }
    }
}