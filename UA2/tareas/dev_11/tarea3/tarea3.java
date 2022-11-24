
import java.io.FileNotFoundException;
import java.io.FileReader;


class Reader extends Thread{
    
    private String ficheroReader;

    public Reader(String fichero){ //Constructor con el nombre del fichero
        this.ficheroReader = fichero;
        setName(fichero);
    }
    
    public void run() {

        int nCar = 0;

        FileReader fr = null;

        try { //Intentamos abrir el fichero con un try catch
            
            fr = new FileReader(ficheroReader);

            int caracter = fr.read();

            while (caracter != -1) { //Mientras el programa identifique que hay caracteres se repite
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
    
    //Inicio
    public void start(){
        startTime = System.nanoTime();
    }
    
    //Se para
    public void stop(){
        endTime = System.nanoTime();
    }
    
    //Tiempo que ha tardado
    public long elapsedTime(){
        return timeElapsed = endTime - startTime;
    }
    
}

public class tarea3 {         
    public static void main(String[] args) {  
        
        //Creamos los timers
        ExecutionTimer timerHilos = new ExecutionTimer();
        ExecutionTimer timerSinHilos = new ExecutionTimer();
        int nArgs = args.length;
        
        //primero realizamos la acción mediante hilos
        System.out.println("Se ejecutan los hilos");
        System.out.println("");
        timerHilos.start();

        for(int i = 0; i < nArgs; i++){ //Creamos los hilos y los iniciamos 
            Reader hilo = new Reader(args[i]);                                 
            hilo.start();
            
            //Metemos el respectivo try catch para unirse para evitar errores
            try{
                hilo.join();
            }catch(InterruptedException e){
                //Nothing...                                        
            }   
        }
        //Lo detenemos
        timerHilos.stop();
        
        System.out.println("");
        System.out.println("Se han terminado de ejecutar los hilos");
        System.out.println("------");
        System.out.println("");
        
        //después sin hilos
        System.out.println("Ahora vamos a mostrar la ejecucion sin hilos");
        System.out.println("------");
        
        timerSinHilos.start();
        //por cada argumento se llamará a la función reader
        for(int i = 0; i < nArgs; i++){
            readerSinHilos(args[i]);
        }
        timerSinHilos.stop();

        System.out.println("Fin ejecución sin hilos");
        System.out.println("------");
        
        //Los sincronizamos
        System.out.println("Ejecución con hilos ha tardado: " + (timerHilos.elapsedTime() / 1000000) + " milisegundos --- Ejecución sin hilos ha tardado: " + (timerSinHilos.elapsedTime() / 1000000) + " milisegundos.");
        
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
