public class Tarea3 {
    /**
 *
 * @author avilnec
 */
    public static void main(String[] args) {
    // Si no le pasamos argumentos, devolverá 1.
        if (args.length < 1) {
            System.out.println("1");
            System.exit(1);
        } else {
            try {
    // Con el try, intentará convertir el argumento a string y si lo consigue, devolvera 0.
                int arg = Integer.parseInt(args[0]);
    // si el argumento es menor a 0, devolvera 3.
                if (arg < 0) {
                    System.out.println("3");
                    System.exit(3);
                } else
                    System.out.println("0");
                    System.exit(0);
    // Con el catch, si el argumento al convertirlo a entero da error, seria una cadena y devolvemos 2.
            } catch (Exception e) {
                System.out.println("2");
                System.exit(2);
            }
        }
    }
}