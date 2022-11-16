/*Condiciones del ejercicio: 
- Si el número de argumentos es < 1 debe devolver 1
- Si el argumento es una cadena debe devolver 2
- Si el argumento es un número entero menor que 0 debe devolver 3
- En cualquier otro caso debe devolver 0*/

public class Argumentos {
	public static void main(String[] args) {
		//Condición numero 1:
        try{
            if(args.length < 1) {
                System.exit(1);
                
            }
            //Condición numero 3:
            if(comprobarNumeromenorcero(args[0])) {
                System.exit(3);
                
            //Condición numero 2:
            } else if(args[0].length()>1){
                System.exit(2);
                
            //Condición default:
            } else {
                System.exit(0);
            }
        }catch(Exception e){
            System.exit(0);
        }
		
	}
	
	//FUNCION PARA COMPROBAR SI ES UN NUMERO MENOR QUE CERO
	public static boolean comprobarNumeromenorcero(String cadena){
		
		try {
			int numero = Integer.parseInt(cadena);
			if(numero<0) {
				return true;
			} else {
				return false;
			}
			
		} catch (NumberFormatException error){
			return false;
		}
	}
}
