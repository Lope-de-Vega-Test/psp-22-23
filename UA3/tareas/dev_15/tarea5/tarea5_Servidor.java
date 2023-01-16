import java.io.*;
import java.net.*;


public class tarea5_Servidor {
    
    public static void main(String[] args) throws IOException {
       ServerSocket serverSocker = new ServerSocket(5000);
       Socket socketCli =serverSocker.accept();

       BufferedReader recibido=new BufferedReader(new InputStreamReader(socketCli.getInputStream()));
        int num=Integer.parseInt(recibido.readLine());
        System.out.println("numero recibido: "+num);

        long numcuadrado;
        long numcubo;
        numcuadrado=num*num;
        numcubo=numcuadrado*num;

        PrintWriter enviar=new PrintWriter(socketCli.getOutputStream(),true);
        enviar.println(numcuadrado+" "+numcubo);

        System.out.println("Cuadrado del numero: "+numcuadrado);
        System.out.println("Cubo del numero "+numcubo);

        socketCli.close();
    }
}
