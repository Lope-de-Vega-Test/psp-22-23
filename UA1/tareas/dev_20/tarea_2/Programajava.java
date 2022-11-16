package lanzarprogramajava;
import java.util.Scanner;
/**
 *
 * @author Lucía Luna
 */
public class Programajava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada= new Scanner(System.in);
        
        String Palabra= " ";
        
        boolean numLetra= false;
        
        System.out.println("Indrouce una cadena de caracteres: ");
        
        
        
        while(numLetra==false)
        {
            Palabra += entrada.nextLine();
            if(Palabra.contains("*"))
            {
                System.out.println("La cadena de caracteres contiene un *");
                numLetra=false;
            }
            break;
        }
        
        
        
        
        for(int i=0; i<Palabra.lastIndexOf("*"); i++)
            {
                System.out.print(Palabra.charAt(i));
            }
    }
    
}
