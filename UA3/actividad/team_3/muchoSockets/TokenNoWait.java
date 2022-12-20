package muchoSockets;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TokenNoWait {
    
    public static void main(String[] args) {
        
        String argId, argPort, argToken, argLast;
        argId = args[0];
        argPort = args[1];
        argToken = args[2];
        argLast = args[3];

        /*
         * id -> Identificador 
         * port -> Puerto del Socket
         * token -> Comprobador para habilitar los envíos
         * last -> Comprobador para saber si estamos en el último miembro de la cadena
         */
        int id = Integer.parseInt(argId);
        int port = Integer.parseInt(argPort);
        boolean token;
        boolean last;
        if(argToken.equals("no")) {
        	token=false;
        }else {
        	token=true;
        }
        if(argLast.equals("no")) {
        	last=false;
        }else {
        	last=true;
        }
        

        //WHILE INFINITO
        while(true){
            //COMPROBAR SI EL MIEMBRO TIENE EL TOKEN
            if(token){
                //ENVIAR SEÑAL
//            	System.out.println(argId+" "+argPort+" "+argToken+" "+argLast);
                try{
                    Socket sender;
                    //COMPROBAR SI EL MIEMBRO ES EL ÚLTIMO DE LA CADENA
                    if(last){
                        sender = new Socket("localhost",10000);
                    }else{
                        sender = new Socket("localhost",port+1);
                    }
                    InetAddress i = sender.getInetAddress();
                    System.out.println("Puerto Local: " + sender.getLocalPort());
                    System.out.println("Puerto al que envio: " + sender.getPort());
                    System.out.println("Nombre Host/IP: " + sender.getInetAddress());
                    System.out.println("Host Remoto: " + i.getHostName().toString());
                    System.out.println("IP Host Remoto: " + i.getHostAddress().toString());

                    token = false;
                    /**
                     * TIEMPO DE ESPERA SEGÚN ID SEÑAL
                     */
//                    Thread.sleep(id * 1000);
//                    System.out.println(id+" fin dormir");
                    sender.close();

                }catch(Exception e1){
                    System.out.print(".");
                    
                    try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
                }
            }else{
                //ESCUCHAR SEÑAL
                try{
                    ServerSocket reciever = new ServerSocket(port);
                    System.out.println("Escuchando en " + reciever.getLocalPort());
                    Socket senderMember = reciever.accept();
                    token = true;

                    reciever.close();
                }catch(Exception e2){
                    System.out.println("El socket no puede escuchar ninguna señal");
                }
            }
        }
    }
}
