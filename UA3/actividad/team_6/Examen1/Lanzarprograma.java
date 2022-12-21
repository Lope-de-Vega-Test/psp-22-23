/**
 * @file        MiembroToken.java
 *
 * @brief       Solución para la actividad evaluable de la UA3.
 * @date        14/12/2022
 * @author      José Elías Alonso Trenas Miembro 1 - joselias2003@gmail.com (dev_4)
 * @author      Irene Alba Posadas Miembro 2 - ireealba23@gmail.com (dev_1)
 * @author      Lucía Luna Posadas Miembro 3 - lucialunaposadas@gmail.com (dev_20)
 * @author      Lucia Maria Ayllon Pozo Miembro 4 - luciaayllon7@gmail.com (dev_6)
 *
 * @note        ¡Pero qué diablos!
 * @note        https://docs.google.com/presentation/d/e/2PACX-1vQzxbSl2IXLxelLggksAWEQd2tDT-5sNqETQCpIHCnNBqjoSmbvlJdCcOfu_rjQLz_BN6lsoOjimqSO/pub?start=false&loop=false&delayms=3000
 */

/*
* @class    MiembroToken
* @brief    Clase para simular el comportamiento de una red token ring
*
* @todo FR1 [5 puntos]: Implementa la clase MiembroToken con la funcionalidad descrita previamente. Para comprobar la correcta ejecución del sistema, la clase MiembroToken debe imprimir por pantalla la suficiente información para ver el estado de cada Miembro de la red.
* @todo FR2 [2,5 puntos]: Mejora la clase MiembroToken para poder ejecutar su funcionalidad, en este caso, dormir una cantidad de tiempo determinada, como un hilo.
* @todo FR4 [1 punto]: Mejora la clase MiembroToken para crear una red token ring de anillo doble, es decir, se puede tener otro token en otro anillo virtual, en sentido contrario.
*/

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
