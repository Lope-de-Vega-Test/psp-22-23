import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase que contendrá el saldo total de la cuenta. Los threads harán consultas al objeto de esta clase
class CuentaCorriente{
    int saldo;

    public CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int getSaldo(){
     
        try {
            Thread.sleep((int) (Math.random()*(1751)+250));
        } catch (InterruptedException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return saldo;
    }

    public synchronized void setSaldo(int saldo){
        
        try {
            Thread.sleep((int) (Math.random()*(1751)+250));
        } catch (InterruptedException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.saldo = saldo;
    }
    //Suma el saldo al total, muestra el saldo anterior y quien ha realizado la operacion
    public synchronized void añadirSaldo(int añadir, String nombre){ //comentar synchronized y probar el código y cambiar la variable en el main
        System.out.println("La cuenta tenía un saldo de "+this.saldo);
        
        this.saldo+=añadir;
        
        System.out.println("Su nuevo saldo es "+this.saldo);
        System.out.println("Operación realizada por " +nombre);
    }
}
//clase para los hilos que desplegaremos
class Usuario extends Thread{

    String nombre;
    int dinero;
    CuentaCorriente cuenta;

    public Usuario(String nombre, int dinero, CuentaCorriente cuenta) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.cuenta = cuenta;
    }

    public void run(){
        cuenta.añadirSaldo(dinero, nombre);
    }
}


public class tarea_2 {

    public static void main(String[] args) {
        //Creamos e inicializamos la cuenta a 200
        CuentaCorriente cuenta = new CuentaCorriente(200);
        
        //Creamos la lista de hilos que funcionarán en el programa
        ArrayList<Thread> Hilos = new ArrayList<Thread>(); 
        
        for(int i=0; i<5; i++)
        {
            //aleatorizamos el dinero con el que cuenta cada hilo
            int dinero = (int) (Math.random()*(151)+50);
            //creamos el hilo con su nombre, dinero inicial y pasándole la cuenta común a todos los hilos
            Usuario user = new Usuario("Hilo "+i,dinero*i,cuenta);//para comprobar el funcionamiento sin synchronized cambia la variable dinero por un número
            //añadimos este hilo al arraylist de hilos
            Hilos.add(new Thread(user)); 
        }
        //Inicializamos cada hilo
         for(int i=0; i<Hilos.size(); i++)
        {
            Hilos.get(i).start();
        }
        //Esperamos a que finalize la ejecución de cada hilo
         for(int i=0; i<Hilos.size(); i++)
        {
            try
            {
                Hilos.get(i).join();
            }
            catch (InterruptedException e){}
        }
         //mostramos lo deseado
         System.out.println("El total en la cuenta es de "+ cuenta.getSaldo());
         System.out.println("Al quitar el synchronized, los mensajes se nos muestran en desorden.\n Las operaciones se realizan de forma asíncrona");
    }
    
}
