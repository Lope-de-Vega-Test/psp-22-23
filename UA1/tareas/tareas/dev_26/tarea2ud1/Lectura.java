import java.util.Scanner;

public class Lectura {
    public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
	
		System.out.println("Introduzca caracteres (introducir * devuelve la cadena antes de *)");
		String textoBruto = entrada.next();
		String textoLimpio = null;
		boolean flagSalir = false;
		
		do {
			if(textoBruto.contains("*")) {
				textoLimpio = textoBruto.substring(0,textoBruto.indexOf("*"));
				System.out.println(textoLimpio);
				flagSalir = true;
			}
			else {
				System.out.println("Continue introduciendo caracteres");
				textoBruto = textoBruto.concat(entrada.next());
			}	
		}while(!flagSalir);
	}
}
