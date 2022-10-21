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
import java.util.Scanner;

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
        Scanner entrada = new Scanner(System.in);
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);

        File directorio = new File("build\\classes");
        ProcessBuilder pb = new ProcessBuilder("java", "ej2.cadenaCaracteres");
        pb.directory(directorio);//Le decimos dónde está el proceso que vamos a llamar

        Process p = pb.start();// se ejecuta el proceso

        OutputStream os = p.getOutputStream();//Escritura: aquello que mandamos al proceso llamado

        String cadenaMandada;//Variable para guardar el string que mandamos al proceso

        do {//El proceso se repite tantas veces como veces repita el proceso
            //Por eso ambos tienen un do-while con una condición de salida similar, dependiente de '*'
            System.out.println("Introduce una cadena de caracteres\nEl caracter de terminación es '*'\n");
            cadenaMandada = entrada.nextLine();
            cadenaMandada += "\n";
            os.write(cadenaMandada.getBytes());//Tras leer el string lo manda al proceso
            os.flush(); // vacia el buffer de salida
        } while (cadenaMandada.contains("*") == false);//Repite mientras la cadena mandana no contenga '*'

        InputStream is = p.getInputStream();// lectura: recibe respuesta del proceso
        int c;
        while ((c = is.read()) != -1) {
            System.out.print((char) c);
        }
        is.close();

        int exitVal;
        try {
            // COMPROBACION DE ERROR - 0 bien - 1 mal
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            InputStream er = p.getErrorStream(); //Recoge errores
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine()) != null) {
                System.out.println("ERROR >" + liner);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
