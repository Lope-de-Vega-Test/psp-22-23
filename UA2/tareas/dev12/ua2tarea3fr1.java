package paquete;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


class ExecutionTimer {  //medir tiempo de ejecucuin
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

public class ua2tarea3fr1 {

    public static void main(String[] args) {
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        try { // arry
            FileReader file1 = new FileReader(args[0]);
            FileReader file2 = new FileReader(args[1]);

            int contador = 0; //contador de lineas

            while (file1.read() != -1) {
                contador++;
            }
            while (file2.read() != -1) {
                contador++;
            }

            System.out.println("Hay: " + contador+" lineas");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer.stop();
        timer.printElapsedTime();
    }
}