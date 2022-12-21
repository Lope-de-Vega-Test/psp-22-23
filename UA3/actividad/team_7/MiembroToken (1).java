/*
* @team     7
* @author   David Bravo Fernandez       (dev_7)
* @author   Adrián Luque Mantero        (dev_21)
* @author   Ignacio Martinez Sanchez    (dev_22)
 */

import java.io.*;
import java.net.*;

public class MiembroToken {

    static int id;
    static int selfPort;
    static int lastPort;
    static boolean isFirst = false;
    static boolean isLast = false;

    /* @brief       Classic java main, starting execution
     * @param       arg[0] = [id]
     * @param       arg[1] = [puerto]
     * @param       arg[2] = [isFirst]
     * @param       arg[3] = [isLast]
     */
    // java MiembroToken.java id puerto isFirst isLast
    public static void main(String[] args) throws InterruptedException {
        // CONTROL DE ERRORES: CANTIDAD DE PARAMENTROS Y FORMATO DE ENTRADA DE USUARIO
        if (args.length < 4 && args.length > 5) {
            System.out.println("Debes introducir al menos 4 parametros y maximo 5!");
            System.exit(-1);
        } else {
            // ID
            try {
                id = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Error en parametro (pos 1, id): debe ser un numero entero");
                System.exit(-1);
            }
            // PUERTO
            try {
                selfPort = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error en parametro (pos 2, puerto): debe ser un numero entero");
                System.exit(-1);
            }

            // PRIMERO?
            if (!args[2].equals("yes") && !args[2].equals("no")) {
                System.out.println("Error en parametro (pos 3, primero?): debe ser 'yes' o 'no'");
                System.exit(-1);
            } else {
                if (args[2].equals("yes")) {
                    isFirst = true;
                }
            }
            // ULTIMO?
            if (!args[3].equals("yes") && !args[3].equals("no")) {
                System.out.println("Error en Parametro (pos 4, ultimo?): debe ser 'yes' o 'no'");
                System.exit(-1);
            } else {
                if (args[3].equals("yes")) {
                    isLast = true;
                }
            }
            if (args.length == 5) {
                try {
                    lastPort = Integer.parseInt(args[4]);
                } catch (NumberFormatException e) {
                    System.out.println("Error en parametro (pos 5, puertoFinal): debe ser un numero entero");
                    System.exit(-1);
                }
            }
        } // FIN CONTROL ERRORES
        // CREACION Y EJECUCION DE UN HILO
        Hilo h = new Hilo(id, selfPort, isFirst, isLast, lastPort);
        h.start();
        try {
            h.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // FIN MAIN()
} // FIN CLASE MIEMBROTOKEN

class Hilo extends Thread {

    private int id;
    private int selfPort;
    private int lastPort;
    private boolean isFirst;
    private boolean isLast;
    private boolean hasToken;
    private Socket client;
    private ServerSocket server;

    // CONSTRUCTOR PARA EL PRIMER MIEMBRO TOKEN, PARA QUE SEPA QUIEN ES EL ULTIMO PUERTO
    public Hilo(int id, int selfPort, boolean isFirst, boolean isLast, int lastPort) {
        this.id = id;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.selfPort = selfPort;
        this.lastPort = lastPort;
        this.hasToken = false;
        if (this.isFirst) {
            this.hasToken = true;
        }
    }

    public void run() {
        // ME PRESENTO
        introducingMyself();
        // SIEMPRE ME CONFIGURO PARA ESCUCHAR!
        if (!serverSetUp()) {
            System.out.println("Miembro: " + id + ", No pudo configurarse");
            System.exit(-1);
        }
        // QUIEN SOY?
        if (isFirst) { // SOY EL PRIMERO!

            // INTENTO ENVIAR EL TOKEN HACIA DELANTE
            // ESPERA TANTO TIEMPO COMO MIEMBROS HAYA (ESPERA A QUE A TODOS LOS MIEMBROS LES DE TIEMPO A CONFIGURARSE)
            try{Hilo.sleep(Math.abs((lastPort-10000)*100));} catch (Exception e) {}
            if (sendToken(selfPort + 1)) {
                hasToken = false;
            } else {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            // INTENTO ENVIAR EL TOKEN HACIA ATRAS
            // ESPERA TANTO TIMEPO COMO MIEMBROS HAYA (ESPERA A QUE A TODOS LOS MIEMBROS LES DE TIEMPO A CONFIGURARSE)
            try{Hilo.sleep(Math.abs((lastPort-10000)*100));} catch (Exception e) {}
            
            if (sendToken(lastPort)) {
                hasToken = false;
            } else {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            // ESPERO A RECIBIR CUALQUIER TOKEN
            do {
                hasToken = waitForToken();
            } while (!hasToken);

            hasToken = false;
            // ESPERO A RECIBIR EL OTRO TOKEN
            do {
                hasToken = waitForToken();
            } while (!hasToken);

            System.out.println("---");
            System.out.println("Miembro: " + id + ", Fin del programa");
        } // FIN PRIMERO
        else if (isLast) { // SOY EL ULTIMO!

            // ESPERO A RECIBIR EL TOKEN DEL PRIMER MIEMBRO
            do {
                hasToken = waitForToken();
            } while (!hasToken);

            // INTENTO ENVIAR EL TOKEN
            if (sendToken(selfPort - 1)) {
                hasToken = false;
            } else {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            // ESPERO A RECIBIR EL OTRO TOKEN
            do {
                hasToken = waitForToken();
            } while (!hasToken);

            // INTENTO ENVIAR EL TOKEN
            if (sendToken(10000)) {
                hasToken = false;
            } else {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

        } // FIN ULTIMO
        else { // NO SOY NI EL PRIMERO NI EL ULTIMO!

            // ESPERO A RECIBIR CUALQUIER TOKEN
            do {
                hasToken = waitForToken();
            } while (!hasToken);

            // AVERIGUO SI ESTOY MAS CERCA DEL PRIMERO O DEL ULTIMO PARA EL PRIMER ENVIO DEL PRIMER TOKEN
            int firstSentTo = 0;
            if (getClosest() == -1) {
                // INTENTO ENVIAR EL TOKEN HACIA DELANTE SI
                if (sendToken(selfPort + 1)) {
                    hasToken = false;
                    firstSentTo = 1;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                }
            } else if (getClosest() == 1) {

                // INTENTO ENVIAR EL TOKEN HACIA ATRAS
                if (sendToken(selfPort - 1)) {
                    hasToken = false;
                    firstSentTo = -1;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                }
            }

            do {
                hasToken = waitForToken();
            } while (!hasToken);

            // ENVIO EL SEGUNDO TOKEN HACIA DELANTE O HACIA ATRAS DEPENDIENDO DE A QUIEN LE ENVIE ANTES
            if (firstSentTo == -1) {
                if (sendToken(selfPort - 1)) {
                    hasToken = false;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                }
            } else if (firstSentTo == 1) {
                if (sendToken(selfPort + 1)) {
                    hasToken = false;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                }
            } else {
                if (sendToken(selfPort + 1)) {
                    hasToken = false;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                    System.out.println("hacia delante");
                }
                if (sendToken(selfPort - 1)) {
                    hasToken = false;
                } else {
                    System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
                    System.out.println("hacia atras");
                }
            }

        } // FIN NI PRIMERO NI ULTIMO
    } // FIN RUN()

    void introducingMyself() {
        System.out.println("Miembro: " + id + ", Escucho Puerto: " + selfPort);
        if (isFirst) {
            System.out.println("Miembro: " + id + ", Tiene el Token, es el Primero");
        }

    }

    boolean serverSetUp() {
        boolean hasSetUp = false;
        try {
            Hilo.sleep(50);
            server = new ServerSocket(selfPort);
            hasSetUp = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasSetUp;
    }

    boolean sendToken(int toPort) {
        boolean hasSentToken = false;
        try {
            client = new Socket("localhost", toPort);
            System.out.println("Miembro: " + id + ", Envio Token al puerto " + toPort);
            hasSentToken = true;
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hasSentToken;
    }

    boolean waitForToken() {
        boolean hasReceivedToken = false;

        while (!hasReceivedToken) {
            try {
                do {
                    client = server.accept();
                } while (client == null);
                hasReceivedToken = true;
                System.out.println("Miembro: " + id + ", Token recibido");
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return hasReceivedToken;
    }

    int getClosest() {
        int closest = 0;
        int diffToFirst = Math.abs(selfPort - 10000);
        int diffToLast = Math.abs(selfPort - lastPort);

        if (diffToFirst < diffToLast) {
            closest = -1;
        } else if (diffToLast < diffToFirst) {
            closest = 1;
        }

        return closest;
    }

} // FIN CLASE HILO
