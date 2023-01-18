import java.io.*;
import java.net.*;



public class tarea4ServidorLuciaAyllon {
    public static void main(String[] args) {
        ServerSocket socket = new ServerSocket(9397);
        Socket socketCliente = socket.accept();
        BufferedReader Recibido = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String MsjCliente = Recibido.readLine();
        System.out.println("Mensaje del Cliente: " + MsjCliente);
        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        String MsjServidor = MsjCliente.toUpperCase();
        enviar.println(MsjServidor);
        System.out.println("Mensaje del Servidor: " + MsjServidor);
        socket.close();
    }
}
