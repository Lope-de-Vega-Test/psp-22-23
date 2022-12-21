/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        20/12/2022

 * @author      Víctor Aljama Iglesias Miembro 1 - victor.aljama@gmail.com (dev_3)
 *
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */

/*
* @class    MiembroToken
* @brief    Clase para simular el comportamiento de una red token ring
*
* @todo FR1 [5 puntos]: Implementa la clase MiembroToken con la funcionalidad descrita previamente. Para comprobar la correcta ejecución del sistema, la clase MiembroToken debe imprimir por pantalla la suficiente información para ver el estado de cada Miembro de la red.
* @todo FR2 [2,5 puntos]: Mejora la clase MiembroToken para poder ejecutar su funcionalidad, en este caso, dormir una cantidad de tiempo determinada, como un hilo.
* @todo FR4 [1 punto]: Mejora la clase MiembroToken para crear una red token ring de anillo doble, es decir, se puede tener otro token en otro anillo virtual, en sentido contrario.
*/

import java.io.*;
import java.net.*;

class ejecutar{

    public static int puerto,puertoAux,contadorPrimero,contadorSegundo,contadorTercero,dormir;
    public static String token_al_inicio, soy_el_ultimo;
    public static boolean token;

    public ejecutar(int puerto, String token_al_inicio, String soy_el_ultimo){
        this.puerto = puerto;
        this.token_al_inicio = token_al_inicio;
        this.soy_el_ultimo = soy_el_ultimo;
    }

    public static void ejecucion(int id)throws IOException{
        puertoAux = puerto+1;
        contadorPrimero=0;
        contadorSegundo=0;
        contadorTercero=0;

        if(token_al_inicio.equals("yes")){
            token=true;
            System.out.println("-------------------------------------------");
            System.out.println();
            System.out.println("El miembro " + id + " tiene el token al inicio");
        }

        do{

                if(token==true && contadorPrimero<5){

                    if(soy_el_ultimo.equals("no")){
                        System.out.println();
                        System.out.println("El token ha sido enviado al puerto " + puertoAux);
                        System.out.println();
                        System.out.println("-------------------------------------------");
                        System.out.println();
                    }
                    else{
                        System.out.println();
                        System.out.println("El token ha sido enviado al puerto 10000");
                        System.out.println();
                        System.out.println("-------------------------------------------");
                        System.out.println();
                    }

                    String Host = "localhost";
                    try{
                        if(soy_el_ultimo.equals("no")){
                            Socket EnviarToken = new Socket(Host, puertoAux);
                            System.out.println("__________________________________");
                            System.out.println("Puerto Local: " + EnviarToken.getLocalPort());
                            System.out.println("Puerto Remoto: " + EnviarToken.getPort());
                            System.out.println("__________________________________");

                            EnviarToken.close();

                            if(token_al_inicio.equals("no") && soy_el_ultimo.equals("no")){
                                contadorSegundo++;
                            }

                            if(contadorSegundo==5 && token_al_inicio.equals("no") && soy_el_ultimo.equals("no")){

                                System.out.println();
                                System.out.println("**********************");
                                System.out.println("Saliendo del programa");
                                System.out.println("**********************");

                                System.exit(0);
                            }
                        }
                        else if(soy_el_ultimo.equals("yes")){

                            Socket EnviarToken = new Socket(Host, 10000);
                            System.out.println("__________________________________");
                            System.out.println("Puerto Local: " + EnviarToken.getLocalPort());
                            System.out.println("Puerto Remoto: " + EnviarToken.getPort());
                            System.out.println("__________________________________");
                            EnviarToken.close();

                            contadorTercero++;
                            if(contadorTercero==5 && soy_el_ultimo.equals("yes")){

                                System.out.println("**********************");
                                System.out.println("Saliendo del programa");
                                System.out.println("**********************");

                                System.exit(0);
                            }
                        }

                        token=false;
                    }catch(IOException f){
                        System.exit(0);
                    }

                }
                else if(token==false){

                    if(token_al_inicio.equals("yes") && contadorPrimero<5){

                        if(contadorPrimero<5){
                            ServerSocket Escuchar = new ServerSocket(puerto);

                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            System.out.println("Escuchando en " + Escuchar.getLocalPort());

                            Socket EnviarToken = Escuchar.accept();
                            System.out.println("El miembro " + id + " ha recibido el token desde el puerto " + puerto);
                            token=true;
                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            // Realizar Acciones con cliente 1

                            Escuchar.close();
                            contadorPrimero++;
                            if(contadorPrimero==5 && token_al_inicio.equals("yes")){

                                System.out.println("**********************");
                                System.out.println("Saliendo del programa");
                                System.out.println("**********************");

                                System.exit(0);
                            }
                        }
                    }

                    if(token_al_inicio.equals("no") && soy_el_ultimo.equals("no")){

                        if(contadorPrimero<5){
                            ServerSocket Escuchar = new ServerSocket(puerto);

                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            System.out.println("Escuchando en " + Escuchar.getLocalPort());

                            Socket EnviarToken = Escuchar.accept();
                            System.out.println("El miembro " + id + " ha recibido el token desde el puerto " + puerto);
                            token=true;
                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            // Realizar Acciones con cliente 1

                            Escuchar.close();
                        }

                    }

                    if(soy_el_ultimo.equals("yes")){

                        if(contadorPrimero<5){
                            ServerSocket Escuchar = new ServerSocket(puerto);

                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            System.out.println("Escuchando en " + Escuchar.getLocalPort());

                            Socket EnviarToken = Escuchar.accept();
                            System.out.println("El miembro " + id + " ha recibido el token desde el puerto " + puerto);
                            token=true;
                            System.out.println();
                            System.out.println("-------------------------------------------");
                            System.out.println();
                            // Realizar Acciones con cliente 1

                            Escuchar.close();
                        }

                    }

                }

                dormir=id*500;

                try {
                    Thread.sleep(dormir);
                }
                catch (Exception e) {
                    System.out.println(e);
                }

        }while(1>0);

    }

}

class Hilo extends Thread{

    private ejecutar ejecutar_hilo;
    int id;

    public Hilo(int id, ejecutar ejecutar_hilo){
        this.id=id;
        this.ejecutar_hilo = ejecutar_hilo;
    }

    public void run(){
        try{
            ejecutar.ejecucion(id);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}

public class MiembroToken{
    public static void main(String[] args){

        int id = Integer.parseInt(args[0]);
        int puerto = Integer.parseInt(args[1]);
        String token_al_inicio = args[2];
        String soy_el_ultimo = args[3];

        ejecutar ejecutar_hilo = new ejecutar(puerto, token_al_inicio, soy_el_ultimo);

        Hilo hilo_cliente_servidor = new Hilo(id, ejecutar_hilo);

        System.out.println("Comienza la ejecución del hilo ...");
        System.out.println("----------------------------------");
        System.out.println();


        hilo_cliente_servidor.start();

        try {
            hilo_cliente_servidor.join();
        } catch (Exception e) {
            // nothing to do here
        }

    } // End of main()

} // End of class
