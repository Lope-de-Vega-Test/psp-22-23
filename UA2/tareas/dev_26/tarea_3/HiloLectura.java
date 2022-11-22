import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;

public class HiloLectura extends Thread{

    FileReader fr = null;
    
    private int orden;
    private int fileSize;
    private String nombreFichero;
    
    public HiloLectura(String nombreFichero, int orden) {
        this.orden = orden;
        this.nombreFichero = nombreFichero;
        this.fileSize = 0;
    }

    public void setFileSize(int fileSize){
        this.fileSize = fileSize;
    }

    public int getFileSize(){
        return fileSize;
    }

    public synchronized void mostrarDatosHilo(int orden, int fileSize, long tiempotranscurrido){
        System.out.println("Hilo numero "+ orden + " | Tamaño del archivo: " + fileSize + " | Tiempo transcurrido: ("+ tiempotranscurrido +"ns | "+(tiempotranscurrido / 1000000)+"ms)");
    }

    @Override
    public void run() {
        int contador = 0;
        ExecutionTimer timer = new ExecutionTimer();
        try{
            //Abrir el fichero indicado en la variable nombreFichero
            fr = new FileReader(nombreFichero);
            int caract = fr.read();
            //Se recorre el fichero hasta encontrar el carácter -1
            while(caract != -1) {
                //System.out.print((char)caract);
                //Leer el siguiente carácter
                caract = fr.read();
                contador++;
            }
            setFileSize(contador);
        }catch (FileNotFoundException e) {
            //Operaciones en caso de no encontrar el fichero
            System.out.println("Error: Fichero no encontrado");
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            //Operaciones en caso de error general
            System.out.println("Error de lectura del fichero");
            System.out.println(e.getMessage());
        }
        finally {
            //Operaciones que se harán en cualquier caso. Si hay error o no.
            try {
                if(fr != null)
                    fr.close();
            }
            catch (Exception e) {
                System.out.println("Error al cerrar el fichero");
                System.out.println(e.getMessage());
            }
            timer.stop();
            long tiempotranscurrido = timer.getelapsedTime();
            mostrarDatosHilo(orden, fileSize, tiempotranscurrido);
        }
    }
}

