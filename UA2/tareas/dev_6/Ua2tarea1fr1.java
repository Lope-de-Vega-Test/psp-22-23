public class Ua2tarea1fr1 {

    public static void main(String[] args) {
        System.out.println("-------------------------------");
        System.out.println("Hilos: Bloques NO Sincronizados");
        System.out.println("-------------------------------");
        

        Contador cont = new Contador(100);
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

