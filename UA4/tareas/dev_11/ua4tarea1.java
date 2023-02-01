import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class ua4tarea1 {
    public static void main(String[] args) throws SocketException, IOException {

        Scanner sca = new Scanner(System.in);
        FTPClient cliente = new FTPClient();

        System.out.println("Introduzca el servidor al que se quiere conectar");
        String servFTP = sca.nextLine();
        // "ftp.rediris.es"; // servidor FTP
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
            System.out.println("Conexi�n rechazada: " + respuesta);
            System.exit(0);
        }
        // desconexi�n del servidor FTP
        cliente.disconnect();
        System.out.println("Conexi�n finalizada.");
    }

}// ..
