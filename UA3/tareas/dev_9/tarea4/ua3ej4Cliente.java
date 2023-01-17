import java.util.Scanner;
import java.io.*;
import java.net.*;
public class ua3ej4Cliente {
    public static void main(String[] args) throws IOException {
        Scanner entrada=new Scanner(System.in);
        Socket socketCliente = new Socket("localhost", 100);
        String texto;
        boolean repetir=true;
        System.out.print("Introduce el texto: ");
        String textocompl="";
        
        
        while(repetir){
            texto=entrada.nextLine();
            if(texto.length()>0){
                if(texto.equals("*")){
                    repetir=false;
                    try{
                        PrintWriter enviar=new PrintWriter(socketCliente.getOutputStream(), true);
                        enviar.println(textocompl);
                        System.out.println("El mensaje ha sido mandado");
                    }
                    catch(Exception e){
                        System.out.println("El mensaje no se ha podido mandar");
                    }
                }
                else
                textocompl+=texto;
            }
        }
        BufferedReader recibido=new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeRecibido=recibido.readLine();
        System.out.println("El mensaje es: "+mensajeRecibido);
        socketCliente.close();
    }
}
