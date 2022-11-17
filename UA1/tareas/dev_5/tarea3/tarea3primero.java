/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package tarea3;

import java.io.InputStream;

public class Tarea3primero {

    public static void main(String[] args){ 
   
         if(args.length<1){
             System.exit(1);
         }
         else{
             try{
                 int numero=0;
             numero=Integer.parseInt(args[0]);
             if(numero<0){
                 System.exit(3);}
             else{
             System.exit(0);}
             }catch(Exception e){
                 System.exit(2);
             }
}
    }
}
