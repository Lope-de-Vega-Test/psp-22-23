
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {

        int NUM_MIEMBROS = Integer.parseInt(args[0]);
        int id = 1;
        int port = 10000;

        Runtime r = Runtime.getRuntime();
        ProcessBuilder myProcess;
        Process p;
        
        // SOLO SE EJECUTA SI EL USUARIO QUIERE LANZAR 2 O MAS MIEMBROS
        if (NUM_MIEMBROS > 1) {
            // PRIMER MIEMBRO TOKEN
            myProcess = new ProcessBuilder("java", "MiembroToken.java", "1", "10000", "yes", "no", String.valueOf(port+NUM_MIEMBROS-1));
            myProcess.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            p = myProcess.start();
            // BUCLE QUE LANZA N MIEMBROS TOKEN
            if (NUM_MIEMBROS > 2) {
                for (int i = 0; i < NUM_MIEMBROS - 2; i++) {
                    myProcess = new ProcessBuilder("java", "MiembroToken.java", String.valueOf(++id), String.valueOf(++port), "no", "no");
                    myProcess.redirectOutput(ProcessBuilder.Redirect.INHERIT);
                    p = myProcess.start();
                }
            }

            // ULTIMO MIEMBRO TOKEN
            myProcess = new ProcessBuilder("java", "MiembroToken.java", String.valueOf(++id), String.valueOf(++port), "no", "yes");
            myProcess.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            p = myProcess.start();
        } else if (NUM_MIEMBROS==1) {
            System.out.println("No puede haber un TOKEN RING con 1 solo Miembro!");
        } else {
            System.out.println("El TOKEN RING debe tener al menos 2 Miembros!");
        }
    }

}
