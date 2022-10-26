
package primero;
import java.util.Scanner;


public class Primero {


    public static void main(String[] args) {
        
        System.out.println(""+args.length);
        
        for (int i=0;i<args.length;i++) {
            if (args[i].equals("-v")){
                System.out.println("0.0v");
            }
            else if (args[i].equals("--loop")){
                 int variable = Integer.parseInt(args[i+1]);
               for (int e=0;e<variable;e++){
                 
                    System.out.println("accion numero"+(e+1));      
        
    }
               
        }
        
           
        

    }
    
}
