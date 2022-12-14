package tarea2;

import java.io.IOException;
import java.util.Scanner;

public class tarea2 {
    public static void main(String[] args) throws IOException{
        Scanner s = new Scanner(System.in);
        int valor ;
        valor = 1;
        
        String cadena[] = new String[100];
        for(int i=0;i<valor;i++){
            System.out.println( "introduce un numero o una cadena: ");

            cadena[i]=s.nextLine();
            if (!cadena[i].contains("*"))
            {
                valor++;
            }
            if(cadena[i].contains("*")){
                i=valor;
            }
    }
                
                System.out.println("Resultado: ");
        for(int j=0;j<valor;j++){

            if (!cadena[j].contains("*") )
            {
                System.out.println(cadena[j]);
            }
        }
    }
}
