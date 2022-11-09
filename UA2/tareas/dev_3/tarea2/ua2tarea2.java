// Compilar y ejecutar: javac ua2tarea2.java && java ua2tarea2

import java.io.*;
import java.lang.Thread;

class CuentaCorriente //creamos la clase CuentaCorriente.
{
    long dormir = (long) Math.random()*(2000-250+1)+250; //le asignamos a una variable el valor aleatorio que usamos en los sleep.

    private int saldo = 0;  // Atributo saldo.
    CuentaCorriente (int saldo) { //constructor para enviar el valor asignado al saldo.
        this.saldo = saldo;
    }

    public void setSaldo(int saldo) {

        //asignamos un nuevo valor al saldo cuando sea introducido.
        this.saldo = saldo;

        //usamos un sleep con tiempo aleatorio definido anteriormente.
        try{
            Thread.sleep(dormir);
        }catch(InterruptedException e){
            //nothing to do here.
        }
    }

    public int getSaldo() {

        //usamos un sleep con tiempo aleatorio definido anteriormente.
        try{
            Thread.sleep(dormir);
        }catch(InterruptedException e){
            //nothing to do here
        }

        //devolvemos el valor actual del saldo.
        return saldo;
    }

    public synchronized void anadirSaldo(int cantidad) {
        //esta función sirve para añadir al saldo la cantidad asignada en los diferentes hilos.
        //usamos la funcion setSaldo para que intervenga la función .sleep().
        setSaldo(saldo+=cantidad);
    }

    public synchronized void mostrar(String nombre, int cantidad) {
        //mostramos en cada hilo de forma sincronizada el saldo que habia antes de que intervenga.
        System.out.println("--------------------------------------------");
        System.out.println("INICIO DEL " + nombre);
        System.out.println("La cantidad inicial del saldo es: " + getSaldo());

        //llamamos a la funcion anadirSaldo pasandole el valor que debe sumarle que en cada hilo es diferente.
        anadirSaldo(cantidad);
        //Por último, mostramos la cantidad de saldo que queda al finalizar la operación.
        System.out.println("La cantidad final del saldo es:  " + getSaldo());
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println();
    }
}

class HiloSaldo extends Thread //Creamos la clase HiloSaldo con la que crearemos los hilos que aumentarán el valor del saldo.
//En este caso implementaremos los hilos heredando de la clase Thread, lo que afectará a la manera en la que
//llamaremos a los hilos en la función main.
{
    private CuentaCorriente saldo;//Instanciamos a la clase CuentaCorriente.
    int cantidad=0; //Declaramos la variable con la que le sumaremos el valor al saldo

    public HiloSaldo(String nombre, CuentaCorriente cc, int cantidad) {
        //Creamos un constructor del hilo para que se le asigne el valor de saldo, una cantidad a sumar y un nombre.
        setName(nombre);
        saldo = cc;
        this.cantidad = cantidad;
    }

    public synchronized void run() {
        //Esta función nos servirá para que, tras declarar los 5 hilos, podamos ejecutarlos.
        saldo.mostrar(getName(),cantidad);
    }
}

public class ua2tarea2 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");

        CuentaCorriente saldo = new CuentaCorriente(0);//Inicializamos el saldo con valor 0.

        //Declaramos los 5 hilos que necesitamos para el ejercicio pasandole los parámetros de nombre, cantidad y saldo.

        //Para asignarle el nombre lo introducimos directamente entre comillas, la cantidad sin comillas ya que no es
        //un String y para el saldo, le asignamos la variable declarada antes.
        HiloSaldo hilo1 = new HiloSaldo("HILO 1", saldo,50);
        HiloSaldo hilo2 = new HiloSaldo("HILO 2", saldo,100);
        HiloSaldo hilo3 = new HiloSaldo("HILO 3", saldo,150);
        HiloSaldo hilo4 = new HiloSaldo("HILO 4", saldo,200);
        HiloSaldo hilo5 = new HiloSaldo("HILO 5", saldo,250);

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println();

        //Iniciamos el proceso de todos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        //En este try catch arrancamos los cinco hilos usando .join() para mantener el orden de ejecucion entre
        //los hilos para que se ejecute primero el 1, luego el 2, etc...
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
        System.out.println("Valor Final del Contador: " + saldo.getSaldo());//Mostramos el valor final.
        System.out.println("--------------------------------------");
    }

    //FR3 y FR4: La diferencia entre un caso y otro es que en el FR4, al usar funciones synchronized, los hilos se
    //coordinan a la hora de acceder al saldo, por lo que las operaciones de sumar y mostrar se ejecutan de forma ordenada,
    //esperando entre los hilos a que el anterior termine de ejecutarse. Por el contrario, en el FR3, al no sincronizar los procesos,
    //los hilos no se coordinan y se ejecutan todos a la vez, accediendo al mismo tiempo al saldo y modificandolo a la vez
    //que otro intenta leerlo, lo que causa que el valor final no sea correcto.
}
