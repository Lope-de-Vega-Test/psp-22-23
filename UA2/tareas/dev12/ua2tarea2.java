package paquete;

import java.lang.Thread;

class javauentacorriente // clase de la cuenta corriente 
{
    long sleep = (long) Math.random()*(2000-250+1)+250; // damos un valor random al sleep.

    private int saldo = 0;  // creamos la variable saldo.
    javauentacorriente (int saldo) { // constructor del saldo
        this.saldo = saldo;
    }

    public void setSaldo(int saldo) {

        
        this.saldo = saldo; //damos valor al saldo

        
        try{
            Thread.sleep(sleep); // activamos sleep
        }catch(InterruptedException e){
            //nothing to do here.
        }
    }

    public int getSaldo() {

       
        try{
            Thread.sleep(sleep); // activamos sleep
        }catch(InterruptedException e){ 
            //nothing to do here
        }

        
        return saldo; // nos devuelve el saldo actual
    }

    public synchronized void nuevosaldo(int total) {
        
        setSaldo(saldo+=total); // sincronizamos el saldo nuevo con la cantidad anterior
    }

    public synchronized void mostrar(String hilo, int total) {   // se muestran los hilos

        System.out.println();
        System.out.println("En ejecucion el " + hilo);
        System.out.println("Su saldo inicial es: " + getSaldo());
        nuevosaldo(total); // añadimos el saldo nuevo al total anterior
        System.out.println("La cantidad final del saldo es:  " + getSaldo()); // mostramos ese nuevo total
        System.out.println();
    }
}

class saldohijos extends Thread // en la funcion saldohijos se crearan los hilos que aumenten el valor del saldo
{
    private javauentacorriente saldo;
    int total=0; // total sera el valor que iremos sumando a sueldo

    public saldohijos(String hilo, javauentacorriente cc, int total) { // constructor de los hilos
        setName(hilo);
        saldo = cc;
        this.total = total;
    }

    public synchronized void run() {
        saldo.mostrar(getName(),total); // ejecutar los hilos
    }
}

public class ua2tarea2 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");

        javauentacorriente saldo = new javauentacorriente(0); // saldo con valor 0 sera inicializado
        // declaramos los 5 hilos
        saldohijos hilo1 = new saldohijos("primer hilo", saldo,50);

        saldohijos hilo2 = new saldohijos("segundo hilo", saldo,100);

        saldohijos hilo3 = new saldohijos("tercer hilo", saldo,150);

        saldohijos hilo4 = new saldohijos("cuarto hilo", saldo,200);

        saldohijos hilo5 = new saldohijos("quinto hilo", saldo,250);


        System.out.println("EJECUCUION DE HILOS");
        System.out.println();
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {   // try catch para ordenar los hilos

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

        System.out.println();
        System.out.println("Hilos Terminados");
        System.out.println();
        System.out.println("Total del saldo: " + saldo.getSaldo());  // se muestra el resultado(valor total del saldo)
        System.out.println();
    }
    //FR3: Los procesos se sincronizan, por lo tanto los hilos y, a su vez, las operaciones se ejecutarian de manera ordenada y el resultado seria correcto.
    //FR4 En este caso contrario, al no estar los procesos sincronizados los hilos se ejecutarian a la vez por lo que el resultado seria erroneo.
}
