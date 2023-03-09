
package com.victoraljama;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.SocketException;


public class Api {
    
    private String hostName;
    private int port;
    
    public Api(){
        this.hostName = "localhost";
        this.port = 8080;
    }

    public String getHostName() {
        return hostName;
    }

    public int getPort() {
        return port;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    public void crearApi() throws SocketException{
        
        gestionDatos data = new gestionDatos();
        
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(hostName, port), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        server.createContext("/api/greeting", new GreetingHandler());
        
        server.createContext("/api/bye", new ByeHandler());
        
        server.createContext("/api/hash", new HashHandler());
        
        server.createContext("/api/person", new PersonHandler(data.store));
        
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println(
                "El framework-less REST API servidor esta escuchando en " + server.getAddress().getAddress()
                        + ":" + server.getAddress().getPort());
    }
}
