
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class tarea5_Cliente {
    public static void main(String[] args) throws IOException {
        Scanner sca= new Scanner(System.in);
        Numeros numeros;

        Socket socketCli=new Socket("localhost",5000);
        int num=sca.nextInt();
        long numcuadrado=0;
        long numcubo=0;
        
        PrintWriter envio=new PrintWriter(socketCli.getOutputStream(),true);
        envio.println(num);

        System.out.println("mensaje enviado");

        String mensajeRecibido;
        String [] array=new String[2];

        BufferedReader recibo=new BufferedReader(new InputStreamReader(socketCli.getInputStream()));
        mensajeRecibido=recibo.readLine();

        array=mensajeRecibido.split(" ");

        numcuadrado=Long.parseLong(array[0]);
        numcubo=Long.parseLong(array[1]);

        numeros =new Numeros(num,numcuadrado,numcubo);

       System.out.println(numeros.toString());

        socketCli.close();
    }
}
