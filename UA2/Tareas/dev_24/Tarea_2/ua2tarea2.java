import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.processing.SupportedOptions;
import javax.xml.stream.util.EventReaderDelegate;

public class ua2tarea2 {
    
    public static void main(String[] args) {
        
        CuentaCorriente cuenta=new CuentaCorriente(30);
       
        Hilos hilo1=new Hilos("Hilo1", 60, cuenta);
        Hilos hilo2=new Hilos("Hilo2", 200, cuenta);
        Hilos hilo3=new Hilos("Hilo3", 300, cuenta);
        Hilos hilo4=new Hilos("Hilo4", 56, cuenta);
        Hilos hilo5=new Hilos("Hilo5", 100, cuenta);
       
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
        }
        catch(InterruptedException e){}
        
        System.out.println("El saldo final es: "+cuenta.saldo+" €");
    }
}

class CuentaCorriente{
   
    int saldo;
    int incremento;
   
        this.saldo=0;
        this.incremento=0;
    }
  
    public CuentaCorriente(int saldo){
        this.saldo=saldo;
    }
   
    public void setSaldo(int saldo) {
        this.saldo = saldo;
        
        try{
        Thread.sleep((int)(Math.random()*(1751)+250));
        }
        catch(InterruptedException e){}
    }
    public int getSaldo() {
        try{
        Thread.sleep((int)(Math.random()*(1751)+250));
        }
        catch(InterruptedException e){}
        return saldo;
    }
    
    public synchronized void incrementar(){
        incremento++;
    }
    
    public synchronized void resultado(String nombre){
        System.out.println("--------------------------------------");
        System.out.println("El valor previo del saldo era: "+getSaldo()+"€");
        System.out.println("El saldo actual es: "+(saldo+=incremento)+"€");
       
        incremento=0;
        System.out.println("Quien ha realizado el ingreso es: "+nombre);
        System.out.println("--------------------------------------");
        System.out.println("");
        System.out.println("");
    }
}

class Hilos extends Thread{
    
    int num;
    private CuentaCorriente cuenta;
    
    public Hilos(String nombre, int num, CuentaCorriente cuenta){
        setName(nombre);
        this.num=num;
        this.cuenta=cuenta;
    }
   
    public void run(){
        
        for (int i=0; i<num; i++){
            cuenta.incrementar();
        }
        
        cuenta.resultado(getName());
    }
}
