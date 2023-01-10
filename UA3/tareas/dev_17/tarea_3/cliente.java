import java.io.*;
import java.net.*;
import java.util.Scanner;

public class cliente {

    public static void main(String[] args)  throws IOException{
        int puerto = 8989;
        String url = "localhost";
        Socket socket = new Socket(url, puerto);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe algo: ");
        String mensaje = scan.nextLine();
        out.println(mensaje);
        String mensajeMOD = in.readLine();
        System.out.println(mensajeMOD);
        socket.close();
        

    }

}