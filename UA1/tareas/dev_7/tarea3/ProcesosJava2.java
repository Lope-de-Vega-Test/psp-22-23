import java.util.Arrays;
/**
 *
 * @author david
 */
public class ProcesosJava2 {
    public static void main(String[] args) {
        //Crea un array con los argumentos del main, reemplaza corchetes y espacios vacios a "" para depues meter una coma para ir creando el array
        String[] argumentos = Arrays.toString(args).replace("[", "").replace("]", "").replace(" ", "").split(",");
       
        //Cuenta el numero de argumentos de un array
        int numeroArgumentos = argumentos.length;
        
        if(numeroArgumentos<2){
            if(argumentos[0].isBlank())
                numeroArgumentos=0;
        }
        
        switch(numeroArgumentos){
            case 0: {
                //Devuelve 1 si no hay argumentos
                System.exit(1);
            }
            case 1: 
                //Intenta pasar el argumento a numero entero y si es es menor que 0 devuelve 3
                try{
                    int valorArgumento = Integer.parseInt(argumentos[0]);
                    if(valorArgumento<0)
                        System.exit(3);
                //No puede pasar el argumento a numero entero y devuelve 2 porque es tipo String
                } catch (Exception e) {
                    System.exit(2);
                }
            default:
                System.exit(0);
            
        }
    }
}
