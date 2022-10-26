import java.util.Scanner;


public class tarea2ejec {
    public static void main(String[] args) {
      
        Scanner sca = new Scanner(System.in);
        String letra = "";
        String lista = "";
        
       while(!letra.equals("*")){
 
          lista += letra; 
          letra = sca.next();
         
       }
        System.out.println("lista "+lista);
       
    }
}
