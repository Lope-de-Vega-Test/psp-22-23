
package ua2tarea1fr2runnable;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;



//Implementamos la interfaz Runnable
public class ua2tarea1fr2runnable implements Runnable {
     //Creamos una variable contador
    private Contador contador;
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: 5 sumadores de 1000");
        System.out.println("-------------------------------");
        
        
        //Contador cont = new Contador(0);
        Contador cont = new Contador(0);
        ua2tarea1fr2runnable hilo = new ua2tarea1fr2runnable(cont);
		Thread hilo1 = new Thread(hilo);
                Thread hilo2 = new Thread(hilo);
                Thread hilo3 = new Thread(hilo);
                Thread hilo4 = new Thread(hilo);
                Thread hilo5 = new Thread(hilo);
        
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        //Lanzamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        //No se sigue ejecutando el código hasta que terminen todos los hilos
        try {
        hilo1.join();
        hilo2.join();
        hilo3.join();
        hilo4.join();
        hilo5.join();

        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        //Muestra el valor final del contador
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }


    
public ua2tarea1fr2runnable(Contador c) {
        //Asignamos valor al contador
        contador = c;
    }
    
	public synchronized void run() {
            //synchronized(contador) {
            for(int j=0; j<1000;j++)
            {
                contador.incrementa();
                /*
                try {
                    sleep(100);
                }
                catch (InterruptedException e) {}
                */
            }
            //Muestra el nombre del hilo acutal y el valor d contador
            System.out.println("Hola desde el Hilo" +
	       Thread.currentThread().getId() + " - contador vale " + contador.valor());
        }
}
    
class Contador{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }
    //Incrementa el valor en 1
    public void incrementa() {
        c++;
    }
    //Devuelve el valor
    public int valor() {
        return c;
    }
} // Fin Class Contador