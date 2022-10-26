
package actividad3_javicastilla;


public class Actividad3_JaviCastilla {

    public static void main(String[] args) {
        //Si el numero de argumentos es menor o igual a 0, el sistema a traves de la funcion System.exit() devolverá el número 1 como ponia en el enunciado.
        if(args.length<=0){
            System.exit(1);
        }
        //Si hay un argumento introducido tendremos las siguientes posibilidades: 
        else{
            //Lo primero que tenemos que hacer es crear una funcion que nos detecte si el argumento introducido es un numero o no, la funcion que yo he hecho tiene el nombre de 
            //funcionnumero() que es de tipo Boolean. Ahora si la variable no es un numero y hay uno o varias cadenas introducidas, la funcion System.exit() devolvera el numero 2.
            if(funcionnumero(args[0]) == false && args.length==1){
                System.exit(2);
            }
            //Si la funcion creada nos detecta que el argumento introducido es un numero tenemos las siguientes posibilidades: 
            else{
                //Usamos de nuevo la funcion funcionnumero() para detectar si el argumento es un numero.
                if(funcionnumero(args[0]) && args.length==1){
                    //Creamos una variable int para introducir el numero.
                    int numero = 0;
                    //Al int le pasamos el valor del argumento, pero como es un numero metido en un String tenemos que pasarlo a numero  
                    //Con la funcion Integuer.parseInt() convertimos los String en numero en el caso que se pueda hacer
                    numero = Integer.parseInt(args[0]);
                    //Si el numero guardado es menor a 0 la funcion System.exit() devuelve el 3
                    if(numero<0){
                        System.exit(3);
                    }
                    //En el caso de que el numero introducido sea mayor a 0, a seguir las especificaciones pone que todo lo demas sea 0, hacemos que el System.exit devuelva 0
                    else{
                        System.exit(0);
                    }
                }
                //Si se detecta que hay mas de una variable independientemente de que sea o no un numero, la funcion System.exit() devolvera el numero 0.
                else{
                    System.exit(0);
                }
            }  
        }
    }
    //Esta funcion consiste en comprobar si el argumento introducido es un numero o no. Para ello tendremos que usar un try catch para que no nos de un fallo en el programa.
    public static boolean funcionnumero(String parametro){
        //Con el try se comprueba si el argumento es un numero. En el caso de que sea verdad devolvera true.
        try{
           Integer.parseInt(parametro);
           return true;
        }
        //En el caso del catch, si se comprueba que el argumento no es un numero, para evitar fallo en el programa usamos el catch que devolvera false.
        catch (NumberFormatException error){
           return false;
        }
    }
        
    }
