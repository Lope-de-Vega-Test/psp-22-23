/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea2Elena;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class tarea2 {
    
    public static void main(String[] args) throws IOException {
        Scanner sca =new Scanner(System.in);
        System.out.println("INTRODUCE LOS CARACTERES:");
        
         String cadena="";
         String linea="";
         cadena=sca.nextLine();
        
         while(!cadena.equals("*")){
            linea+=cadena;
            cadena=sca.nextLine();
         }
         
         System.out.println("LA CADENA ES: "+linea);
}
}
