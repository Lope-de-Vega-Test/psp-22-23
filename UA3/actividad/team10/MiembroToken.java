/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        21/12/2022
 * @author      Rafael Damian Cristea Cristea Miembro 1 - github@email (dev_A)
 * @author      Luis Manuel Rodriguez Lopez Miembro 2 - github@email (dev_B)
 * @author      Javier Castilla Tejeda Miembro 3 - github@email (dev_C)
 *
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */
import java.io.*;
import java.net.*;
public class MiembroToken{
    static class Hilos extends Thread{
        String[] args;
        int puerto, puertoenvio, id;
        boolean token;
        Socket Envio, Escucha;
        ServerSocket Server;
        public Hilos(String args[]) throws IOException {
            this.puerto = Integer.parseInt(args[1]);;
            if (args[3].equals("no")){
            this.puertoenvio = puerto+1;
            }
            else this.puertoenvio = 10000;
            this.id = Integer.parseInt(args[0]);
            this.token = args[2].equals("yes"); 
            this.Server = new ServerSocket(puerto);
        }
        void EscucharToken() throws IOException{
            System.out.println("Escuchando en " + puerto);
            Escucha = Server.accept();
            token = true;
        }
        void MostrarToken() throws InterruptedException, IOException{
            System.out.println("Esperando token");
            Thread.sleep(id*1000);
            EnviarToken();
            token = false;
        }
        void EnviarToken() throws IOException{
            System.out.println("Enviando a "+puertoenvio+"....");
            Envio = new Socket("localhost", puertoenvio);
            Envio.close();
        }
        public void run()
        {
            while(true)
            {
                try{
                    if(token){
                        MostrarToken();
                        System.out.println("No posee el token\n\n");
                    }else{
                        EscucharToken();
                        System.out.println("Posee el token");
                    }
                }catch(Exception e){
                    System.out.println("No se puede ejecutar el run");
                }
                
            }
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException{
        Hilos hilo = new Hilos(args);
        System.out.println("Id del hilo: "+hilo.id);
        hilo.start();
}
}
