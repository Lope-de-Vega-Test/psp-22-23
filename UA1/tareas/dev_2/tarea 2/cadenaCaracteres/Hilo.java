package cadenaCaracteres;
//LA  CLASE HILO HEREDA DE LA CLASE THREAD PARA HACER EL HILO
public class Hilo extends Thread {
//	INICIALIZAMOS LA VARIABLE TEXTO
	private String texto = "";

	public void run() {
//		INSTANCIAMOS LA CLASE DONDE TENEMOS LA FUNCION QUE VAMOS A UTILIZAR
		Lectura leer = new Lectura();
		String resultado = leer.escribir(texto);
//		MOSTRAMOS EL STRING
		System.out.println(resultado);
		System.out.println("fin texto");

	}
}
