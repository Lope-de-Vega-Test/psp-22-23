package UA2.tareas.dev_8.tarea_3;

import java.io.FileReader;

class Reader extends Thread {
    private String fichero;

    public Reader(String fichero) {
        this.fichero = fichero;
        setName(fichero);
    }

    public void run() {
        int caracteres = 0;

        FileReader fr = null;

        try {
            fr = new FileReader(fichero);
            int caracter = fr.read();

            while (caracter != -1) {
                caracteres++;
                caracter = fr.read();
            }
        }  catch (Exception e) {
            System.err.println("Error de lectura " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("Caracteres de texto introducidos" + caracteres);

                }
            } catch (Exception e) {
                System.err.println("Cerramos el fichero" + e.getMessage());
            }
        }
    }
}

// La clase ExecutionTimer donde se alojará los tiempos en los que el programa tardará en recorrer usando hilos y sin hilos.

class ExecutionTimer {
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed;

    public void start() {
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public long elapsedTime() {
        return timeElapsed = endTime - startTime;
    }
}

public class UA2tarea3 {
    public static void main(String[] args) {

        ExecutionTimer timerH = new ExecutionTimer();
        ExecutionTimer timerSinH = new ExecutionTimer();
        int numeroArgumentos = args.length;

        // Usamos los hilos de la clase Reader.

        System.out.println("--------------------");
        System.out.println("Ejecutamos los hilos");
        timerH.start();

        for (int i = 0; i < numeroArgumentos; i++) {
            Reader hilo = new Reader(args[i]);

            hilo.start();

            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }

        }
        timerH.stop();

        // Sin la ejecucion de hilos.

        System.out.println("--------------------");
        System.out.println("Ejecutamos sin hilos");
        for (int i = 0; i < numeroArgumentos; i++) {
            reader(args[i]);
        }
        timerSinH.stop();

        System.out.println("El tiempo transcurrido con hilos es: " + (timerH.elapsedTime() / 1000000)
                + ", y sin hilos: " + timerSinH.elapsedTime() / 1000000);
    }

    // Sin usar los hilos.

    public static void reader(String fichero) {
        int caracteres = 0;

        FileReader fr = null;

        try {
            fr = new FileReader(fichero);
            int caracter = fr.read();

            while (caracter != -1) {
                caracteres++;
                caracter = fr.read();
            }
        } catch (Exception e) {
            System.err.println("Error de lectura" + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                    System.out.println("Caracteres de texto introducidos" + caracteres);

                }
            } catch (Exception e) {
                System.err.println("Cerramos el fichero" + e.getMessage());
            }
        }
    }
}