/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej3extra;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */

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

public class Ej3extra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ExecutionTimer timer = new ExecutionTimer();
        File f;
        FileReader fr = null;//Necesario para trabajar con el fichero

        timer.start();
        
        for (int i = 0; i < args.length; i++)//El programa va abriendo cada archivo con un for
        {
        System.out.println(args[i]);
        f = new File(args[i]);
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

        timer.stop();//Detenemos el contador de tiempo y mostramos el resultado

        System.out.println(
                "\n");
        timer.printElapsedTime();
    }
}

/*
    La comparación entre el tiempo de ambos está en el programa con hilos

 */
