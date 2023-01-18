import java.io.*;
import java.util.Scanner;
import java.net.*;

package UA3.tareas.dev_6.tarea3;


public class tarea3ClienteLuciaAyllon {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Socket SocketCliente = new Socket("localhost", 9397 );
        String txt;
        System.out.print("Introduce lo que quieras decir: ");
            txt=entrada.nextLine();
        PrintWriter enviar=new PrintWriter(SocketCliente.getOutputStream(), true);
            enviar.println(txt);
            System.out.println("mensaje enviado");
        BufferedReader recibido=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String msjRecibido=recibido.readLine();
        System.out.println("El mensaje es: "+msjRecibido);
        SocketCliente.close();
}
