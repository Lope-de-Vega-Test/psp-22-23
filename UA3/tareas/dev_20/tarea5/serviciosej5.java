package serviciosej5;
import java.io.*;
import java.net.*;

/**
 *
 * @author Lucía Luna
 */

public class serviciosej5 {
    
    public static void main(String[] args) throws IOException {
       ServerSocket serverSocker = new ServerSocket(5000);
       Socket socketCli =serverSocker.accept();

       BufferedReader recibido=new BufferedReader(new InputStreamReader(socketCli.getInputStream()));
        int num=Integer.parseInt(recibido.readLine());
        System.out.println("El numero recibido es : "+num);

        long numcuadrado;
        long numcubo;
        numcuadrado=num*num;
        numcubo=numcuadrado*num;

        PrintWriter enviar=new PrintWriter(socketCli.getOutputStream(),true);
        enviar.println(numcuadrado+" "+numcubo);

        System.out.println("El cuadrado del numero es: "+numcuadrado);
        System.out.println("El cubo del numero es : "+numcubo);

        socketCli.close();
    }
}

