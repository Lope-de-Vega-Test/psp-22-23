package bloquesnosincronizados;

import java.util.Scanner;

/**
*
* @author Lucía Luna
*/

public class BloquesNoSincronizados {

public static void main(String[] args) {




System.out.println("-------------------------------");




System.out.println("Hilos: Bloques NO Sincronizados");




System.out.println("-------------------------------");






Contador cont = new Contador(1000);




//Creamos los 5 hilos




HiloSumador hilo1 = new HiloSumador("Soy el hilo 1", cont);




HiloSumador hilo2 = new HiloSumador("Soy el hilo 2", cont);




HiloSumador hilo3 = new HiloSumador("Soy el hilo 3", cont);




HiloSumador hilo4 = new HiloSumador("Soy el hilo 4", cont);




HiloSumador hilo5 = new HiloSumador("Soy el hilo 5", cont);




System.out.println("Comienza la ejecución del hilo 1 ...");




System.out.println("--------------------------------------");




//comienza hilo 1

hilo1.start();



System.out.println("Comienza la ejecución del hilo 2 ...");




System.out.println("--------------------------------------");




//comienza hilo 2

hilo2.start();





System.out.println("Comienza la ejecución del hilo 3 ...");




System.out.println("--------------------------------------");


//comienza hilo 3

hilo3.start();



System.out.println("Comienza la ejecución del hilo 4 ...");




System.out.println("--------------------------------------");


//comienza hilo 4

hilo4.start();



System.out.println("Comienza la ejecución del hilo 5 ...");




System.out.println("--------------------------------------");

//comienza hilo 5

hilo5.start();



//join() suele utilizarse para mantener un orden en la secuencia de los hilos

hilo1.join();
hilo2.join();
hilo3.join();
hilo4.join();
hilo5.join();




System.out.println("--------------------------------------");




System.out.println("... Finaliza la ejecución de todos los hilos ");



System.out.println("--------------------------------------");


System.out.println("Valor Final del Contador: " + cont.valor());


System.out.println("--------------------------------------");
}



}

//Clase HiloSumador
package bloquesnosincronizados;

/**
*
* @author Lucía Luna
*/


class HiloSumador implements Runnable
{

private Contador contador;


public HiloSumador(String nombre, Contador c) {


setName(nombre);

contador = c;

}



public void run() {

for(int j=0; j<1000;j++)

{


contador.incrementa();

}

System.out.println(getName() + " - contador vale " + contador.valor());

}

    private void setName(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private String getName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void start() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void join() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


} // Fin Class HiloSumador
    

//Clase Contador
package bloquesnosincronizados;
/**
 *
 * @author Lucía Luna
 */
class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c++;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
    
    
}
