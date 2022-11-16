package tarea2;

import static java.lang.Thread.sleep;

class cuentaCorriente{
    int saldo = 0;

    //Constructor de la clase
    public cuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    //Getter
    public int getSaldo() {
        
        try{
            //Dormimos el hilo el tiempo indicado por el ejercicio
            Thread.sleep((int)(Math.random()*(1751)+250));
        }
        catch(InterruptedException e){}
        
        return saldo;
    }

    //Setter
    public void setSaldo(int saldo) {
        this.saldo = saldo;
        
        try{
            //Dormimos el hilo el tiempo indicado por el ejercicio
            Thread.sleep((int) Math.random()*(1751)+250);
        }
        catch(InterruptedException e){
            
        }
    }
    
    //Funcion para aumentar el saldo según la cantidad que se le asigne
    public synchronized void incrementa(int cantidad) { //Usamos synchronized para que los hilos se sincronizen
        setSaldo(saldo+=cantidad); 
    }
    
    public synchronized void mostrar(String nombre, int cantidad) {
        //Mostramos en cada hilo el valor de saldo (De forma sincronizada)
        System.out.println("--------------------------------------------");
        System.out.println("--INICIO:-- " + nombre);
        System.out.println("Saldo inicial: " + getSaldo());

        //Llamamos a la funcion incrementa para aumentar la cantidad que se indique en cada hilo
        incrementa(cantidad);
        //Mostramos valor final de saldo
        System.out.println("La cantidad final del saldo es:  " + getSaldo());
        System.out.println();
    }
}

class HiloSumador extends Thread //Creacion de la clase Hilo
{
    private cuentaCorriente saldo;
    int cantidad = 0;

    public HiloSumador(String nombre, cuentaCorriente sald, int cantidad) {
        //Constructor para asignar el valor de saldo, la cantidad a sumar y el nombre
        setName(nombre);
        saldo = sald;
        this.cantidad = cantidad;
    }
    
    public synchronized void run(){
        //Funcion para ejecutar los hilos que hayamos creado
        saldo.mostrar(getName(), cantidad);
    }
}


public class Tarea2 {

    public static void main(String[] args) {

        cuentaCorriente saldo = new cuentaCorriente(0);//Inicializamos el saldo con valor 0.

        //Declaramos los hilos y les asignamos los valores de nombre, saldo y cantidad a sumar, siguiendo nuestro constructor
        HiloSumador  hilo1 = new HiloSumador ("Hilo 1: ", saldo,50);
        HiloSumador  hilo2 = new HiloSumador ("Hilo 2: ", saldo,100);
        HiloSumador  hilo3 = new HiloSumador ("Hilo 3: ", saldo,150);
        HiloSumador  hilo4 = new HiloSumador ("Hilo 4: ", saldo,200);
        HiloSumador  hilo5 = new HiloSumador ("Hilo 5: ", saldo,250);

        System.out.println("Se ejecutan los hilos");

        //Iniciamos el proceso de todos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        //Usamos un try catch para evitar posibles errores en la creación de los hilos
        //Usamos .join para que los hilos se ejecuten de forma ordenada
        try {

            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();

        }

        catch (InterruptedException e)
        {

        }

        System.out.println("Ejecucion de hilos finalizada");
        System.out.println("--------------------------------------");
        System.out.println("Valor final: " + saldo.getSaldo());//Mostramos el valor final.
        System.out.println("--------------------------------------");    }
}
    

