package com.adrianluque;

import com.sun.net.httpserver.HttpServer;
import com.adrianluque.handlers.FarewellHandler;
import com.adrianluque.handlers.GreetingsHandler;
import com.adrianluque.handlers.UserHandler;
import com.adrianluque.storage.Gateway;
import com.adrianluque.storage.users.UserStorage;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;


public class Api extends Thread {
    // Attributes
    private String hostname;
    private int port;

    // Constructors
    public Api() {
        this.hostname = "localhost";
        this.port = 8000;
    }

    // Getters
    public String getHostname() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }

    // Setters
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPort(int port) {
        this.port = port;
    }


    // Runnable method
    @Override
    public void run() {
        Gateway gateway = new Gateway();
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(hostname, port), 0);
        } catch (BindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.createContext("/api/greetings", new GreetingsHandler());
        server.createContext("/api/farewell", new FarewellHandler());
        server.createContext("/api/users", new UserHandler(gateway));
        
        

        server.start();
        System.out.println("API REST server is running listening on " + getHostname() + ":" + getPort());
    }
}
