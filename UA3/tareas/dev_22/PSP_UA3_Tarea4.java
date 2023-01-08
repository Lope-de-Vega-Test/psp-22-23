/**
 * @mainpage                    UA3 Ejercicio 4 (Cliente)
 *                                          
 */
package psp_ua3_tarea4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea4 {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        String Saludo = "";
        int port = 12345; /**< puerto por el que escucha*/
        InetAddress destino = InetAddress.getLocalHost();/**< IP host local*/
        //InetAddress destino = InetAddress.getByName("80.120.54.1"); /**< Para definir el destino de un host con una IP concreta*/

        System.out.println("Introduce el mensaje");
        Saludo = entrada.nextLine();/**< Recibimos mensaje por entrada estandar*/

        byte[] mensaje = new byte[1024]; /**< matriz de bytes*/
        mensaje = Saludo.getBytes();  /**< codificarlo a bytes para enviarlo*/

        
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, destino, port);/**< construyo el datagrama a enviar*/

        DatagramSocket socket = new DatagramSocket(34567);
        socket.send(envio);/**< envio datagrama a destino y port*/
        
        byte[] bufer = new byte[1024];/**< para recibir el datagrama*/

        
        DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);/**< construyo datagrama a recibir*/

        socket.receive(recibo);/**< recibo datagrama*/

        String paquete = new String(recibo.getData());/**< obtengo el paquete*/
        
        System.out.println(paquete.trim());/**< imprimo solamente el contenido del paquete con trim*/
        
        socket.close();
    }

}
