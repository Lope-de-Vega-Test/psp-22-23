/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej2;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/* Criterios a), e), f), g), h)
Fecha de Entrega: 19/10/2022 - 14:00:00
PROCESOS. Crea un programa Java que implemente las siguientes Funcionalidades Requeridas (FRs):

FR1: lea una cadena de caracteres desde la entrada estándar hasta recibir un carácter de terminación, en concreto, un asterisco * - 2 puntos
FR2: una vez recibido el caracter de terminación, muestre por pantalla toda la información leída - 2 puntos
FR3: Crea después otro programa que ejecute el anterior - 2 puntos
Implementa el control de errores - 2 puntos
Documenta el código - 2 puntos */

/**
 *
 * @author Ignacio
 */
public class Ej2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
        
        
        ProcessBuilder pb = new ProcessBuilder("java", "ej2/cadenaCaracteres");

        Process p = pb.start();
        
        // escritura -- envia entrada 
	OutputStream os = p.getOutputStream();
	os.write("Hola Manuel\n".getBytes());
	os.flush(); // vacia el buffer de salida

	// lectura -- obtiene la salida
	InputStream is = p.getInputStream();
	int c;
	while ((c = is.read()) != -1){
            System.out.print((char) c);
        }
        is.close();

        // COMPROBACION DE ERROR - 0 bien - 1 mal
	int valorSalida;
	try {
            valorSalida = p.waitFor();
            System.out.println("Valor de Salida: "+valorSalida);
	} catch (InterruptedException e) {
            e.printStackTrace();
	}

	try {
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine()) != null){
		System.out.println("ERROR: " + liner);
            }
	} catch (IOException ioe) {
            ioe.printStackTrace();
	}
        
    }
    
}
