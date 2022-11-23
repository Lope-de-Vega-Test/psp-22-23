import java.io.FileNotFoundException;
import java.io.FileReader;
//clase para leer el fichero que vamos a introducir
class Lectura extends Thread {
    String fichero;
    //constructor
    public Lectura(String fichero){
        this.fichero=fichero;
        setName(fichero);
    }
    //run de cada hilo
    public void run(){
        int contador = 0;
        //Variable FileReader
        FileReader fr = null;
        try {
            //Abrir el fichero indicado en la variable fichero
            fr = new FileReader(fichero);
            //Leer el primer carácter
            int caract = fr.read();
            //Se recorre el fichero hasta encontrar el carácter -1
            while(caract != -1) {
                //añadimos 1 al contador por cada caracter leido
                contador++;
                //Leer el siguiente carácter
                caract = fr.read();
            }
        }
        catch (FileNotFoundException e) {
            //Operaciones en caso de no encontrar el fichero
            System.out.println("Error: Fichero no encontrado");
            //Mostrar el error producido por la excepción
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            //Operaciones en caso de error general
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            //mostramos el contador de caracteres
            System.out.println("\nNumero de caracteres: "+contador);
            //Operaciones que se harán en cualquier caso. Si hay error o no.
            try {
                //Cerrar el fichero si se ha abierto
                   if(fr != null)
                    fr.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
    }

    
}


// Esta clase almacena el tiempo de ejecucion de cada hilo
class Timer {
    private long inicio = 0;
    private long fin = 0;
    private long diferencia;

    public void start() {
        inicio = System.currentTimeMillis();
    }

    public void stop() {
        fin = System.currentTimeMillis();
    }
    //tiempo total de ejecucion
    public long elapsedTime() {
        return diferencia = fin - inicio;
    }
}

public class ua2tarea3{
    public static void main(String[] args) {
        //creamos los timers para ver el tiempo que tardan en ejecutarse
        Timer timer1 = new Timer();
        Timer timer2 = new Timer();
        //guardmos el numero de argumentos introducidos por consola
        int numFicheros = args.length;
        //iniciamos el contamods del hilo1
        timer1.start();
        for(int i=0;i<numFicheros;i++){
            //creamos el hilo
            Lectura hilo1 = new Lectura(args[i]);
            try{
                hilo1.join();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        //paramos contados hilo
        timer1.stop();
        //iniciamos contador sin hilo
        timer2.start();
        for(int i=0;i<numFicheros;i++){
            Lectura2(args[i]);
        }
        //paramos contados hilo
        timer2.stop();
        //mostramos resultados
        System.out.println("Tiempo del hilo: " + timer1.elapsedTime() +" milisegundos.\n" + "Tiempo sin hilos: " + timer2.elapsedTime()+" milisegundos.");

    }
    //clase para leer el fichero sin usar hilos
    public static void Lectura2(String fichero){
        int contador = 0;
        //Variable FileReader
        FileReader fr = null;
        try {
            //Abrir el fichero indicado en la variable fichero
            fr = new FileReader(fichero);
            //Leer el primer carácter
            int caract = fr.read();
            //Se recorre el fichero hasta encontrar el carácter -1
            while(caract != -1) {
                //añadimos 1 al contador por cada caracter leido
                contador++;
                //Leer el siguiente carácter
                caract = fr.read();
            }
        }
        catch (FileNotFoundException e) {
            //Operaciones en caso de no encontrar el fichero
            System.out.println("Error: Fichero no encontrado");
            //Mostrar el error producido por la excepción
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            //Operaciones en caso de error general
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            //mostramos el contador de caracteres
            System.out.println("\nNumero de caracteres: "+contador);
            //Operaciones que se harán en cualquier caso. Si hay error o no.
            try {
                //Cerrar el fichero si se ha abierto
                   if(fr != null)
                    fr.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
        }
    }
}

//podemos observar que la ejecucion con hilo es mas rapida que al ejecucion secuencial, obteniendo un resultado en milisegundos inferior.