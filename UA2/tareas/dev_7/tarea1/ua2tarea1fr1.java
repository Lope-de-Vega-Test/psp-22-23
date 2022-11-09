
package ua2tarea1fr1;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ua2tarea1fr1 {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: 5 sumadores de 1000");
        System.out.println("-------------------------------");
        //Declaramos una nueva variable de tipo contador inicializada a 0
        Contador cont = new Contador(0);
        //Creación de los hilos asignando nombre y el contador
        HiloSumador hiloSuma1 = new HiloSumador("Hilo Sumador1", cont);
        HiloSumador hiloSuma2 = new HiloSumador("Hilo Sumador2", cont);
        HiloSumador hiloSuma3 = new HiloSumador("Hilo Sumador3", cont);
        HiloSumador hiloSuma4 = new HiloSumador("Hilo Sumador4", cont);
        HiloSumador hiloSuma5 = new HiloSumador("Hilo Sumador5", cont);
        
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        //Lanzamos los hilos
        hiloSuma1.start();
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();
        
        //No se sigue ejecutando el código hasta que terminen todos los hilos
        try {
            hiloSuma1.join();
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
            
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
}

class Contador
{
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

//Creamos la clase Tread
class HiloSumador extends Thread 
{
    //Creamos una variable contador
    private Contador contador;
    //Pasamos por valor el nombre y el contador 
    public HiloSumador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }
//Incrementa de forma asíncrona  el contador hasta 1000 por eso hay errores y no se muestra lo que quieres
    public void run() {
        for(int j=0; j<1000;j++) 
        {
            contador.incrementa();
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
}// Fin Class HiloSumador