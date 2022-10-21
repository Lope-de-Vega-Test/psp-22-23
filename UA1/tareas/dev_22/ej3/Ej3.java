/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ej3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/* Criterios a), e), f), g), h)
Fecha de Entrega: 26/10/2022 - 14:00:00
PROCESOS. Crea un programa en Java que admita argumentos desde main() y devuelva con System.exit() los siguientes valores:

Si el número de argumentos es < 1 debe devolver 1
Si el argumento es una cadena debe devolver 2
Si el argumento es un número entero menor que 0 debe devolver 3
En cualquier otro caso debe devolver 0
Realiza un segundo programa Java que ejecute al anterior. Este programa deberá mostrar por pantalla lo que sucede según el valor devuelto al ejecutar el programa principal.

Crea un programa Java que implemente las siguientes Funcionalidades Requeridas (FRs):

FR1: admita argumentos desde main() - 1 punto
FR2: devuelva con System.exit() los siguientes valores:
Si el número de argumentos es < 1 debe devolver 1 - 1 punto
Si el argumento es una cadena debe devolver 2 - 1 punto
Si el argumento es un número entero menor que 0 debe devolver 3 - 1 punto
En cualquier otro caso debe devolver 0 - 1 punto
FR3: Crea después otro programa que ejecute el anterior - 1 punto
Implementa el control de errores - 2 puntos
Documenta el código - 2 puntos */
/**
 *
 * @author Ignacio
 */
public class Ej3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException  {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
               
        File directorio = new File("build\\classes");
        ProcessBuilder pb = new ProcessBuilder("java", "ej3.argumentos", "Pepe");//Para este ejemplo mandamos string y debe devolver 2
        pb.directory(directorio);//Le decimos dónde está el proceso que vamos a llamar

        Process p = pb.start();
        
        try {
            
            int exitVal;
            try {
                exitVal = p.waitFor();
                System.out.println("Valor de Salida: " + exitVal);// COMPROBACION DE ERROR - 0 bien - 1 mal
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
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
