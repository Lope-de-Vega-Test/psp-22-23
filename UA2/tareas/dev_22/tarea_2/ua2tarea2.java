/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sincronizahilos2;

/**
 *
 * @author Ignacio
 */
class CuentaCorriente {

    int saldo;

    public CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldo() {
        try {
            int numero = (int) (Math.random() * ((2000 - 250 + 1) + 250));

            Thread.sleep(numero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return saldo;
    }

    public void setSaldo(int saldo) {
        try {
            int numero = (int) (Math.random() * ((2000 - 250 + 1) + 250));

            Thread.sleep(numero);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.saldo = saldo;
    }
    //Parámetro, constructor y get y set del objeto

    public synchronized void aumentaSaldo(int ingreso, String nombreHilo) {
        //Es en esta función en la que usamos get y set para mostrar y guardar el saldo
        //Es por eso que es en estos get y set donde ponemos los sleep
        System.out.println("Saldo antes: " + this.getSaldo());
        this.setSaldo(saldo+ingreso);
        System.out.println("Saldo después: " + this.getSaldo());
        System.out.println("Ingresado por " + nombreHilo);
    }
}

class HiloIngresa extends Thread {

    int dinero;
    String nombre;
    CuentaCorriente cuenta;

    public HiloIngresa(int dinero, String nombre, CuentaCorriente cuenta) {
        this.dinero = dinero;
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    public int getDinero() {
        return dinero;
    }

    public String getNombre() {
        return nombre;
    }
    
    //Parámetros, constructor y un par de gets

    public void run() {
        synchronized(cuenta){//Run sincronizado con el objeto en común
        cuenta.aumentaSaldo(this.getDinero(), this.getNombre());
        }
    }

}

public class SincronizaHilos2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        CuentaCorriente cuenta = new CuentaCorriente(500);//Creamos la cuenta

        System.out.println("Saldo inicial: " + cuenta.getSaldo());//Mostramos valor inicial

        HiloIngresa hilo1 = new HiloIngresa(50, "Hilo 1", cuenta);
        HiloIngresa hilo2 = new HiloIngresa(50, "Hilo 2", cuenta);
        HiloIngresa hilo3 = new HiloIngresa(50, "Hilo 3", cuenta);
        HiloIngresa hilo4 = new HiloIngresa(50, "Hilo 4", cuenta);
        HiloIngresa hilo5 = new HiloIngresa(50, "Hilo 5", cuenta);//Creamos hilos
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();//Comienza la ejecución de los hilos
        
        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();//Los incluimos
            
        }catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("Saldo final: "+cuenta.getSaldo());//Mostramos valor final
        
    }
    
    /*Con synchronized:
        Saldo inicial: 500
        Saldo antes: 500
        Saldo después: 550
        Ingresado por Hilo 2
        Saldo antes: 550
        Saldo después: 600
        Ingresado por Hilo 5
        Saldo antes: 600
        Saldo después: 650
        Ingresado por Hilo 1
        Saldo antes: 650
        Saldo después: 700
        Ingresado por Hilo 4
        Saldo antes: 700
        Saldo después: 750
        Ingresado por Hilo 3
        Saldo final: 750
        BUILD SUCCESSFUL (total time: 17 seconds)
    
    Sin synchronized:
        Saldo inicial: 500
        Saldo antes: 500
        Saldo antes: 500
        Saldo antes: 550
        Saldo antes: 550
        Saldo después: 550
        Ingresado por Hilo 4
        Saldo antes: 550
        Saldo después: 550
        Ingresado por Hilo 2
        Saldo después: 600
        Ingresado por Hilo 5
        Saldo después: 600
        Ingresado por Hilo 3
        Saldo después: 600
        Ingresado por Hilo 1
        Saldo final: 600
        BUILD SUCCESSFUL (total time: 7 seconds)
    
    Aunque ejecutando varias veces cada caso el resultado varía, ocurre siempre lo mismo: con synchronized tarda 
    bastante más. Eso significa que los hilos están esperando cuando les toca, es decir, cuando se lo hemos indicado
    */
    

}
