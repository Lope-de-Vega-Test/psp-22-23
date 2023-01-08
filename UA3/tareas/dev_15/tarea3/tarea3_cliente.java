
//Cliente
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tarea3_cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce un mensaje:");
        String mensaje = sc.nextLine();
        out.println(mensaje);
        String respuesta = in.readLine();
        System.out.println(respuesta);
        socket.close();
    }
}


