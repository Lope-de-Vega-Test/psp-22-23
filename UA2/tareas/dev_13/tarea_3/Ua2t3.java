/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


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

public class Ua2t3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          ExecutionTimer timer = new ExecutionTimer();
          timer.start();
        try {
            FileReader fr1 = new FileReader(args[0]);
            FileReader fr2 = new FileReader(args[1]);
            FileReader fr3 = new FileReader(args[2]);
            
            int totalLineas=0;
            
            while(fr1.read()!=-1){
            totalLineas++;   
            }
            while(fr2.read()!=-1){
            totalLineas++;   
            }
            while(fr3.read()!=-1){
            totalLineas++;   
            }
            
            System.out.println("Total de lineas: "+totalLineas);
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Ua2t3.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ua2t3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        timer.stop();
        timer.printElapsedTime();
    }
    
}
