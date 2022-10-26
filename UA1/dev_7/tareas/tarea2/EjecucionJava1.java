import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 *
 * @author david
 */
public class EjecucionJava1 {
    public static void main(String[] args) {
        try {
            //Construye un proceso de tipo java y nombre asignado
            ProcessBuilder pb = new ProcessBuilder("java", "ProcesosJava1.java");
            //Redirije los problemas para que no los muestre
            pb.redirectErrorStream(true);
            //Establece el origen y el destino de la E/S estandar del subproceso para que sean los mismos que los del proceso Java actual.
            pb.inheritIO();
            //Crea un proceso y lo inicia
            Process p = pb.start();
            //Espera hasta que termine el proceso
            p.waitFor();     
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
