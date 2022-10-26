import java.util.Arrays;
/**
 *
 * @author david
 */
public class EjecucionJava2 {
    public static void main(String[] args) {

        try{
            //Construye un proceso de tipo java y nombre asignado y crea un array tipo string de los argumentos
            ProcessBuilder pb = new ProcessBuilder("java", "ProcesosJava2.java", Arrays.toString(args));
            //Crea un proceso y lo inicia
            Process p = pb.start();  
            //Espera a que se termine el proceso         
            p.waitFor();
            //Imprime por pantalla el valor de salida del proceso
            System.out.println(p.exitValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
