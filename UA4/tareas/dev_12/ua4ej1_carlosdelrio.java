import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

import org.apache.commons.net.ftp.;

public class clienteFTP1_carlosdelrio {
    public static void main(String[] args) throws SocketException, IOException {

        FTPClient cliente = new FTPClient();
        Scanner entrada = new Scanner (System.in);
        System.out.println("INTRODUCE EL FTP");

        String servFTP = entrada.nextLine(); // servidor FTP

        System.out.println("Nos conectamos a: " + servFTP);

        cliente.connect(servFTP);

        // respuesta del servidor FTP
        System.out.print(cliente.getReplyString());
        // codigo de respuesta
        int respuesta = cliente.getReplyCode();

        System.out.println("Respuesta: "+respuesta);

        // comprobacion del codigo de respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)) {
            cliente.disconnect();
            System.out.println("Conexi�n rechazada: " + respuesta);
            System.exit(0);
        }
        // desconexion del servidor FTP
        cliente.disconnect();
        System.out.println("Conexi�n finalizada.");
    }

}