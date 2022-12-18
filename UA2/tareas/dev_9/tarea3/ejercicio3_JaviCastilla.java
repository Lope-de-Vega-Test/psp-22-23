import java.io.FileNotFoundException;
import java.io.FileReader;


//clase con la que leer el fichero que pasamos cuando usamos hilos
class Hilos extends Thread{
    
    private String fichero;
    
    //constructor para guardar direccion y nombre del fichero
    public Hilos(String fichero){
        this.fichero = fichero;
        setName(fichero);
    }
    
    //función que se ejecuta al iniciar el hilo
    public void run() {

        int numCaracts = 0;

        FileReader lectf = null;

        try {
            //Introducimo el fichero a leer
            lectf = new FileReader(fichero);

            int caract = lectf.read();

            //Mientras que dentro del fichero haya caracteres, el contador se ira sumando, cuando ya no haya mas caracteres se cerrará.
            while (caract != -1) {
                numCaracts++;
                caract = lectf.read();
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
                if (lectf != null) {
                    lectf.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }

    }
}

//La clase tiempo la usaremos para cronometrar el tiempo de ejecucion del programa con hilos y sin hilos.
class Tiempo{
    private long inicio = 0;
    private long fin = 0;
    private long tiempoConcurrido=0;
    
    //Se inicia
    public void start(){
        inicio = System.nanoTime();
    }
    
    //Se para
    public void stop(){
        fin = System.nanoTime();
    }
    
    //Tiempo que ha tardado
    public long concurrido(){
        return tiempoConcurrido = fin - inicio;
    }
    
}

//clase principal
public class ejercicio3_JaviCastilla {        
    
    public static void main(String[] args) {  
        
        //Creamos las variables del tiempo para ambos casos
        Tiempo cronoHilos = new Tiempo();
        Tiempo cronoSinHilos = new Tiempo();
        int nArgs = args.length;
        
        //Comenzaremos el programa con la ejecución sin los hilos
        System.out.println("Se ejecutara el programa sin hilos.");
        System.out.println("----------------------");
        
        cronoSinHilos.start();
        for(int i = 0; i < nArgs; i++){
            lecturaFichero(args[i]);
        }
        cronoSinHilos.stop();
        
        System.out.println("--------------------------");
        System.out.println("Fin de la ejecución sin hilos...");
        System.out.println("--------------------------");

        //Ahora ejecutaremos el programa con los hilos
        System.out.println("Se ejecutarán los hilos.");
        cronoHilos.start();
        
        for(int i = 0; i < nArgs; i++){
            
            Hilos hilo = new Hilos(args[i]);                       
                        
            hilo.start();
            
            try{
                hilo.join();
            }catch(InterruptedException e){
                                                  
            }   
        }
        cronoHilos.stop();
        
        System.out.println("----------------------");
        System.out.println("Fin de la ejecución");
        System.out.println("----------------------");
        
        
       
        
       //Ahora mostramos el resultado de los tiempos de ejecución de ambos casos, y como se podrá comprobar los hilos tardan menos en ejecutarse que el programa en si.
        System.out.println("La ejecución con hilos ha tardado: " + (cronoHilos.concurrido() / 1000000) + " milisegundos, mientras que la ejecución sin hilos ha tardado: " + (cronoSinHilos.concurrido() / 1000000) + " milisegundos.");
        
    }
    //Esta función la usaremos para que el la ejecución sin hilos pueda leer el fichero
    public static void lecturaFichero(String fichero) {

        int numCaracts = 0;

        FileReader lectf = null;

        try {
            lectf = new FileReader(fichero);

            int caract = lectf.read();

            while (caract != -1) {
                numCaracts++;
                caract = lectf.read();
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
                if (lectf != null) {
                    lectf.close();
                }
            } catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }

    }
    
}
