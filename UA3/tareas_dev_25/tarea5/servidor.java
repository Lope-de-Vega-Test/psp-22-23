/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1.dani.socket.tarea5;
import java.io.*;
import java.net.*;
/**
 *
 * @author RafaelRomero
 */
public class servidor {

        static long numero;
        static long cuadrado;
        static long cubo;

        /**
         * Metodo main
         * 
         * @param arg
         * @throws IOException
         */
        public static void main(String[] arg) throws IOException {

                //Puerto comunicaciones
                int Puerto = 6000;
                String cuadradoADevolver;
                String cuboADevolver;
                try {
                        //Abrir canal de servidor con el puerto usado
                        ServerSocket Servidor = new ServerSocket(Puerto);
                        System.out.println("Escuchando en " + Servidor.getLocalPort());

                        //Abrir canal a la espera de escuchar cliente 1
                        Socket cliente1 = Servidor.accept();

                        //Flujos de datos para recibir el mensaje
                        DataInputStream flujo_entrada = new DataInputStream(cliente1.getInputStream());
                        String mensaje = flujo_entrada.readUTF();
                        System.out.println("Numero a operar: " + mensaje);

                        numero = Long.parseLong(mensaje);

                        System.out.println("\n Conexion realizada con cliente 1");

                        Servidor.close();
                } catch (Exception e) {
                        System.out.println("Error al recibir");
                }
                cuadrado = numero * numero;
                cubo = numero * numero * numero;
                cuadradoADevolver = Long.toString(cuadrado);
                cuboADevolver = Long.toString(cubo);
      
                String Host = "localhost";
                try {
                        //Abrir canal 
                        Socket mensajeVuelta = new Socket(Host, Puerto);
                        
                        //Creación flujos de datos para mandar mensaje por argumento
                        DataOutputStream flujo_salida = new DataOutputStream(mensajeVuelta.getOutputStream());
                        flujo_salida.writeUTF(cuadradoADevolver);
                        flujo_salida.writeUTF(cuboADevolver);
                        flujo_salida.close();

                        //Cerrar canal
                        mensajeVuelta.close();
                } catch (Exception e1) {
                        System.out.println("Error al enviar");
                }
        }

}