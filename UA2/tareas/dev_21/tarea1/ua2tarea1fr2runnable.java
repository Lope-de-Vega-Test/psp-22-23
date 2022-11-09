package javahere;


public class Main{
    public static void main(String[] args){
    // creacion de contador
        Contador c = new Contador();
        // creacion de hilos + ejecucion
        Hilo h1 = new Hilo(c); 
        new Thread(h1).start();
        
        Hilo h2 = new Hilo(c);
        new Thread(h2).start();
        
        Hilo h3 = new Hilo(c);
        new Thread(h2).start();

        Hilo h4 = new Hilo(c);
        new Thread(h2).start();

        Hilo h5 = new Hilo(c);
        new Thread(h2).start();
    }
}

class Contador{
       int contador;
       
       public Contador(){
           this.contador=0;
       }
       
       public synchronized int getContador(){
           return this.contador;
       }
       
       public void setContador(int x){
           this.contador=x;
       }
       
       public synchronized void increaseAThousand(){
            for(int i=0; i<1000; i++){
                contador++;
            }
           notify();
       }
}

class Hilo implements Runnable{

    Contador c;
    public Hilo(Contador c){
        this.c = c;
    }
    @Override
    public void run(){
        // incrementa el contador en 1000 y muestra el id del hilo + el contador
        c.increaseAThousand();
        System.out.println("Hilo con ID: "+Thread.currentThread().getId()+":"+c.getContador());
    }
    
}