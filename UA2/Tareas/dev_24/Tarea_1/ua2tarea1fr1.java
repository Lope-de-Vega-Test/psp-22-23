/*T1 - Tarea 1 - Programación y sincronización de hilos en Java 1
Criterios a), b), c), d), e), f), g) y h)
Fecha de Entrega: 09/11/2022 - 14:00:00
FR1: Crea un programa en Java que lance 5 hilos. Cada hilo incrementará una variable contador de tipo entero en 1000 unidades. Esta variable estará compartida por todos los hilos. Comprueba el resultado final de la variable y reflexiona sobre el resultado. ¿Se obtiene el resultado esperado? - 3 puntos
FR2: Modifica el programa anterior para sincronizar el acceso a dicha varaible. Lanza primero los hilos mediante la clase Thread y después mediante el interfaz Runnable. Comprueba los resultados e indica las variaciones - 3 puntos
Implementa el control de errores básico - 2 puntos
Documenta el código indicando el funcionamiento del programa y las diferencias que has observado entre el primer y el segundo apartado. - 2 puntos
Entregables:
tareas\dev_X\tarea_1\ua2tarea1fr1.java
tareas\dev_X\tarea_1\ua2tarea1fr2.java
tareas\dev_X\tarea_1\ua2tarea1fr2runnable.java*/



class Contador
{
    private int cont = 0;  
    Contador (int cont) { 
        this.cont = cont; 
    }

    public void incrementar() {
        cont++; 
    }

    public int valor() {
        return cont; 
    }
} 

class HiloSumador extends Thread 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador cont) {
        setName(nombre);
        contador = cont;
    }

    public void run() {
        for(int j=0; j<1000;j++) 
        {
            contador.incrementar();
        }
        System.out.println("Hilo: " + contador.valor()); 
    }
}

public class Ua2tarea1fr1 {


    public static void main(String[] args) {
        
        Contador contar = new Contador(0);
        
        HiloSumador hilo1 = new HiloSumador("Hilo 1", contar);
        HiloSumador hilo2 = new HiloSumador("Hilo 2", contar);
        HiloSumador hilo3 = new HiloSumador("Hilo 3", contar);
        HiloSumador hilo4 = new HiloSumador("Hilo 4", contar);
        HiloSumador hilo5 = new HiloSumador("Hilo 5", contar);
        
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        }
        catch (InterruptedException e)
        {
            
            System.out.println("Error, pruebe de nuevo");
        }
        
        
        System.out.println("Valor del Contador: " + contar.valor());
    } 
}
