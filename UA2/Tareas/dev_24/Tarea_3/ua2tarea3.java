import java.io.FileNotFoundException;
import java.io.FileReader;

public class ua2tarea3 {         
    public static void main(String[] args) {  
        
       
        ExecutionTimer timerHilos = new ExecutionTimer();
        ExecutionTimer timerSinHilos = new ExecutionTimer();
        int nArgs = args.length;
        
        
        System.out.println("Se ejecutan los hilos");
        System.out.println("");
        timerHilos.start();

        for(int i = 0; i < nArgs; i++){ 
            Reader hilo = new Reader(args[i]);                                 
            hilo.start();
            
            
            try{
                hilo.join();
            }catch(InterruptedException e){
                                                       
            }   
        }
       
        timerHilos.stop();
        
        System.out.println("");
        System.out.println("Se han terminado de ejecutar los hilos");
        System.out.println("");
        
        
        System.out.println("Ahora vamos a mostrar la ejecucion sin hilos");
        
        timerSinHilos.start();
       
        for(int i = 0; i < nArgs; i++){
            readerSinHilos(args[i]);
        }
        timerSinHilos.stop();

        System.out.println("Fin ejecución sin hilos");
       
        
        
        System.out.println("Ejecución con hilos ha tardado: " + (timerHilos.elapsedTime() / 1000000) + " milisegundos --- Ejecución sin hilos ha tardado: " + (timerSinHilos.elapsedTime() / 1000000) + " milisegundos.");
        
    }
class Reader extends Thread{
    
    private String ficheroReader;

    public Reader(String fichero){
        this.ficheroReader = fichero;
        setName(fichero);
    }
    
    public void run() {

        int nCar = 0;

        FileReader fr = null;

        try { 
            
            fr = new FileReader(ficheroReader);

            int caracter = fr.read();

            while (caracter != -1) { 
                nCar++;
                caracter = fr.read();
            }

            System.out.println("El fichero: " + getName() + " tiene un total de " + nCar + " caracteres");

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

    class ExecutionTimer{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
    
    
    public void start(){
        startTime = System.nanoTime();
    }
    
   
    public void stop(){
        endTime = System.nanoTime();
    }
    
    
    public long elapsedTime(){
        return timeElapsed = endTime - startTime;
    }
    
}
    
    public static void readerSinHilos(String fichero) {

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
