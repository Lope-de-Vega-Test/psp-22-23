import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//MIDE EL TIEMPO QUE TARDA EN EMPEZAR A EJECUTAR EL PROGRAMA
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
        System.out.println("TIEMPO DE EJECUCION EN NANOSEGUNDOS: " + timeElapsed);
        System.out.println("TIEMPO DE EJECUCION EN MILISEGUNDOS: " + timeElapsed / 1000000);
    }
}

public class ua2tarea3fr1 {

    
    public static void main(String[] args) {
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        try {
    
            FileReader fr1 = new FileReader(args[0]);
            FileReader fr2 = new FileReader(args[1]);
            FileReader fr3 = new FileReader(args[2]);

    
            int lineas = 0;

            while (fr1.read() != -1) {
                lineas++;
            }
            while (fr2.read() != -1) {
                lineas++;
            }
            while (fr3.read() != -1) {
                lineas++;
            }

            System.out.println("TOTAL: " + lineas);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer.stop();
        timer.printElapsedTime();
    }
}