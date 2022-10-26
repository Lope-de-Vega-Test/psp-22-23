import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 *
 * @author david
 */
public class ProcesosJava1 {
    public static void main(String[] args) {
        try {
              //Crea puente entre flujos de bytes y flujos de caracteres: lee bytes y los decodifica en caracteres utilizando un juego de caracteres especifico.
              InputStreamReader is = new InputStreamReader(System.in);
              //Lee texto de un flujo de entrada de caracteres, almacenando en bufer los caracteres
              BufferedReader br = new BufferedReader (is);
			        char texto;
              //Muestra los carcteres anteriores cuando lea un *
			          while ((texto = (char) br.read()) != '*')
				          System.out.print((char) texto);	 
                is.close();
		          } catch (Exception e) {
			            e.printStackTrace();
		            }
  }
}
