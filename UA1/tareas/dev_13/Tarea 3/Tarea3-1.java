/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tarea3.pkg1;

/**
 *
 * @author Javier
 */
public class Tarea31 {

    
    public static void main(String[] args) {
        	//si no hay argumentos devolverá 1
                if(args.length<1){
			System.exit(1);
                }else{
                    try{
                        //intentará convertir el argumento a string. Si lo consigue devolverá 0
                        int argumentoInt = Integer.parseInt(args[0]);
                        if(argumentoInt < 0){
                            //si el numero es menor que 0 devolverá 3
                            System.exit(3);
                        }else System.exit(0);
                    }catch(Exception e) {
                                //en caso de error al convertir el argumento a entero (es decir, es una cadena) devolverá 2
                                System.exit(2);
                }
	}
    }
}
    

