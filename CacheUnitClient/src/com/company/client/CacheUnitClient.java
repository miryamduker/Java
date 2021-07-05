package com.company.client;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

public class CacheUnitClient {

    public CacheUnitClient(){

    }

    public java.lang.String send(java.lang.String request) {

        ObjectOutputStream output;
        ObjectInputStream input;
        String content = null;
        try {
            Socket myServer = new Socket("127.0.0.1", 12345);
            output = new ObjectOutputStream(myServer.getOutputStream());
            output.writeObject(request);
            output.flush();
            input = new ObjectInputStream(myServer.getInputStream());
            content = (String) input.readObject();
//            System.out.println("dddd");
            output.close();
            input.close();
            myServer.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return content;


    }
}
