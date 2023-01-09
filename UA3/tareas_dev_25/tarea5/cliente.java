/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg1.dani.socket.tarea5;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author RafaelRomero
 */
public class cliente {

        public static void main(String[] arg) throws IOException {
                numeros num = new numeros();

                String Host = "localhost";

                //Puerto comunicaciones
                int Puerto = 6000;
                try {
                //Abrir el canal
                Socket Cliente1 = new Socket(Host, Puerto);

                //Pedir mensaje
                Scanner scan = new Scanner(System.in);
                System.out.println("Introduce un numero: ");
                String miMensaje = scan.nextLine();

                //Control de errores
                while(Integer.parseInt(miMensaje)<0) {
                        System.out.println("Número incorrecto");
                        System.out.println("Introduce un numero: ");
                         miMensaje = scan.nextLine();
                }

                //Almacenar en clase número
                num.setNumero(Integer.parseInt(miMensaje));
                
                //Creación de flujos de datos para mandar mensaje por argumentos.
                DataOutputStream flujo_salida = new DataOutputStream(Cliente1.getOutputStream());

                flujo_salida.writeUTF(miMensaje);
                flujo_salida.close();

                //Cerrar canal
                Cliente1.close();
                }catch(Exception e) {
                        System.out.println("Error al enviar");
                }
                try {
                //Abrir canal de servidor con el puerto usado
                ServerSocket ServidorVuelta = new ServerSocket(Puerto);
                System.out.println("Escuchando en " + ServidorVuelta.getLocalPort());

                //Abrir canal a la espera de escuchar con cliente 1
                Socket cliente1Vuelta = ServidorVuelta.accept();

                //Flujos de datos para recibir mensaje
                DataInputStream flujo_entrada = new DataInputStream(cliente1Vuelta.getInputStream());
                String mensaje = flujo_entrada.readUTF();
                System.out.println("Cuadrado del numero: " + mensaje);
                String mensaje1 = flujo_entrada.readUTF();
                System.out.println("Cubo del numero: " + mensaje1);

                System.out.println("\n Conexion realizada con cliente de vuelta");
                
                //Guardar clase numeros
                num.setCuadrado(Long.parseLong(mensaje));
                num.setCubo(Long.parseLong(mensaje1));

                System.out.println(num.toString());
                ServidorVuelta.close();

                }catch(Exception e1) {
                        System.out.println("Fallo cosa recibir");
                }
        }

}
