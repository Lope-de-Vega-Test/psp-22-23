package tarea3parte1;

import java.util.Scanner;
import java.lang.*;

public class Tarea3parte1 {

    public static void main(String[] args) {

        // SI NO EXISTEN ARGUMENTOS, NOS DEVOLVERA EL VALOR 1
        if (args.length < 1) {

            System.out.println("No hay argumentos, valor: 1");

        } else {

            for (int i = 0; i < args.length; i++) {

                if (esNumero(args[i]) == false) {

                    System.out.println("El argumento es " + args[i] + " valor devuelto: 2");

                }

                if (esNumero(args[i]) == true) {

                    int argInt;
                    argInt = Integer.parseInt(args[i]);

                    if (argInt < 0) {

                        System.out.println("El argumento es " + argInt + " valor devuelto: 3");

                    }

                    if (argInt > 0) {

                        System.out.println("El argumento es " + argInt + " valor devuelto: 0");

                    }

                }

            }

        }

    }

    public static boolean esNumero(String cadena) {

        boolean dev;

        try {

            Integer.parseInt(cadena);
            dev = true;

        } catch (NumberFormatException excepcion) {

            dev = false;

        }

        return dev;

    }

}
