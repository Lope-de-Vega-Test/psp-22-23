// Compilar y ejecutar: javac ua2tarea1fr3.java && java ua2tarea1fr3

import java.io.*;
import java.lang.Thread;

class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) {
        this.c = c;
    }

    public synchronized void incrementa() {
        //Usaremos esta función para incrementar en 1 el valor del contador en los diferentes hilos
        //cada iteración del bucle for que añadiremos más adelante.
        //Hacemos uso de "synchronized" para que los hilos se coordinen a la hora de acceder al contador.
        c++;
    }

    public int valor() {
        //devolvemos el valor actual del contador.
        return c;
    }
} // Fin Class Contador

class HiloSumador implements Runnable
//En este caso se nos pide implementar la interfaz Runnable, lo que afectará a la manera en la que
//llamaremos a los hilos en la función main.
{
    private Contador contador;//Instanciamos a la clase Contador.

    public HiloSumador(Contador c) {
        //creamos un constructor del hilo para que se le asigne el valor de contador.
        this.contador = c;
    }

    public synchronized void run() {
        //Esta función nos servirá para que, tras declarar los 5 hilos, podamos ejecutarlos.

        //Gracias a este for, que ya nombramos antes, hacemos que cada hilo, al iniciarse, incremente en 1 el valor del contador
        //por cada iteración, de tal modo que cuando finalice el bucle, cada hilo incrementará en 1000 dicho valor.
        for(int j=0; j<1000;j++)
        {
            contador.incrementa();

        }
        //Por último, mostramos el valor que tiene el contador al finalizar la operación.
        System.out.println(" El contador vale " + contador.valor());
    }
} // Fin Class HiloSumador

public class ua2tarea1fr3 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");

        Contador cont = new Contador(0);//Inicializamos el contador con valor 0.

        //Declaramos los 5 hilos que necesitamos para el ejercicio
        Runnable hilo1 = new HiloSumador( cont);
        Runnable hilo2 = new HiloSumador( cont);
        Runnable hilo3 = new HiloSumador( cont);
        Runnable hilo4 = new HiloSumador( cont);
        Runnable hilo5 = new HiloSumador( cont);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        //Iniciamos el proceso de todos los hilos
        new Thread(hilo1).start();
        new Thread(hilo2).start();
        new Thread(hilo3).start();
        new Thread(hilo4).start();
        new Thread(hilo5).start();

        //Aunque no es necesario, usamos este sleep() para dar tiempo a los 5 hilos a que incrementen el contador y lo
        //muestren antes de que aparezca en pantalla el valor final.
        try {
            Thread.sleep(700);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());//Mostramos el valor final.
        System.out.println("--------------------------------------");
    }
}
