//compilar y ejecutar: javac ua2tarea3fr1.java && java ua2tarea3fr1

import java.io.FileReader;
import java.io.File;//Para obtener información sobre archivos y directorios y su manejo

class ua2tarea3fr1{

    public static void main(String[] args) {

        String nombreFichero;//Usaremos esta variable para enviar el nombre de cada fichero introducido como argumento
        int contadorCaracteres = 0;//En esta variable guardaremos el total de caracteres de cada fichero
        FileReader fichero = null;//Este FileReader nos servirá para leer cada fichero caracter por caracter
        File f;//La variable File la usamos para verificar que el argumento sea válido

        long t_comienzo, t_fin;//Estas variables las usamos para comprobar al final del proceso el tiempo de ejecución del programa
        t_comienzo = System.currentTimeMillis();//Le pasamos al t_comienzo el valor actual del tiempo de ejecución

        //Con este for buscamos repetir el proceso por cada argumento introducido, usando para ello args.length
        for (int i = 0; i < args.length; i++) {

            //En cada iteración del for guardamos el valor del argumento en la posicion actual a nombreFichero
            nombreFichero = args[i];
            f = new File(nombreFichero);//Pasamos a f el nombre del fichero

            //Si el nombre y la ruta del argumento son correctos, contaremos los caracteres del documento
            if(compruebaPath(f)){
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
            //Si no son correctos, lo mostraremos por pantalla
            else{
                System.out.println("***********************************");
                System.out.println("EL FICHERO " + nombreFichero + " NO EXISTE EN LA RUTA ESPECIFICADA");
                System.out.println("***********************************");
            }
        }

        t_fin = System.currentTimeMillis();//Le pasamos al t_fin el valor actual del tiempo de ejecución
        long t_total = t_fin - t_comienzo;//El tiempo total de ejecución es la resta del tiempo final menos el inicial

        System.out.println();
        System.out.println("EL PROGRAMA SE HA EJECUTADO EN: " + t_total + " milisegundos");//Mostramos el tiempo de ejecución total
    }

    public static boolean compruebaPath(File f) {
        boolean result = false;

        //Usando las funciones de la librería File comprobamos si el fichero en la ruta introducida existe, es un fichero y se puede leer.
        //Si las tres condiciones se cumplen, devolveremos el valor de true, permitiendo que el proceso se ejecute, sino, se devolverá false
        if(f.exists()){
            if(f.isFile()){
                if(f.canRead()){
                    result = true;
                }
            }
        }
        return result;
    }
}
