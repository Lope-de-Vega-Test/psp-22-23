/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ej3;

/**
 *
 * @author Ignacio
 */
public class argumentos {
    public static void main(String[] args) {
        
        if(args.length<1){
            System.exit(1); //System devuelve 1 si no hay argumentos
        }else{
            for(int i=0;i<args.length;i++){
                if(((Object)args[i]).getClass().getSimpleName().equals("String")){ 
                    System.exit(2);//Devuelve 2 si un argumento es un String
                }else if(((Object)args[i]).getClass().getSimpleName().equals("Integer") && (Integer.parseInt(args[i]))<0){
                    System.exit(3);//Devuelve 3 si un argumento es Integer y además menor a 0
                }
            }
            System.exit(0); //Si no se ha cumplido nada de lo anterior devuelve 0
        }
        
        
    }
}
