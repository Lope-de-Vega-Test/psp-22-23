/*
 * @author Alfonso Alcaraz
 */
package procesos2java;

public class Index {

	public static void main(String[] args) {

		try {
			//VARIABLE CONTENCION POSIBLE NUMERO
			int numero = 0;
			//SI NO TIENE ARGUMENTOS IMPRIME 1
			if (args.length < 1) {
				System.out.println("1");
				System.exit(1);
				//SI TIENE ALGUN ARGUMENTO
			} else {
				//SI TIENE SOLO UN ARGUMENTO
				if (args.length == 1) {
					//SI EL ARGUMENTO ES UN CADENA DE CARACTERES
					if (esNumero(args[0]).equals("String")) {
						System.out.println("2");
						System.exit(2);
						//SI EL ARGUMENTO ES UN NUMERO LO CONVIERTE EN ENTERO
					} else {
						numero = Integer.parseInt(args[0]);
						//ES NUMERO Y MENOR QUE 0
						if (esNumero(args[0]).equals("Integer") && numero < 0) {
							System.out.println("3");
							System.exit(3);
							//ES NUMERO Y MAYOR QUE 0
						} else {
							System.out.println("0");
							System.exit(0);
						}
					}
					//SI TIENE MAS DE UN ARGUMENTO
				} else {
					System.out.println("0");
					System.exit(0);
				}
			}
			//SI NO TIENE ARGUMENTOS SALTA LA EXCEPCION
		} catch (Exception e) {
			System.out.println("1");
			System.exit(1);

		}

	}
	//DEVUELVE INTEGER O STRING SEGUN EL ARGUMENTO
	public static String esNumero(String texto) {

		String check;

		try {
			Integer.parseInt(texto);
			check = "Integer";
		} catch (Exception e) {
			check = "String";
		}

		return check;
	}

}
