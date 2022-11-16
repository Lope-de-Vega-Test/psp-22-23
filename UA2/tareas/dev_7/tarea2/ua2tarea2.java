
import java.util.Random;
import java.util.Scanner;


public class Cuenta {

    public static void main(String args[]) {
        System.out.println("");
        CuentaCorriente cc = new CuentaCorriente(50);
        System.out.println("Saldo inicial: "+cc.getSaldo());
        Hilos hilo1 = new Hilos(cc,200,"Pedro");
        Hilos hilo2 = new Hilos(cc,44,"Fernando");
        Hilos hilo3 = new Hilos(cc,800,"Alvaro");
        Hilos hilo4 = new Hilos(cc,1,"Paco");
        Hilos hilo5 = new Hilos(cc,50,"Luis");
        Hilos hilo6 = new Hilos(cc,250,"Antonio");
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            hilo6.join();
        }
        catch (InterruptedException e){}  
        System.out.println("");
        System.out.println("Saldo final: "+cc.getSaldo());
        
    }
}


class CuentaCorriente{
    Random r = new Random();
    private int saldo=0;
    
    public CuentaCorriente() {
        saldo = 0;
    }

    public CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int getSaldo() {
        try {
        Hilos.sleep(r.nextInt(250,2000)); 
        }
        catch (InterruptedException e){}
        
        
       
        return saldo;
    }

    public synchronized void setSaldo(int saldo) {
        try {
        Hilos.sleep(r.nextInt(250,2000)); 
        }
        catch (InterruptedException e){}
        this.saldo = saldo;
    }

    
    public void sacarDinero(int sacar){
    saldo=saldo-sacar;
    }
    
    public synchronized void ingresarDinero(int ingresar, String persona){
        try {
        Hilos.sleep(r.nextInt(250,2000)); 
        }
        catch (InterruptedException e){}
        System.out.println("");
        System.out.println("Estado previo del saldo: "+saldo);
        saldo=saldo+ingresar;
        System.out.println("Estado actual del saldo: "+saldo);
        System.out.println("El ingreso fue realizado por "+persona);
    }
}

class Hilos extends Thread{
    public Scanner sc = new Scanner(System.in);

    //Creamos una variable contador
    CuentaCorriente cc;
    int ingresar;
    String persona;
    //Pasamos por valor el nombre y el saldo 
    public Hilos(CuentaCorriente cc,int ingresar, String nombre) {
        setName(nombre);
        persona=nombre;
        this.ingresar=ingresar;
        this.cc=cc;
        //this.saldo=cc.getSaldo() ;
    }

    public void run() {
       // System.out.println("Valor a ingresar: ");
       // ingresar=sc.nextInt(); sc.nextLine();
        cc.ingresarDinero(ingresar, persona);
    }
}


/*
Quitando el sleep los resultados son los mismos pero quitando los synchronized salen los datos desotrdenados
*/
