
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class ua2tarea3 {

    public static void main(String[] args) {
        System.out.println();
        ArrayList<String> file_path = new ArrayList<String>(); // Creacion de lista
        Collections.addAll(file_path, args); // Agregar a la lista los N 
        if (file_path.size() > 0) {
            ArrayList<File> file = new ArrayList<File>();

            // Por cada argumento...
            for(int i = 0; i < file_path.size(); i++) {
                File f = new File(file_path.get(i));
                if (f.exists()) {
                    file.add(f);
                } else {
                    System.out.println(file_path.get(i) + " no existe.");
                }
            }

            // Por cada fichero [...]
            for(int i = 0; i < file.size(); i++) {
                long t_comienzo, t_fin;
                
                Hilo h = new Hilo(file.get(i)); ///[...] crear un hilo [...]
                
                t_comienzo =  System.currentTimeMillis(); // Capturar tiempo del sistema antes de ejecutar el hilo
                
                h.start(); // [...] y lanzarlo
                
                // Adjuntar el proceso del hilo al proceso principal
                try{h.join();} catch (Exception e) {}
                
                t_fin = System.currentTimeMillis(); // Capturar tiempo del sistema despues de finalizar el hilo
                
                long t_total = t_fin - t_comienzo;
                System.out.println("\t- Tiempo transcurrido: " + t_total + " ms");
            }
            
            // Comparacion secuencial
            /*
            System.out.println("\nSecuencial");
            long t_comienzo, t_fin;
            t_comienzo =  System.currentTimeMillis(); // Capturar tiempo del sistema antes de ejecutar el hilo
            countCharacters(file.get(0));
            t_fin = System.currentTimeMillis(); // Capturar tiempo del sistema despues de finalizar el hilo
            long t_total = t_fin - t_comienzo;
            System.out.println("\t- Tiempo transcurrido: " + t_total + " ms");
            
            
            // La unica diferencia que he notado es que
            // la ejecucion secuencial tarda aproximadamente
            // la mitad de tiempo que en la concurrente.
            */
            
            
        } else {
            System.out.println("No se introdujeron parametros.");
        }

    }
    
    public static void countCharacters(File f) {
        try {
            FileReader fr = new FileReader(f); // Crear una copia del archivo en memoria
            BufferedReader br = new BufferedReader(fr); // Guardar en memoria el contenido del fichero
            int char_count = 0; // Variable contador de caracteres
            // Mientras que lea caracteres y no el final del archivo [...]
            while (br.read() != -1) {
                char_count++; // [...] el contador se incrementa en uno
            }
            System.out.println("El fichero '" + f.getName() + "' tiene " + char_count + " caracteres.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Hilo extends Thread {

    File f;

    public Hilo(File f) {
        this.f = f;
    }

    public void run() {
        try {
            FileReader fr = new FileReader(f); // Crear una copia del archivo en memoria
            BufferedReader br = new BufferedReader(fr); // Guardar en memoria el contenido del fichero
            int char_count = 0; // Variable contador de caracteres
            // Mientras que lea caracteres y no el final del archivo [...]
            while (br.read() != -1) {
                char_count++; // [...] el contador se incrementa en uno
            }
            System.out.println("El fichero '" + f.getName() + "' tiene " + char_count + " caracteres.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
