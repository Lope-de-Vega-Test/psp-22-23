//compilar y ejecutar: javac ua2tarea3fr2.java && java ua2tarea3fr2

import java.io.FileReader;
import java.io.File;//Para obtener información sobre archivos y directorios y su manejo

class Contador
{
    int contadorCaracteres = 0;//En esta variable guardaremos el total de caracteres de cada fichero

    private String titulo = "";  // Atributo título
    File f;//La variable File la usamos para verificar que el argumento sea válido

    //Constructor de la clase en la que introducimos el argumento actual y se lo pasamos a f para verificar que existe
    Contador (String titulo) {
        this.titulo = titulo;
        f = new File(titulo);
    }

    //Función para comprobar que cada argumento sea correcto
    public static boolean compruebaPath(File f) {
        boolean result = false;

        //Leeremos la ruta y verificamos si existe o no
        if(f.exists()){
            if(f.isFile()){
                if(f.canRead()){
                    result = true;
                }
            }
        }

        return result;
    }

    public void incrementa() {
        FileReader fichero = null;//Este FileReader nos servirá para leer cada fichero caracter por caracter

        if(compruebaPath(f)){
            try {
            //Abrimos el fichero indicado en la variable nombreFichero
            fichero = new FileReader(titulo);
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

            //Cerramos el FileReader
            fichero.close();
            }

            //Control de errores del programa
            catch(Exception e)
            {
                System.out.println("Error: "+e.getMessage());
            }

            //Finalmente mostramos el total de caracteres del fichero junto con su nombre
            System.out.println("--------------------------------------------------------");
            System.out.println("CONTANDO CARACTERES EN EL FICHERO " + titulo + "...");
            System.out.println();
            System.out.println("Hay " + contadorCaracteres + " caracteres en el archivo " + titulo);
            System.out.println("--------------------------------------------------------");
            System.out.println();
            System.out.println();

            //Volvemos a inicializar a 0 el contador de caracteres
            contadorCaracteres = 0;
        }
        //Si el fichero no existe en la ruta introducida, mostraremos el error
        else{
            System.out.println("***********************************");
            System.out.println("EL FICHERO " + titulo + " NO EXISTE EN LA RUTA ESPECIFICADA");
            System.out.println("***********************************");
            System.out.println();
            System.out.println();
        }

    }

    public int valor() {
        //Devolvemos el valor actual del contador.
        return contadorCaracteres;
    }
} // Fin Class Contador

class HiloContador extends Thread{
//En este caso implementaremos los hilos heredando de la clase Thread, lo que afectará a la manera en la que
//llamaremos a los hilos en la función main.

private Contador contador;//Instanciamos a la clase Contador.

    public HiloContador(String nombre, Contador titulo) {
        //creamos un constructor del hilo para que se le asigne el valor de titulo y un nombre.
        setName(nombre);
        contador = titulo;
    }

    //Aquí le indicamos al hilo qué debe hacer cuando se inicialice
    public void run() {
        contador.incrementa();
    }

}

class ua2tarea3fr2{

    public static void main(String[] args) {

        String nombreFichero;//Usaremos esta variable para enviar el nombre de cada fichero introducido como argumento
        //int contadorCaracteres = 0;//En esta variable guardaremos el total de caracteres de cada fichero
        //FileReader fichero = null;//Este FileReader nos servirá para leer cada fichero caracter por caracter

        long t_comienzo, t_fin;
        t_comienzo = System.currentTimeMillis();

        //Con este for buscamos repetir el proceso por cada argumento introducido, usando para ello args.length
        for (int i = 0; i < args.length; i++) {


            //En cada iteración del for guardamos el valor del argumento en la posicion actual a nombreFichero
            nombreFichero = args[i];

            //Instanciamos la clase contador pasándole el nombre del fichero actual
            Contador contador = new Contador(nombreFichero);

            //Instanciamos un hilo que sobrescribiremos para contar los caracteres de cada fichero
            HiloContador hilocontador = new HiloContador("Hilo "+i, contador);

            //Iniciamos el hilo contador
            hilocontador.start();

            //Implementamos el hilo en ejecución
            try {
                hilocontador.join();
            }
            //Control de errores
            catch (Exception e) {
                // TODO: handle exception
            }
        }

        t_fin = System.currentTimeMillis();
        long t_total = t_fin - t_comienzo;

        System.out.println();
        System.out.println("EL PROGRAMA SE HA EJECUTADO EN: " + t_total + " milisegundos");
    }
}
