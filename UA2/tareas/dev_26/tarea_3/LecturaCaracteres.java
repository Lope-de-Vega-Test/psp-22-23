
public class LecturaCaracteres{
    public static void main(String[] args) {

        HiloLectura hilos[] = new HiloLectura[5];
        for(int i = 0; i < hilos.length-1; i++){
            hilos[i] = new HiloLectura(args[i],(i+1));
            hilos[i].start();
        }    
        for(int i = 0; i < hilos.length-1; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}