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
        if (args.length == 4) {
            Hilo h = new Hilo(id, selfPort, isFirst, isLast);
            h.start();
            try {
                h.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (args.length == 5) {
            Hilo h = new Hilo(id, selfPort, isFirst, isLast, lastPort);
            h.start();
            try {
                h.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    } // FIN MAIN()

} // FIN CLASE MIEMBROTOKEN

class Hilo extends Thread {

    private int id;

    private int selfPort;
    private int nextPort;
    private int lastPort;

    private boolean isFirst;
    private boolean isLast;

    private boolean hasToken;

    private Socket client;
    private ServerSocket server;

    public Hilo(int id, int selfPort, boolean isFirst, boolean isLast) {
        this.id = id;
        this.isFirst = isFirst;
        this.isLast = isLast;

        this.selfPort = selfPort;

        this.hasToken = false;

        if (this.isFirst) {
            this.hasToken = true;
        }
    }

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
        System.out.println("Miembro: " + id + ", Escucho Puerto: " + selfPort);

        // SIEMPRE ME CONFIGURO PARA ESCUCHAR!
        try {
            server = new ServerSocket(selfPort);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

        // QUIEN SOY?
        if (isFirst) { // SOY EL PRIMERO!

            // EL PRIMERO SIEMPRE TIENE EL TOKEN
            System.out.println("Miembro: " + id + ", Tiene el Token, es el Primero");
            try {
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = selfPort + 1;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
                System.out.println("Miembro: " + id + ", Esperando a que regrese el Token");
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = lastPort;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
                System.out.println("Miembro: " + id + ", Esperando a que regrese el Token");
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            // ESPERO A RECIBIR EL TOKEN
            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }
            // COMPRUEBO SI RECIBI EL TOKEN
            if (hasToken && client != null) {
                System.out.println("Miembro: " + id + ", Token recibido");
            }

            System.out.println("---");
            System.out.println("Miembro: " + id + ", Recorrido hacia Delante finalizado");
            System.out.println("---");

            client = null;
            hasToken = false;

            // ESPERO A RECIBIR EL TOKEN
            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }
            // COMPRUEBO SI RECIBI EL TOKEN
            if (hasToken && client != null) {
                System.out.println("Miembro: " + id + ", Token recibido");
            }

            System.out.println("---");
            System.out.println("Miembro: " + id + ", Recorrido hacia Atras finalizado");
            System.out.println("---");
            System.out.println("Miembro: " + id + ", Fin del programa");
            System.out.println("---");

        } // FIN PRIMERO
        else if (isLast) { // SOY EL ULTIMO!

            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }

            // RECIBO EL TOKEN Y ME DUERMO
            System.out.println("Miembro: " + id + ", Token recibido");
            try {
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = selfPort - 1;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }

            // RECIBO EL TOKEN Y ME DUERMO
            System.out.println("Miembro: " + id + ", Token recibido");
            try {
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = 10000;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

        } // FIN ULTIMO
        else { // NO SOY NI EL PRIMERO NI EL ULTIMO!

            // SIEMPRE ME CONFIGURO PARA ESCUCHAR!
            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }

            // RECIBO EL TOKEN Y ME DUERMO
            System.out.println("Miembro: " + id + ", Token recibido");
            try {
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = selfPort + 1;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

            while (!hasToken) {
                try {
                    do {
                        client = server.accept();
                    } while (client == null);
                    hasToken = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    //System.out.println("Estoy escuchando pero nadie me habla ):");
                }
            }

            // RECIBO EL TOKEN Y ME DUERMO
            System.out.println("Miembro: " + id + ", Token recibido");
            try {
                Hilo.sleep(id * 1000);
            } catch (Exception e) {
            }

            // INTENTO ENVIAR EL TOKEN
            try {
                nextPort = selfPort - 1;
                client = new Socket("localhost", nextPort);
                System.out.println("Miembro: " + id + ", Envio Token al puerto " + nextPort);
                hasToken = false;
                client.close();
            } catch (Exception e) {
                System.out.println("Miembro: " + id + ", No pudo enviar el Token ):");
            }

        } // FIN NI PRIMERO NI ULTIMO

    } // FIN RUN()

} // FIN CLASE HILO
