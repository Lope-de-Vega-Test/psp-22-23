 //execute:
//java Lanzador.java
//importante encontrarse en la carpeta del archivo Lanzador.java a la hora de ejecutar y que ambos
//archivos esten en la misma carpeta

import java.io.*;
import java.util.Scanner;

public class lanzador {

	public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.dir");
        Runtime.getRuntime().exec("cmd.exe /c start cmd /k java MiembroToken.java 3 10002 0 1");
        Runtime.getRuntime().exec("cmd.exe /c start cmd /k java MiembroToken.java 2 10001 0 0");
        Runtime.getRuntime().exec("cmd.exe /c start cmd /k java MiembroToken.java 1 10000 1 0");


    }
}
