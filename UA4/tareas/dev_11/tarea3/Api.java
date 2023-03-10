package com.mycompany.api;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class Api {
    
    private String host;
    private int puerto;
    
    public Api(){
        this.host = "localhost";
        this.puerto = 8080;
    }

    public String getHost() {
        return host;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
    
    public void apiMaker() throws SocketException{
        
        DataBaseHandler data = new DataBaseHandler();
        
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(host, puerto), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        server.createContext("/api/greeting", new GreetingHandler());
        
        server.createContext("/api/bye", new ByeHandler());
        
        server.createContext("/api/person", new PersonHandler(data.storage));
        
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println(
                "Estoy escuchando en el puerto " + server.getAddress().getAddress()
                        + ":" + server.getAddress().getPort());
    }
}
