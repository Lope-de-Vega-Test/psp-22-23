//compilar y ejecutar: javac ua2tarea3fr1.java && java ua2tarea3fr1

import java.io.FileReader;

class ua2tarea3fr1{

    public static void main(String[] args) {

        String nombreFichero;//Usaremos esta variable para enviar el nombre de cada fichero introducido como argumento
        int contadorCaracteres = 0;//En esta variable guardaremos el total de caracteres de cada fichero
        FileReader fichero = null;//Este FileReader nos servirá para leer cada fichero caracter por caracter

        //Con este for buscamos repetir el proceso por cada argumento introducido, usando para ello args.length
        for (int i = 0; i < args.length; i++) {

            //En cada iteración del for guardamos el valor del argumento en la posicion actual a nombreFichero
            nombreFichero = args[i];

            try {
                //Abrimos el fichero indicado en la variable nombreFichero
                fichero = new FileReader(nombreFichero);
                //Leemos el primer carácter guardándolo en una variable de tipo int
                int caracter = fichero.read();
                //sumamos el primer caracter a contadorCaracteres
                contadorCaracteres = 1;

                //Se recorre el fichero hasta encontrar el carácter -1 que marca el final del fichero
                while(caracter != -1) {
                    //Leemos el siguiente carácter
                    caracter = fichero.read();

                    //Aumentamos en cada iteración en uno el valor de contadorCaracteres
                    contadorCaracteres++;
                }

                //Cerramos el fichero
                fichero.close();
            }

            //Control de errores del programa
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
            }

            //Finalmente mostramos el total de caracteres del fichero junto con su nombre
            System.out.println("--------------------------------------------------------");
            System.out.println("CONTANDO CARACTERES EN EL FICHERO " + nombreFichero + "...");
            System.out.println();
            System.out.println("Hay " + contadorCaracteres + " caracteres en el archivo " + nombreFichero);
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println();

            //Volvemos a inicializar a 0 el contador de caracteres
            contadorCaracteres = 0;

        }
    }
}
