package UA2.tareas.dev_6.tarea2;

import java.util.ArrayList;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

class CuentaCorriente {
    Double saldo;

    
    public CuentaCorriente(double saldo){
        this.saldo = saldo;
    }

    
    public void setSaldo(double saldo) {
        try{
            sleep(2000);
            this.saldo = saldo;
        }catch(Exception e){
            //Nothing...
        }
    }

    
    public double getSaldo() {
        try{
            sleep(2000);
        }catch(Exception e){
            //Nothing...
        }
        return saldo;
    }
    
    
    public void ingresarSaldo(double cantidad, String nombre){                        
        synchronized(saldo){
            double oldSaldo = saldo;
            saldo += cantidad;
            System.out.println("cuenta antes del ingreso " + oldSaldo + ". \n"
                    + "cuenta actualmente: " + saldo + ". \n"
                            + "el ingreso ha sido reaalizado por: " + nombre + ".");
            System.out.println("-------------------------------------------------");
        }
    }
}
public class Ingreso extends Thread{
    Scanner scan = new Scanner(System.in);
    
    CuentaCorriente cuenta;
    double cantidad;
    
    public Ingreso(CuentaCorriente cuenta, double cantidad, String nombre){
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        setName(nombre);
    }
    public void run(){
        cuenta.ingresarSaldo(cantidad, getName());
    }

public class Ua2tarea2 {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(25);
        
        System.out.println("El valor inicial es: " + cuenta.getSaldo());
        
        Ingreso hilo1 = new Ingreso(cuenta, 38, "Lucia");
        Ingreso hilo2 = new Ingreso(cuenta, 96, "Paula");
        Ingreso hilo3 = new Ingreso(cuenta, 3968, "Juan");
        Ingreso hilo4 = new Ingreso(cuenta, 3687, "miguel");
        Ingreso hilo5 = new Ingreso(cuenta, 589, "Maria");
        
        
        
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        try{
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }catch(Exception e){
            //Nothing...
        }
        
        System.out.println("---------------------------");
        System.out.println("Operaciones finalizadas...");
        System.out.println("---------------------------");
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
