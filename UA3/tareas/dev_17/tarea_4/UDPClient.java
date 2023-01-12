import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        //iniciamos el socket del cliente
        DatagramSocket socket = new DatagramSocket();
        //guardamos la direccion
        InetAddress address = InetAddress.getByName("localhost");
        System.out.println("Escribe un texto: ");
        //inicializamos un Bufferedreader para obtener lo introducido por pantalla
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input;
        //mientras lo ingresado por teclado no sea igual a *, el programa sigue
        while (!(input = in.readLine()).equals("*")) {
            //convertimos el mensaje a bytes
            byte[] sendData = input.getBytes();
            //creamos el paquete que vamos a enviar al servidor.
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, 8888);
            //enviamos el paquete
            socket.send(sendPacket);

            //Creamos una variable tio byte[], donde guardaremos la respuesta del servidor
            byte[] receiveData = new byte[1024];
            //guardamos lo que nos envia el servidor en un paquete
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //hacemos una parada
            socket.setSoTimeout(3000);
            try {
                //recibimos el paquete
                socket.receive(receivePacket);
                //convertimos el mensaje de bytes a caracteres, y quitamos os espacios de inicio y final.
                //este mensaje ya viene cambiado a mayusculas
                String upperCase = new String(receivePacket.getData()).trim();
                //mostramos el texto
                System.out.println("Texto en mayusculas: " + upperCase);
                System.out.println("\nEscribe un texto: ");
            } catch (SocketTimeoutException e) {
                System.out.println("Error");
            }
        }
        socket.close();
    }
}
