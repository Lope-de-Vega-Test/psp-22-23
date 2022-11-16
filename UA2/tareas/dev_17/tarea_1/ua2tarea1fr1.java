
class Contador{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }
    //funcion que usaremos para incrementar el contador
    public void incrementa() {
        c++;
        
    }
    //funcoin que devuelve el valor de contador
    public int valor() {
        return c;
    }
}

class HiloSumador1 extends Thread 
{
    private Contador contador;
    //con esta funcion podremos crear los hilos, asignarle un nombre y un numero de contador.
    public HiloSumador1(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }
    //aqui vamos a llamar a la funcion incrementar 1000 veces, para que aumente 1000 cada hilo.
    public void run() {
        
        for(int j=0; j<1000;j++){
            contador.incrementa();
        }
        System.out.println("el contador de "+getName()+" es " + contador.valor());
        
    }
}


public class ua2tarea1fr1 {
    public static void main(String[] args) {        
        System.out.println("-------------------------");
        System.out.println("BLOQUES NO SINCRONIZADOS");
        System.out.println("-------------------------");
        //creamos los hilos, cada uno sn su nombre y con la variable contador
        Contador cont = new Contador(0);
        HiloSumador1 hilo1 = new HiloSumador1("hilo 1", cont);
        HiloSumador1 hilo2 = new HiloSumador1("hilo 2", cont);
        HiloSumador1 hilo3 = new HiloSumador1("hilo 3", cont);
        HiloSumador1 hilo4 = new HiloSumador1("hilo 4", cont);
        HiloSumador1 hilo5 = new HiloSumador1("hilo 5", cont);
        //los iniciamos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        System.out.println("Hilos ejecutandose.");
        //usamos .join para hacer que el programa principal espere hasta que este hilo este muerto
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
            
        }catch (InterruptedException e)
        {
            System.out.println(""+e);
        }
        System.out.println("-------------------------------");
        System.out.println("Finalizada la ejecucion de los hilos");
        System.out.println("-------------------------------");
        System.out.println("Contador final: "+cont.valor());
    }
}
//podemos observar que cada vez que ejecutamos el programa el contador tiene un numero distinto, debido a que no esta sincronizado