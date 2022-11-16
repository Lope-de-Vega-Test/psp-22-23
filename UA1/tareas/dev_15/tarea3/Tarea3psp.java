



import java.io.InputStream;


public class Tarea3psp {

     public static void main(String[] args){ 
   
         if(args.length<1){
             System.exit(1);
         }
         else{
             try{
                 int numero=0;
             numero=Integer.parseInt(args[0]);
             if(numero<0){
                 System.exit(3);}
             else{
             System.exit(0);}
             }catch(Exception e){
                 System.exit(2);
             }
}
    }
         }
