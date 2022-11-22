/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej3hilos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
class HiloLector extends Thread {

    String path; //En principio lo único necesario es su correspondiente path del documento

    public String getPath() {
        return path;
    }

    public HiloLector(String path) {
        this.path = path;
    }

    public void run() {
        File f;
        FileReader fr = null;//Necesario para trabajar con el fichero

        System.out.println(this.getPath());
        f = new File(this.path);
        if (f.exists()) {//Comprueba que el path existe
            System.out.print("EL path EXISTE");

            if (f.canRead()) {
                try {
                    int caract;
                    int c = 0;
                    fr = new FileReader(f);
                    caract = fr.read();
                    while (caract != -1) {
                        
                        caract = fr.read();
                        c++;//Tan simple como que cada caracter aumenta el contador, que luego mostramos
                    }
                    System.out.println("\n\tNúmero de caracteres fichero: " + c);
                } catch (Exception e) {
                    //Operaciones en caso de error general
                    System.out.println("Error de lectura del fichero");
                    System.out.println(e.getMessage());
                } finally {
                    //Operaciones que se harán en cualquier caso. Si hay error o no.
                    try {
                        //Cerrar el fichero si se ha abierto
                        if (fr != null) {
                            fr.close();
                        }
                    } catch (Exception e) {
                        System.out.println("Error al cerrar el fichero");
                        System.out.println(e.getMessage());
                    }
                }

            } else {
                System.out.println(" PERO NO SE PUEDE LEER");
            }//Imprime esto si no se puede leer
        }
    }
}

class ExecutionTimer {//Esta clase la usaré para medir el tiempo del proceso

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

public class Ej3Hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ExecutionTimer timer = new ExecutionTimer();

        ArrayList<Thread> myThreads = new ArrayList<Thread>(); //Array de hilos

        for (int i = 0; i < args.length; i++) //For de creación de hilos y adición de hilos al array
        {
            HiloLector hilo = new HiloLector(args[i]);//Es en su declaración que cada hilo recibe su path
            myThreads.add(new Thread(hilo));
        }

        timer.start();
        for (int j = 0; j < myThreads.size(); j++) //El start se implementa con un for
        {
            myThreads.get(j).start();
        }

        for (int j = 0; j < myThreads.size(); j++) //Cada join se hace en un try-catch
        {                                       // vía for
            try {
                myThreads.get(j).join();
            } catch (Exception e) {
                // Nothing to do here ...
            }
        }

        timer.stop();//Detenemos el contador de tiempo y mostramos el resultado

        System.out.println(
                "\n");
        timer.printElapsedTime();
    }
}

/*
    Primera ejecución:
    
    C:\Users\Ignacio\Desktop\el_quijote.txt
    EL path EXISTE
            Número de caracteres fichero: 1038397
    C:\Users\Ignacio\Desktop\lorem.txt
    EL path EXISTE
            Número de caracteres fichero: 1481344


    Execution time in nanoseconds: 389869400
    Execution time in milliseconds: 389

    Segunda ejecución:

    C:\Users\Ignacio\Desktop\el_quijote.txt
    C:\Users\Ignacio\Desktop\lorem.txt
    EL path EXISTEEL path EXISTE
            Número de caracteres fichero: 1481344

            Número de caracteres fichero: 1038397


    Execution time in nanoseconds: 362346000
    Execution time in milliseconds: 362

Aparte de lo llamativo de que se han mezclado los sout de ambos hilos, me sorprende
que no haya una diferencia tan notable como esperaba, en general al ejecutar ambos
si, bien en la mayoría de los casos los hilos eran más rápidos que el proceso único,
en ocasiones el proceso era más rápido y en general no se aprecia una diferencia
sustancial, tal vez debido a haber usado dos documentos en vez de un gran número
de ellos, en cuya caso podría haberse observado una diferencia mayor

 */
