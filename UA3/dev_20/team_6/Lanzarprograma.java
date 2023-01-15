import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clase Lanzarprograma
public class Lanzarprograma{

   
    //Funcion main que nos preguntara por los miembros que formaran parte del todo el procesos para enviar y recibir token junto a su despectivo puerto
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Pon un numero de miembros que quieres lanzar:  ");
        
        int numeroMiembros = entrada.nextInt();
        
        String command = "cmd.exe /c start "+"java Miembrotoken.java";
        
        //Funcion for que permita avanzar el proceso del programa
        for(int i = 2; i<=numeroMiembros-1;i++)
        {
            try 
            {
                Process child = Runtime.getRuntime().exec(command+" "+i+" "+(10000+i-1)+" no no");
            } catch (IOException ex) {
                Logger.getLogger(Lanzarprograma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            try 
            {
                Process child = Runtime.getRuntime().exec(command+" "+numeroMiembros+" "+(10000+numeroMiembros-1)+" no yes");
            } catch (IOException ex) {
                Logger.getLogger(Lanzarprograma.class.getName()).log(Level.SEVERE, null, ex);
            }
           

            try 
            {
                Process child = Runtime.getRuntime().exec(command+" "+1+" "+(10000)+" yes no");
            } catch (IOException ex) {
                Logger.getLogger(Lanzarprograma.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
