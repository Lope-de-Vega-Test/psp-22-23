
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
/**
 *
 * @author 34617
 */
public class ua2tarea3 {

    public static void main(String args[]) {
        
        long t_comienzo, t_fin, t_total;
        
        
        ArrayList<String> listaArgumentos = new ArrayList<String>(); 
        Collections.addAll(listaArgumentos, args);//Metemos los argumentos en un vector
        ArrayList<File> nombreFichero = new ArrayList<File>();
        if (listaArgumentos.size() > 0) {
            for (int i = 0; i < listaArgumentos.size(); i++) {
                File f = new File(listaArgumentos.get(i));
                if (f.exists()) {
                    nombreFichero.add(f);
                } else {
                    System.out.println(f.getName() + " no existe");
                }
                //Si el fichero existe lo agregamos a un vector de ficheros existentes

            }
            for (int i = 0; i < nombreFichero.size(); i++) {
                Hilo h = new Hilo(nombreFichero.get(i));
                t_comienzo = System.currentTimeMillis();
          
                h.start();
                try{
                h.join();
                }catch(Exception e){}
                t_fin = System.currentTimeMillis();
                
                t_total = t_fin - t_comienzo;
               
                System.out.println("Tiempo tot: "+t_total);
            }
            //Creamos un hilo por cada fichero y calculamos el tiempo
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
            FileReader fr = null;
            BufferedReader br = null;
            int caract = 0;
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            while (br.read() != -1) {
                caract++;
            }
            //Mientras tenga caracteres para leer aumenta el contador
            System.out.println(f.getName()+ " caracteres: "+caract);
        } catch (Exception e) {
            System.out.println("errorr");
        }
    }

}
