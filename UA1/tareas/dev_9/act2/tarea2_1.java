
import java.util.Scanner;


public class Actividad2PSPEjecutable {
    public static void main(String[] args) {
    Scanner entrada=new Scanner(System.in);
        String letra = "";
        String linea="";
        while(!letra.equals("*")){
        linea+=letra;
        letra=entrada.next();
        }	
        System.out.println("Linea: "+linea);
}
}
