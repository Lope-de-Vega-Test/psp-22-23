package tarea_3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Lucía Luna
 */

public class Tarea_3 {

    public static void main(String[] args) {
        System.out.println();
        // Creamos una lista
        ArrayList<String> file_path = new ArrayList<String>(); 
        // Agregamos a la lista los N 
        Collections.addAll(file_path, args); 
        
        if (file_path.size() > 0) {
            ArrayList<File> file = new ArrayList<File>();

            // Se realiza un for para cada uno de los argumentos
            for(int i = 0; i < file_path.size(); i++) {
                File f = new File(file_path.get(i));
                if (f.exists()) {
                    file.add(f);
                } else {
                    System.out.println(file_path.get(i) + " no existe.");
                }
            }

            // Se realiza un for para cada uno de los ficheros
            for(int i = 0; i < file.size(); i++) {
                long t_comienzo, t_fin;
                
                Hilo h = new Hilo(file.get(i)); //se crea el hilo
                
                t_comienzo =  System.currentTimeMillis(); // Nos indica el tiempo que tarda antes de ejecutar el hilo
                
                h.start(); // se encarga de lanzar el hilo
                
                //Une el proceso del hilo al proceso principal
                try{h.join();} catch (Exception e) {}
                
                t_fin = System.currentTimeMillis(); // Nos muestra el tiempo que ha tardado en ejecutarse el hilo
                
                long t_total = t_fin - t_comienzo;
                System.out.println("\t- Tiempo transcurrido: " + t_total + " ms");
            }
          } else {
            System.out.println("No se introdujeron parametros.");
        }

    }
    
    public static void countCharacters(File f) {
        try {
            FileReader fr = new FileReader(f); // Se usa para crear una copia en la memoria 
            BufferedReader br = new BufferedReader(fr); // Almacena el contenido del fichero en la memoria
            int char_count = 0; // variable contador 
            // Mientras que lea caracteres y no el final del archivo [...]
            while (br.read() != -1) {
                char_count++; // incrementa el contador en 1
            }
            System.out.println("El fichero '" + f.getName() + "' tiene " + char_count + " caracteres.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


 //Comienza la clase Hilo

class Hilo extends Thread {

    File f;

    public Hilo(File f) {
        this.f = f;
    }

    public void run() {
        try {
            // Se usa para crear una copia en la memoria 
            FileReader fr = new FileReader(f); 
            // Almacena el contenido del fichero en la memoria
            BufferedReader br = new BufferedReader(fr); 
            // variable contador 
            int char_count = 0;
            // mientras que lea caracteres y no el final del archivo 
            while (br.read() != -1) {
                char_count++; // Se incrementa en uno el contador
            }
            System.out.println("El fichero '" + f.getName() + "' tiene " + char_count + " caracteres.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Termina la clase hilo
