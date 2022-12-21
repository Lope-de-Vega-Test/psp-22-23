/**
 * @file        MiembroToken.java
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        21/12/2022
 * @author      Rafael Damian Cristea Cristea Miembro 1 - github@email (dev_A)
 * @author      Luis Manuel Rodriguez Lopez Miembro 2 - github@email (dev_B)
 * @author      Javier Castilla Tejeda Miembro 3 - github@email (dev_C)
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */
import java.io.*;
import java.net.*;
public class MiembroToken{
    static class Hilos extends Thread{
        String[] args;
        int puerto, puertoAux, id;
        boolean token;
        Socket Envio, Escucha;
        ServerSocket Server;
        public Hilos(String args[]) throws IOException {
            this.puerto = Integer.parseInt(args[1]);;
            if (args[3].equals("no")){
            this.puertoAux = puerto+1;
            }
            else this.puertoAux = 10000;
            this.id = Integer.parseInt(args[0]);
            this.token = args[2].equals("yes"); 
            this.Server = new ServerSocket(puerto);
        }
        void esperarT() throws IOException{
            System.out.println("Escuchando en " + puerto);
            Escucha = Server.accept();
            token = true;
        }
        void mostrarT() throws InterruptedException, IOException{
            System.out.println("Esperando a que me den el token");
            Thread.sleep(id*1000);
            enviarT();
            token = false;
        }
        void enviarT() throws IOException{
            System.out.println("Enviando a "+puertoAux+"....");
            Envio = new Socket("localhost", puertoAux);
            Envio.close();
        }
        public void run()
        {
            while(true)
            {
                try{
                    if(token==true){
                        System.out.println("-------------------------------");
                        mostrarT();
                        System.out.println("Ya no tengo el token");
                        System.out.println("-------------------------------");
                        System.out.println("");
                    }else{
                        System.out.println("-------------------------------");
                        esperarT();
                        System.out.println("Me han dado el token");
                        System.out.println("-------------------------------");
                        System.out.println("");
                    }
                }catch(Exception e){
                    System.out.println("-------------------------------");
                    System.out.println("No se encuentra token");
                    System.out.println("-------------------------------");
                } 
            }
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException{
        Hilos hilo = new Hilos(args);
        System.out.println("Id del hilo: "+hilo.id);
        hilo.start();
        try{
            hilo.join();
        }
        catch(Exception e){}
}
}
