package dev_8;

import java.util.Scanner;

public class ua1tarea2 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String allInputs = "";
        String text = "";
        
        while (!text.contains("*")) {
            System.out.println("Introduce un texto: ");
            text = sc.nextLine();

            allInputs += " " + text;

        }
        allInputs = allInputs.substring(0, allInputs.length() -1);
        System.out.println(allInputs);

        
        
    }
    
}
