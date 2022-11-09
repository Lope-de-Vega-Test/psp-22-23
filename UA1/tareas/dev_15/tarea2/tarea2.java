
package paquete1;

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
        System.out.println("introduce caracteres");
        
         String cadena="";
         String linea="";
         cadena=sca.nextLine();
         //lee un caracter diferente de *
         while(!cadena.equals("*")){
            linea+=cadena;
            cadena=sca.nextLine();
         }
         
         System.out.println("La cadena es: "+linea);
}
}