import java.net.*;
import java.io.*;

public class MiembroPrueba {

private int id; 
private int puerto; 
private boolean tokenAlInicio; 
private boolean soyElUltimo;

public MiembroPrueba(int id, int puerto, boolean tokenAlInicio, boolean soyElUltimo) {
        this.id = id;
        this.puerto = puerto;
        this.tokenAlInicio = tokenAlInicio;
        this.soyElUltimo = soyElUltimo;
    }

public void ejecutar() {
        boolean token = tokenAlInicio;
        int puertoEnviar = puerto + 1;
        
        while (true) {
            if (token) {
                System.out.println("Miembro " + id + ": recibido token. Esperando " + id + " segundos.");
                try {
                    Thread.sleep(id*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Miembro " + id + ": esperando token.");
            }
            
            try {
                DatagramSocket socket = new DatagramSocket(puerto);
                byte[] buffer = new byte[1];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                token = true;
                System.out.println("Miembro " + id + ": recibido token.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if (soyElUltimo) {
                puertoEnviar = 10000;
            }
            
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress address = InetAddress.getByName("localhost");
                byte[] buffer = new byte[1];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puertoEnviar);
                socket.send(packet);
                token = false;
                System.out.println("Miembro " + id + ": enviado token.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}