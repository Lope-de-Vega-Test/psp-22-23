import java.net.*;
import java.io.*;
public class ua3Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServidor=new ServerSocket(100);
        System.out.println("Escuchando en el puerto 100");
        Socket Cliente=socketServidor.accept();
        
        BufferedReader recibido=new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
        String mensajeRecibido=recibido.readLine();
        System.out.println("El mensaje es: "+mensajeRecibido);

        PrintWriter enviar=new PrintWriter(Cliente.getOutputStream(), true);
        String mensajeEnviado=mensajeRecibido.toUpperCase();
        enviar.println(mensajeEnviado);
        System.out.println("El mensaje ha sido mandado");
        Cliente.close();
        socketServidor.close();
    }
}
