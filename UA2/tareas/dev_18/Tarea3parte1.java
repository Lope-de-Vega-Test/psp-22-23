package paquete;

import java.io.FileReader;

class ExecutionTimer {

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

    public void printElapsedTime() {

        elapsedTime();
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);

    }

}

class crearhilo extends Thread {

    String[] args;
    int numero = 0;

    public crearhilo(String[] args, int numero) {
        this.args = args;
        this.numero = numero;

    }

    public void run() {
        int linea = 0;
        try {
            FileReader file = new FileReader(args[numero]);
            while (file.read() != -1) {
                linea++;
            }
        } catch (Exception e) {

        }
        System.out.println("Numero de lineas: " + linea);

    }
}

public class ua2tarea3fr2 {

    public static void main(String[] args) {

        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        crearhilo primerhilo = new crearhilo(args, 0);
        crearhilo segundohilo = new crearhilo(args, 1);

        System.out.println("Los hilos son inicializados");
        primerhilo.start();
        segundohilo.start();

        try {
            primerhilo.join();
            segundohilo.join();

        } catch (Exception e) {

        }
        System.out.println("Fin de los hilos");
        timer.stop();
        timer.printElapsedTime();
    }

}
