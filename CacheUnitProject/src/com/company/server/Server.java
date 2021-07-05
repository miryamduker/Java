package com.company.server;

import com.company.services.CacheUnitController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements PropertyChangeListener, Runnable {

    private ServerSocket serverSocket;

    private Socket socket;

    private boolean isUp = true;

    private String propertyName;

    private  String oldProprtyName;


    public Server(){
//        try {
//            serverSocket = new ServerSocket(12345);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent event){

        propertyName = (String)event.getNewValue();
        if (propertyName.equalsIgnoreCase(oldProprtyName)){
            if(propertyName.equalsIgnoreCase("start"))
                System.out.println("Server is already running");
            else if(propertyName.equalsIgnoreCase("shutdown"))
                System.out.println("Server is already shutdown");

//            return;
        }
        else if (!isUp && propertyName.equalsIgnoreCase("SHUTDOWN")){
            System.out.println("Can't stop the Server if it's not  started running...");
//            return;
        }

        else if(propertyName.equalsIgnoreCase("START")){
            isUp = true;

            new Thread(this).start();

            System.out.println("Server started running...");
        }
        else if(propertyName.equalsIgnoreCase("SHUTDOWN")) {

            isUp = false;


            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server stopped running...");
        }
        oldProprtyName = propertyName;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Executor executor = Executors.newCachedThreadPool();
        while(isUp) {
            try {
                socket = serverSocket.accept();
                executor.execute(new HandleRequest(socket, new CacheUnitController<>()));
            } catch (IOException e) {
//                e.printStackTrace();
            }
        }
    }

}
