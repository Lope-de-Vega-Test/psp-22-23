/* FR1: Crea un programa en Java que lance 5 hilos. Cada hilo incrementará una variable contador de tipo entero en 1000 unidades. 
/Esta variable estará compartida por todos los hilos. Comprueba el resultado final de la variable y reflexiona sobre el resultado. 
¿Se obtiene el resultado esperado? - 3 puntos*/


class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public   void incrementa() {
        c++;
    }

    public  int valor() {
        return c;
    }
    
} // Fin Class Contador


class HiloSumador extends Thread 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        
            for(int j=0; j<1000;j++) // TODO: Cambia a 3000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();
            /*
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
            */
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
} // Fin Class HiloSumador


public class BloquesNoSincronizados {
    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: Bloques No Sincronizados");
        System.out.println("-------------------------------");

        Contador cont = new Contador(0);
        HiloSumador hiloSuma1 = new HiloSumador("Hilo Sumador 1", cont);
        HiloSumador hiloSuma2 = new HiloSumador("Hilo Sumador 2", cont);
        HiloSumador hiloSuma3 = new HiloSumador("Hilo Sumador 3", cont);
        HiloSumador hiloSuma4 = new HiloSumador("Hilo Sumador 4", cont);
        HiloSumador hiloSuma5 = new HiloSumador("Hilo Sumador 5", cont);
        

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        hiloSuma1.start();
        hiloSuma2.start();
        hiloSuma3.start();
        hiloSuma4.start();
        hiloSuma5.start();

        try {
            hiloSuma1.join();
            hiloSuma2.join();
            hiloSuma3.join();
            hiloSuma4.join();
            hiloSuma5.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}

// Cada vez que se ejecuta sale un resultado diferente. No sale el resultado esperado