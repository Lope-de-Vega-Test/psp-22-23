import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Ejecutar {
    public static void main(String[] args) {       
        Scanner entrada = new Scanner(System.in);
        System.out.print("Cuantos procesos quieres ejecutar: ");
        int aux = entrada.nextInt();
       String comando = "cmd.exe /c start "+"java MiembroToken.java";
        for(int i = 2; i<=aux-1;i++){
            try {
                Process child = Runtime.getRuntime().exec(comando+" "+i+" "+(10000+i-1)+" no no");
            } 
            catch (IOException ex) {
                Logger.getLogger(Ejecutar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
                Process child = Runtime.getRuntime().exec(comando+" "+aux+" "+(10000+aux-1)+" no yes");
            } 
            catch (IOException ex) {
                Logger.getLogger(Ejecutar.class.getName()).log(Level.SEVERE, null, ex);
            }
        try {
                Process child = Runtime.getRuntime().exec(comando+" "+1+" "+(10000)+" yes no");
            } 
            catch (IOException ex) {
                Logger.getLogger(Ejecutar.class.getName()).log(Level.SEVERE, null, ex);
            }
}
}
