package cadenaCaracteres;

import java.util.Scanner;

public class Lectura {
//	CLASE PARA INTRODUCIR TEXTO MANUALMENTE
	private Scanner scan = new Scanner(System.in);

	public String escribir(String texto) {

		System.out.println("Comienza a escribir");
		System.out.println("Para finalizar escribe * y pulse ENTER");
//		MIENTRAS NO SE INTRODUZCA EL CARACTER * AL FINAL SE REPITE
		while (!texto.endsWith("*")) {
			texto = texto + scan.nextLine();

		}
//		SI * ESTA AL FINAL SE DEVUELVE EL STRING SIN EL ULTIMO CARACTER
		if (texto.endsWith("*")) {
			texto = texto.substring(0, (texto.length() - 1));
		}
		return texto;
	}
}
