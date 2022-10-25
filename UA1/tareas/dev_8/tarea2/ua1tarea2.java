package dev_8;

import java.util.Scanner;

public class ua1tarea2 {


    public static void main(String[] args) {

        // iniciamos las variables
        Scanner sc = new Scanner(System.in);
        String allInputs = "";
        String text = "";
        
        // hacemos que mientas que no contenta * no pare de pedirnos entradas
        while (!text.contains("*")) {
            System.out.println("Introduce un texto: ");
            text = sc.nextLine();

            // concatenamos la entrada
            allInputs += " " + text;

        }
        // quitamos el * del final
        allInputs = allInputs.substring(0, allInputs.length() -1);
        // mostramos la cadena total
        System.out.println(allInputs);

        
        
    }
    
}
