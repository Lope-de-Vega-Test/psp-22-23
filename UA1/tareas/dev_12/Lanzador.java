import java.util.Scanner;

public class Lanzador {
    
      public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String letra = "";
        String lista = "";
        
       while(!letra.equals("*")){
          
          lista += letra; 
          letra = entrada.next();
          
   
       }
        System.out.println("lista "+lista);
       
       
    }
    
    
}
