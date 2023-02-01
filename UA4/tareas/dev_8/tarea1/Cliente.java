package UA4.tareas.dev_8.tarea1;

import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class Cliente {

    public static void main(String[] args) throws SocketException, IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el servidor ftp para conectarnos:\n\tftp.uv.es\n\tftp.unavarra.es\n\tftp.rediris.es\n\tftp.uma.es\n\tftp.udc.es\n\tftp.dit.upm.es\n\tftp.freenet.de");
        // System.out.println("\n\tftp.uv.es\n\tftp.unavarra.es\n\tftp.rediris.es\n\tftp.uma.es\n\tftp.udc.es\n\tftp.dit.upm.es\n\tftp.freenet.de");
        String servFTP = sc.nextLine();
        
        FTPClient cliente = new FTPClient();
        // String servFTP = "ftp.rediris.es"; // servidor FTP
        System.out.println("Nos conectamos a: " + servFTP);
        cliente.connect(servFTP);

        // respuesta del servidor FTP
        System.out.print(cliente.getReplyString());
        // c�digo de respuesta
        int respuesta = cliente.getReplyCode();

        System.out.println("Respuesta: " + respuesta);

        // comprobaci�n del c�digo de respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            cliente.disconnect();
            System.out.println("Conexión rechazada: " + respuesta);
            System.exit(0);
        }
        // desconexi�n del servidor FTP
        cliente.disconnect();
        System.out.println("Conexión finalizada.");
    }

}
