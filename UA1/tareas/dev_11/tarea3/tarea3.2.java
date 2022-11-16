public class tarea3_1 {

    public static void main(String[] args) {
        
        if (args.length < 1){
            System.exit(1);
        }
        
        else{
                
            try{
                
                //Intento de convertir el argumento a Int y si funciona devuelve 0
                int argumentoProbar = Integer.parseInt(args[0]);
                
                //Si se ha podido transformar a INT, aplicaremos las peticiones del ejercicio
                if(argumentoProbar < 0){
                    //Si es menor que 0 devuelve 3
                    System.exit(3);
                }
                
                else{
                    //Si es cualquier otro devuelve 0
                    System.exit(0);
                }
                    
            }catch(Exception e) {
                
                //Si da error significa que no se ha podio transformar a Int, por lo tanto sabemos que es una cadena
                System.exit(2);
            }
        }
}
}
