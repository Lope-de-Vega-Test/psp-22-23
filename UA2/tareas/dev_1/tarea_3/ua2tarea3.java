
package ua2tarea3;

import java.io.FileNotFoundException;
import java.io.FileReader;



/**
 *
 * @author Irene Alba
 */

//clase con la que leer el fichero que pasamos cuando usamos hilos
class Reader extends Thread{
    
    private String fichero;
    
    //constructor para guardar direccion y nombre del fichero
    public Reader(String fichero){
        this.fichero = fichero;
        setName(fichero);
    }
    
    //función que se ejecuta al iniciar el hilo
    public void run() {

        int numCaracts = 0;

        FileReader fr = null;

        try {
            //abrimos el fichero para leerlo
            fr = new FileReader(fichero);

            int caract = fr.read();

            //mientras haya caracteres dentro del fichero se sumará 1 al contador
            while (caract != -1) {
                numCaracts++;
                caract = fr.read();
            }

            System.out.println("Fichero: " + getName() + " - Cantidad de carácteres: " + numCaracts);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }

    }
}

//clase para cronometrar las acciones
class ExecutionTimer{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
    
    //momento en el que inicia
    public void start(){
        startTime = System.nanoTime();
    }
    
    //mmomento en el que para
    public void stop(){
        endTime = System.nanoTime();
    }
    
    //tiempo tardado
    public long elapsedTime(){
        return timeElapsed = endTime - startTime;
    }
    
}

//clase principal
public class ua2tarea3 {        
    
    public static void main(String[] args) {  
        
        //creamos los objetos Timer de ambas acciones
        ExecutionTimer timerHilos = new ExecutionTimer();
        ExecutionTimer timerSinHilos = new ExecutionTimer();
        int nArgs = args.length;
        
        //primero realizamos la acción mediante hilos
        System.out.println("Ejecución hilos...");
        System.out.println("----------------------");
        timerHilos.start();
        
        //para cada argumento se creará un nuevo hilo, se iniciará y se unirá
        for(int i = 0; i < nArgs; i++){
            
            Reader hilo = new Reader(args[i]);                       
                        
            hilo.start();
            
            try{
                hilo.join();
            }catch(InterruptedException e){
                //Nothing...                                        
            }   
        }
        timerHilos.stop();
        
        System.out.println("----------------------");
        System.out.println("Fin ejecución hilos...");
        System.out.println("----------------------");
        System.out.println("");
        
        //después sin hilos
        System.out.println("Ejecución sin hilos...");
        System.out.println("----------------------");
        
        timerSinHilos.start();
        //por cada argumento se llamará a la función reader
        for(int i = 0; i < nArgs; i++){
            reader(args[i]);
        }
        timerSinHilos.stop();
        
        System.out.println("--------------------------");
        System.out.println("Fin ejecución sin hilos...");
        System.out.println("--------------------------");
        
        //al cronometrar ambas acciones podemos observar como cuando usamos la acción sin hilos, mediante la función únicamente, tarda menos que si lo hacemos mediante hilos
        System.out.println("Ejecución con hilos ha tardado: " + (timerHilos.elapsedTime() / 1000000) + " milisegundos --- Ejecución sin hilos ha tardado: " + (timerSinHilos.elapsedTime() / 1000000) + " milisegundos.");
        
    }
    
    //función para leer los ficheros sin usar hilos,.tiene la misma funcionalidad y es igual que la clase reader
    public static void reader(String fichero) {

        int numCaracts = 0;

        FileReader fr = null;

        try {
            fr = new FileReader(fichero);

            int caract = fr.read();

            while (caract != -1) {
                numCaracts++;
                caract = fr.read();
            }

            System.out.println("Fichero: " + fichero + " - Cantidad de carácteres: " + numCaracts);

        } catch (FileNotFoundException e) {
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error en la lectura del fichero");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }

    }
    
}

