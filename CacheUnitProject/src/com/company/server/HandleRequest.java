package com.company.server;

import com.company.dm.DataModel;
import com.company.services.CacheUnitController;
import com.company.services.CacheUnitService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class HandleRequest<T> implements Runnable {

    private Socket socket;
    private CacheUnitController<T> controller;

    CacheUnitService<T> service;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    public HandleRequest(Socket s, CacheUnitController<T> controller) throws IOException {
        this.socket = s;
        this.controller = controller;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        service = new CacheUnitService<>();
    }


    @Override
    public void run() {
        Gson gson = new GsonBuilder().create();

        try {

            String inputString = null;
            inputString = (String) objectInputStream.readObject();

            if (inputString.equalsIgnoreCase("getStatistics")) {

                objectOutputStream.writeObject(service.getStatistics());
                return;
            }

            Type ref = new TypeToken<Request<DataModel<T>[]>>() {}.getType();

            Request<DataModel<T>[]> request = gson.fromJson(inputString, ref);

            Map header = request.getHeaders();
            Boolean success=false;
            String action = header.get("action").toString().toUpperCase().trim();
            switch (action) {
                case "UPDATE":
                    success=controller.update(request.getBody());
                    String bool;
                    if(success){
                        bool="We have updated your request successfully";
                    }
                    else {
                        bool="Sorry, we were not able to update your request";
                    }
                    objectOutputStream.writeObject(bool);
                    break;
                case "GET":
                    DataModel<T>[] dataModels = new DataModel[request.getBody().length];
                    dataModels = controller.get(request.getBody());
                            //controller.get(request.getBody());
//                    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
//                    writer.writeUTF(gson.toJson(1111));
//                    writer.flush();
                    System.out.println(dataModels);
//                    objectOutputStream.writeObject(gson.toJson(dataModel.toString()));
//                    objectOutputStream.flush();
                break;
                case "DELETE":
                    success = controller.delete(request.getBody());
                    if(success){
                        bool="We have deleted your request successfully";
                    }
                    else {
                        bool="Sorry, we were not able to delete your request";
                    }
                    objectOutputStream.writeObject(bool);
                    break;

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}