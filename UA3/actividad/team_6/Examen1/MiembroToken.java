
import java.io.*;
import java.net.*;


/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        14/12/2022
 * @author      José Elías Alonso Trenas Miembro 1 - joselias2003@gmail.com (dev_4)
 * @author      Irene Alba Posadas Miembro 2 - ireealba23@gmail.com (dev_1)
 * @author      Lucía Luna Posadas Miembro 3 - lucialunaposadas@gmail.com (dev_20)
 * @author      Lucia Maria Ayllon Pozo Miembro 4 - luciaayllon7@gmail.com (dev_6)
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

//Clase ExecutionTimer, contador para temporizador 
class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()
    {
        elapsedTime();
        System.out.println("El tiempo que ha tardado en obtener el token es: "+timeElapsed/1000000+" milisegundos ");
    }
}

//Clase MiembroToken
public class MiembroToken
{
    /* @brief       Classic java main, starting execution
     * @param       arg[0] = [id]
     * @param       arg[1] = [puerto]
     * @param       arg[2] = [token_al_inicio]
     * @param       arg[3] = [soy_el_ultimo]
     */


    //Clase Hilo, constara de las variables recibiendo el tipo de respuesta adecuada para llevar acabo el proceso
    static class Hilo extends Thread{
        String[] args;
        int puerto;
        int puertoEnvio;
        int id;
        boolean token;
        Socket Envio;
        ServerSocket Server;
        Socket Escucha;
        ExecutionTimer Timer;

        public Hilo(String args[]) throws IOException {
            this.puerto = Integer.parseInt(args[1]);;
            
            if (args[3].equals("no"))
            {

            this.puertoEnvio = puerto+1;

            }else this.puertoEnvio = 10000;
            
            this.id = Integer.parseInt(args[0]);
            
            this.token = args[2].equals("yes"); 
            //Timer.start();// No funciona :'(
            
            this.Server = new ServerSocket(puerto);

        }

        //Funcion escuchar que recive el puerto por el que miembro lleva al programa.
        void escuchar() throws IOException{
            //Timer.stop();
            //Timer.printElapsedTime();
            System.out.println("Escuchando en " + puerto+ ".....");
            Escucha = Server.accept();
            token = true;
        }
        
        //Funcion que permite saber si el miembro ha recibido el token
        void mostrar() throws InterruptedException, IOException{
            System.out.println("Esperando.....");
            Thread.sleep(id*1000);
            enviar();
            token = false;
        }
        
        //Funcion que permite enviar el token de vuelta al puerto de entrada
        void enviar() throws IOException{
            System.out.println("Enviando a "+puertoEnvio+"....");
            Envio = new Socket("localhost", puertoEnvio);
            Envio.close();
        }
        
        //Funcion que permite ejecutar todo el proceso
        public void run()
        {
            while(true)
            {
                try
                {
                    if(token)
                    {
                        mostrar();
                        System.out.println("No tiene el token\n\n");
                    }else{
                        escuchar();
                        System.out.println("Tiene el token");
                    }
                }catch(Exception e){
                    System.out.println("ERROR");
                }
                
            }
        }
    }

    //Funcion Main
    public static void main(String[] args) throws IOException, InterruptedException 
    {
        Hilo hilo = new Hilo(args);
        System.out.println("ID: "+hilo.id);
        hilo.start();
    }

}