import java.lang.reflect.Constructor;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.processing.SupportedOptions;
import javax.xml.stream.util.EventReaderDelegate;


public class ua2tarea2 {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente();
        //creamos los hilos
        Hilos hilo1 = new Hilos("Hilo1", 60,cuenta);
        Hilos hilo2 = new Hilos("Hilo2", 3000,cuenta);
        Hilos hilo3 = new Hilos("Hilo3", 43,cuenta);
        Hilos hilo4 = new Hilos("Hilo4", 533,cuenta);
        Hilos hilo5 = new Hilos("Hilo5", 122,cuenta);
        //los iniciamos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        //control de errores
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (Exception e) {
            System.out.println("Error, pruebe de nuevo");
        }
        
    }
}
//creamos la clase
class CuentaCorriente {
    float saldo;
    float incremento;
    public CuentaCorriente() {
        this.saldo=0; 
        this.incremento=0;
    }
  
   
    public void setSaldo(float saldo) {
        this.saldo = saldo;
        //usamos el sleep aleatorio
        try {
          Thread.sleep(int)(Math.random()*(1751+250));  
        } catch (Exception e) {
            
        }
        
     
    }
    public float getSaldo() {
         return saldo;
     }
     public float getIncremento() {
         return incremento;
         
     }
     
     public void setIncremento(float incremento) {
         this.incremento = incremento;
         
     }

    public synchronized void incrementar () {
        incremento++;
        

    }

    public synchronized void resultado (String nombre) {
        System.out.println("El saldo anteriormente era:"+saldo);
        System.out.println("El saldo ahora es:" +(saldo+incremento));
        System.out.println("La persona que ha realizado el ingreso es: "+nombre);   
    }
//creamos los hilos 
    class Hilos extends Thread {
        private CuentaCorriente cuenta;
        private int numero;
        
        public Hilos (String nombre, int numero, CuentaCorriente cuenta) {
            
            setName(nombre);
            this.numero = numero;
            this.cuenta =cuenta;
        }
//creamos el run para que realize el incremento
        public synchronized void run(int numero) {
            for (int j =0;j<numero;j++) {
                cuenta.incrementar();
            }
            cuenta.resultado(getName());
        }
    }

}
