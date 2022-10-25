/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Arrays;

/**
 *
 * @author adrian
 */
public class ArgComparator {
    public static void main(String[] args) {
        
        String[] cleanArgs = Arrays.toString(args).replace("[", "").replace("]", "").replace(" ", "").split(",");
        
        int ARGS_COUNT = cleanArgs.length;
        
        if(ARGS_COUNT<2){
            if(cleanArgs[0].isBlank())
                ARGS_COUNT=0;
        }
        
        switch(ARGS_COUNT){
            case 0: {
                //System.out.println("Requires args!");
                System.exit(1);
            }
           
            case 1: 
                try{
                    final int ARG_VALUE = Integer.parseInt(cleanArgs[0]);
                    //System.out.println("Arg type: Integer");
                    if(ARG_VALUE<0)
                        System.exit(3);
                } catch (Exception e) {
                    //System.out.println("Arg type: String");
                    System.exit(2);
                }
            
            default:
                //System.out.println("Too many args or number is greater than 0!");
                System.exit(0);
            
        }
    }
}