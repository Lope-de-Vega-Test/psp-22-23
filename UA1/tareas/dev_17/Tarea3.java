public class Tarea3 {
	public static void main(String[] args)  {
		try {
			//si no hay argumentos, el systemexit devuelve 1
	  		if (args.length == 0) {
				System.out.println("No hay argumentos D:");
				System.exit(1);
			}else{
				//si la funcion tipoargs, que dice si lo introducido es int o string, devuelve true,
				//llamamos a la funcion menorde10, para ver si devolvemos 0 o 3.
				if(tipoargs(args[0]) == true){
					if(menorde10(args[0])==true){
						System.out.println("Es mayor de 0");
						System.exit(0);
					}else{
						System.out.println("es menor de 0");
						System.exit(3);
					}
				//si no es un numero, y es un string, devuelve 2
				}else{
					System.out.println("Es una cadena");
					System.exit(2);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}

  public static boolean tipoargs(String args){
	boolean fin;
	//si args se puede convertir a Int, devuelve true
	try {
		Integer.parseInt(args);
		fin=true;
	//si args no se puede convertir a Int, devuelve false, por lo que es una cadena
	} catch (NumberFormatException exception) {

		fin=false;
	}
	return fin;
  }

  public static boolean menorde10(String args){
	boolean fin=false;
	//declaramos el argumento como int
	int numero=Integer.parseInt(args);
	try {
		//si es un Int y mayor que 0, devuelve true, si no deuveld efaltse
		if(tipoargs(args)==true && numero>=0){
			fin = true;
		}else{
			fin=false;
		}
	} catch (NumberFormatException exception) {
		fin = false;
		}
		return fin;
	}
  }
