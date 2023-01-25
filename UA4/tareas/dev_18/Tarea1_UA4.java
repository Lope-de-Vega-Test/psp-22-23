import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;
import org.apache.commons.net.ftp.*;

public class Tarea1_UA4 {
    public static void main(String[] args) throws SocketException, IOException {

        FTPClient cliente = new FTPClient();
        Scanner entrada = new Scanner(System.in);
        String servFTP = ""; // servidor FTP
        System.out.println("Introduce el FTP del servidor: ");
        servFTP = entrada.nextLine();

        System.out.println("Nos conectamos a: " + servFTP);
        cliente.connect(servFTP);

        // respuesta del servidor FTP
        System.out.print(cliente.getReplyString());
        // codigo de respuesta
        int respuesta = cliente.getReplyCode();

        System.out.println("Respuesta: " + respuesta);

        // comprobacion del codigo de respuesta
        if (!FTPReply.isPositiveCompletion(respuesta)) {

            cliente.disconnect();
            System.out.println("Conexion rechazada: " + respuesta);
            System.exit(0);
        }

        // desconexion del servidor FTP
        cliente.disconnect();
        System.out.println("Conexion finalizada.");

    }

}// ..
