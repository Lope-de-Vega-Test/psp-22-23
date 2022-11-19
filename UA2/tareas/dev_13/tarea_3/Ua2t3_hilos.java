/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class Lector extends Thread{
    String[] args;
    int totalLineas;
    public void run(){
        
        try{
            FileReader fr = new FileReader(args[0]);
            
            while(fr.read()!=-1){
            totalLineas++;   
            }
            
            System.out.println("El total de caracteres es: "+totalLineas);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Lector(String[] args) {
        this.args = args;
    }
}

class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()    
    {
        elapsedTime();
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}

public class Ua2t3_hilos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutionTimer timer = new ExecutionTimer();
        timer.start();
        
        ArrayList<Lector> lectores = new ArrayList();
        
        for(int i=0; i<3; i++){
            Lector lector = new Lector(args);
            lectores.add(lector);
        }
          
        for(int i=0;i<lectores.size();i++){
                lectores.get(i).start();
            
        }
          
        for(int i=0;i<lectores.size();i++){
            try {
                lectores.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ua2t3_hilos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        timer.stop();
        timer.printElapsedTime();
    }
    
}
