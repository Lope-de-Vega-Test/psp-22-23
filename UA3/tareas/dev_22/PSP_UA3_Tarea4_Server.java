/**
 * @mainpage                    UA3 Ejercicio 4 (Servidor)
 *                                          
 */
package psp_ua3_tarea4_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Ignacio
 */
public class PSP_UA3_Tarea4_Server {

    public static void main(String[] args) throws Exception {
        byte[] bufer = new byte[1024];/**< para recibir el datagrama*/

        
        DatagramSocket socket = new DatagramSocket(12345);/**< Asocio el socket al puerto 12345*/

        DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);/**< construyo datagrama a recibir*/
        System.out.println("Esperando mensaje...");

        socket.receive(recibo);/**< recibo datagrama*/
        

        String paquete = new String(recibo.getData());/**< obtengo el paquete*/
        paquete = paquete.trim().toUpperCase();/**< cogemos solo el contenido del paquete, que es el mensaje, y lo ponemos en mayusculas*/
        
        byte[] mensaje = new byte[1024]; /**< matriz de bytes*/
        mensaje = paquete.getBytes();  /**< codificarlo a bytes para enviarlo*/
        
        DatagramPacket envio = new DatagramPacket(mensaje, mensaje.length, InetAddress.getLocalHost(), 34567);
        
        socket.send(envio);/**< envio datagrama a destino y port*/

        socket.close(); /**< cierro el socket*/
    }

}
