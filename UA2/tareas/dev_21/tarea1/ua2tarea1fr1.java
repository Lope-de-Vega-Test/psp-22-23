package javahere;


public class Main{
    public static void main(String[] args){
        // creacion de objeto contador
        Contador c = new Contador();
        // creacion de hilos + asignacion de nombre
        Hilo h1 = new Hilo(c); h1.setName("Hilo 1");
        Hilo h2 = new Hilo(c); h2.setName("Hilo 2");
        Hilo h3 = new Hilo(c); h3.setName("Hilo 3");
        Hilo h4 = new Hilo(c); h4.setName("Hilo 4");
        Hilo h5 = new Hilo(c); h5.setName("Hilo 5");
        // ejecucion de hilos
        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        // al proceso padre anidar los hilos para que se ejecuten todos antes de terminar
        try{
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
        } catch (Exception e) {}

    }
}


// CONTADOR
class Contador{
       int contador;
       
       public Contador(){
           this.contador=0;
       }
       
       public int getContador(){
           return this.contador;
       }
       
       public void setContador(int x){
           this.contador=x;
       }
       
       public void increaseAThousand(){
            for(int i=0; i<1000; i++){
                contador++;
            }
       }
}







// HILO
class Hilo extends Thread{
    Contador c;
    public Hilo(Contador c){
        this.c = c;
    }
    @Override
    public void run(){
        // incrementa el contador en 1000 y muestra el nombre del hilo + el contador
        c.increaseAThousand();
        System.out.println(this.getName() + ": " + c.getContador());
    }
    
    
    
}