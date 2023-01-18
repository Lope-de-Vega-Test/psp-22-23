import java.net.*;
import java.io.*;

package UA3.tareas.dev_6.tarea3;

public class tarea3ServidorLuciaAyllon {
    public static void main(String[] args) {
        ServerSocket SocketServidor=new ServerSocket(9397);
        System.out.println("Escuchando puerto 9397");
        Socket Cliente = SocketServidor.accept();
        BufferedReader recibido = new BufferedReader(new InputStreamReader(Cliente.getInputStream()));
        String msjRecibido=recibido.readLine();
        System.out.println("El mensaje es: "+msjRecibido);
        PrintWriter enviar=new PrintWriter(Cliente.getOutputStream(), true);
        String msjEnviado=msjRecibido.toUpperCase();
        enviar.println(msjEnviado);
        System.out.println("MENSAJE ENVIADO");
        Cliente.close();
        SocketServidor.close();
    }
}
