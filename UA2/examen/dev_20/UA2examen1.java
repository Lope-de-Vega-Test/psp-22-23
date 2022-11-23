package ua2examen1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Lucía Luna
 */

class VasodeCerveza{
    
    private int vaso;
    private int pinta;
    private int mediapinta;
    private boolean disponible = false; 
    
    
    //Función encargada de sincronizan los hilos
    public synchronized int get() {
        while(!disponible) {        
            try {
                wait();             
            } catch(InterruptedException e) {}            
        }
        int vaso_tmp = 0;
        if(vaso == 6)
        {
            vaso_tmp = vaso;
            vaso = 0;
        }
        System.out.println( "Consume: " + vaso_tmp);
        disponible = false; 
        notify();           
        return vaso_tmp;  
        //Devuelve los vasos
       
    }
 
    //Constructor
    public VasodeCerveza(int vaso, int tipo) {
        this.vaso = vaso;
        this.pinta = pinta;
        this.mediapinta = mediapinta;
        
    
    }

    //Método get
    public synchronized int getvaso() {
        return vaso;
    }

    //Método set
    public void setvaso(int idvaso) {
        this.vaso = idvaso;
    }

    // Método get
    public int getPinta() {
        return pinta;
    }

    //Método set
    public void setPinta(int pinta) {
        this.pinta = pinta;
    }
    
    //Método set
      public void setMediaPinta(int mediapinta) {
        this.mediapinta = mediapinta;
    }
     
    //Método get
       public int getMediapinta() {
        return mediapinta;
    }
}



//Comienza la clase camarero
class  Camarero {
    
    int listavasos;

    public int getListaVasos() {
        return listavasos;
    }

    public void setListaVasos(int listaVasos) {
        this.listavasos = listavasos;
    }

    public Camarero(int listaVasos) {
        this.listavasos = listavasos;
    }

    public void servirCerveza(){
    
    
}
     public void devolverCerveza(){
    
    
}
      public void contarVasos(){
    
    
}
}

//Termina la clase camarero



//Comienza la clase HilosClientes
class HilosClientes extends Thread 
{
    private VasodeCerveza vaso;
    private int n;

    public HilosClientes(String string, VasodeCerveza cont) {
    }

    public void HilosCLientes (VasodeCerveza c, int n) {
        vaso = c;
        this.n = n;
    }

    public void run() { 
        int valor = 0;       
        for(int i=0; i<10;i++)
        {
            valor = vaso.get(); // Se sirve
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
}
//Termina la clase camarero


//Clase ExecutionTimer. Calcula el tiempo que tarda en ejecutar el programa.
class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()    
    {
        elapsedTime();
        System.out.println("Nanoseconds: " + timeElapsed);
        System.out.println("Milliseconds: " + timeElapsed / 1000000);
    }
}
//Termina la clase executiontimer

public class UA2examen1 {

    
    public static void main(String[] args) {
         
        
       
        System.out.println("HilosClientess: Bloques NO Sincronizados");
        
 

        VasodeCerveza cont = new VasodeCerveza(100, 0);
        
        HilosClientes HilosClientes1 = new HilosClientes("Homer", cont);
        HilosClientes HilosClientes2 = new HilosClientes("Barney", cont);
        HilosClientes HilosClientes3 = new HilosClientes("Carl", cont);
        HilosClientes HilosClientes4 = new HilosClientes("Lenny", cont);
        HilosClientes HilosClientes5 = new HilosClientes("Lurleen", cont);
      
        System.out.println("Se inicia la ejecución de los hilos clientes");
        
        //Comienza hilo1
        HilosClientes1.start();
        
        //Comienza hilo2
        HilosClientes2.start();
        
        //Comienza hilo3
        HilosClientes3.start();
        
        //Comienza hilo4
        HilosClientes4.start();
        
        //Comienza hilo5
        HilosClientes5.start();
        

        try {
        //Usamos join para mantener un orden en la secuencia de los hilos
        HilosClientes1.join();
        HilosClientes2.join();
        HilosClientes3.join();
        HilosClientes4.join();
        HilosClientes5.join();
        }
        catch (InterruptedException e)
        {
            
        }

        
        System.out.println("Termina la ejecución de los hilos");
        System.out.println("Valor Final del Contador: " + cont.getvaso());
        
   
        
    }
}
    
