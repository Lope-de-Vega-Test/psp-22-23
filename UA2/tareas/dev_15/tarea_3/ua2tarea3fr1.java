
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase que mide el tiempo que tarda en ejecutarlo
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

public class ua2tarea3fr1 {

    /**
     * @param args the command line arguments
     */
    //Clase principal
    public static void main(String[] args) {
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        try {
    //Array de argumentos en la clase main 
            FileReader fr1 = new FileReader(args[0]);
            FileReader fr2 = new FileReader(args[1]);
            FileReader fr3 = new FileReader(args[2]);

    //Contador que mostrara el numero de caracteres totales
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

            System.out.println("Total de lineas: " + lineas);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ua2tarea3fr1.class.getName()).log(Level.SEVERE, null, ex);
        }

        timer.stop();
        timer.printElapsedTime();
    }
}