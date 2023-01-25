import java.io.*;
import java.util.Scanner;

import org.apache.commons.net.ftp.*;

public class NuevoCliente {
    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        FTPClient cliente= new FTPClient();

        String ServerFTP; 
        ServerFTP= entrada.nextLine();
        System.out.println("El servidor al cual nos conectamos es: "+ServerFTP);

        String nombreUser;
        String passwordUser;

        System.out.println("Introduce el nombre de usuario: ");
        nombreUser= entrada.nextLine();
        System.out.println("Introduce ahora una contraseña: ");
        passwordUser= entrada.nextLine();

        try {
            cliente.connect(ServerFTP);
            cliente.enterLocalPassiveMode(); 
    
            boolean login = cliente.login(nombreUser, passwordUser);
            if (login)
                System.out.println("Login correcto...");
            else {
                System.out.println("Login Incorrecto...");
                cliente.disconnect();
                System.exit(1);
            }
            System.out.println("Directorio actual: "
                             + cliente.printWorkingDirectory());
    
            FTPFile[] files = cliente.listFiles();
            System.out.println("Ficheros en el directorio actual:"
                        + files.length);
            String tipos[] = {"Fichero", "Directorio","Enlace simb."};
            
            for (int i = 0; i < files.length; i++) {
                System.out.println("\t" + files[i].getName() + " => "
                        + tipos[files[i].getType()]);
            }
            boolean logout = cliente.logout();
            if (logout) 
                System.out.println("Logout del servidor FTP...");
                    else 
                    System.out.println("Error al hacer Logout...");
            //
            cliente.disconnect();
            System.out.println("Desconectado...");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }		

    }
}
