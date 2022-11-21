package tarea1guille;

//Ponemos un contador;
class Contador {

    private int c = 0;

    Contador(int c) {
        this.c = c;
    }

    public void suma() {
        c++;
    }

    public void resta() {
        c--;
    }

    public int numero() {
        return c;
    }
}
//Termina el contador

class HiloSumar extends Thread {

    private Contador contador;

    public HiloSumar(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        {
            for (int j = 0; j < 1000; j++) {
                contador.suma();
            }
            System.out.println(getName() + "-contador vale " + contador.numero());
        }
    }
}

public class Tarea1guille {

    public static void main(String[] args) {
        //Confirmamos que los bloques estan sincronizados
        System.out.println("Hilos: \nBloques Sincronizados");

        //Creamos los 5 hilos
        Contador cont = new Contador(0);
        HiloSumar hiloSuma1 = new HiloSumar("Hilo Sumador", cont);
        HiloSumar hiloSuma2 = new HiloSumar("Hilo sumador", cont);
        HiloSumar hiloSuma3 = new HiloSumar("Hilo sumador", cont);
        HiloSumar hiloSuma4 = new HiloSumar("Hilo sumador", cont);
        HiloSumar hiloSuma5 = new HiloSumar("Hilo sumador", cont);
        System.out.println("Comienza la ejecucion de los hilos: ");

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
        } catch (InterruptedException e) {

        }

        System.out.println("Finaliza la ejecucion de los hilos");
        System.out.println("Valor Final del Contador: " + cont.numero());

    }
}
