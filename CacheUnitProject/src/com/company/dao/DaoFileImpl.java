package com.company.dao;


import com.company.dm.DataModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
//import com.mby.dao.IDao;
//import com.mby.dm.DataModel;

import java.io.*;
import java.lang.Object;
import java.lang.Long;
import java.lang.String;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.company.dm.DataModel;
import com.google.gson.Gson;

import java.awt.font.TextHitInfo;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DaoFileImpl<T> implements IDao<java.lang.Long, DataModel<T>> {
    private java.lang.String filePath;
    private int capacity;
    private HashMap<Long, DataModel<T>> map;
    //private ObjectOutputStream outputStream;


    /**
     * constructor
     * @param filePath of file
     * @param capacity of map
     */
    public DaoFileImpl(java.lang.String filePath, int capacity){
        this.filePath = new File("src/com/company/resources/"+filePath).getAbsolutePath();
        this.capacity = capacity;
        map = new HashMap<Long, DataModel<T>>(capacity);
    }

    /**
     * constructor
     * @param filePath of file
     */
    public DaoFileImpl(java.lang.String filePath){
        this.filePath = new File("src/com/company/resources/"+filePath).getAbsolutePath();
        map = new HashMap<Long, DataModel<T>>();
    }

    /**
     * updates the file with new info
     //* @param data to be inserted to file
     * @throws FileNotFoundException because we are assuming that the file exists
     */
    public void updateFile() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(filePath));
            stream.writeObject(map);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        File file = new File(filePath);
//        try (FileWriter fileWriter = new FileWriter(file)) {
//            Gson gson = new Gson();
//            String jsonEntity = gson.toJson(map);
//            fileWriter.write(jsonEntity);
//        } catch (FileNotFoundException exception) {
//            try {
//                throw exception;
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
    }

    /**
     * reads the file
     * @throws FileNotFoundException because we are assuming that the file exists
     */
    public void readFile() throws FileNotFoundException {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(filePath));
            map = (HashMap<Long, DataModel<T>>)stream.readObject();
            stream.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Type arrayType = new TypeToken<HashMap<Long, DataModel<T>>>() {
//        }.getType();
//        File file = new File(filePath);
//        try (FileReader fileReader = new FileReader(file)) {
//            Gson gson = new Gson();
//            HashMap<Long, DataModel<T>> fileContent = gson.fromJson(fileReader, arrayType);
//            if (fileContent != null) {
//                map = fileContent;
//            }
//        } catch (FileNotFoundException exception) {
//            throw exception;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * save data
     * @param data to be saved
     */
    @Override
    public void save(DataModel<T> data) throws FileNotFoundException {
        readFile();
        if(map.get(data.getDataModelId()) == null) {
            map.put(data.getDataModelId(), data);
            updateFile();
        }

    }

    /**
     * deletes data
     * @param data to be deleted
     */
    @Override
    public void delete(DataModel<T> data) throws FileNotFoundException {
        readFile();
        if(map.get(data.getDataModelId()) != null) {
            map.remove(data.getDataModelId());
            updateFile();
        }
    }

    /**
     * finds data model by id
     * @param id to be found
     * @return the data model that was found
     */
    @Override
    public DataModel<T> find(java.lang.Long id) {
        try {
            readFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(map);
        if (map==null )return null;
        return map.get(id) ;
    }

}



//
////package com.mby.dao;
//
//
///**
// *this class write and read pages from the Hard Disk
// * the class contains a map of the pages in the hard disk
// * @param <T>
// */
//public class DaoFileImpl<T> implements IDao<java.lang.Long, DataModel<T>> {
//    private final String filePath;
//    private int capacity;
//    private ObjectOutputStream objectInFile;
//    private HashMap<Long, DataModel<T>> map;
//
//    /**
//     *
//     * @param filePath the path of the file that do as if its the Hard Disk
//     */
//    public DaoFileImpl(String filePath){
//        this.filePath=new File("src/com/company/resources/"+filePath).getAbsolutePath();
//        this.capacity=500;
//        map=new HashMap<Long, DataModel<T>>(this.capacity);
//    }
//
//    /**
//     *
//     * @param filePath the path of the file that do as if its the Hard Disk
//     * @param capacity the amount of the pages that could be in the Hard Disk
//     */
//    public  DaoFileImpl(java.lang.String filePath, int capacity){
//        this.filePath=new File("src/com/company/resources/"+filePath).getAbsolutePath();
//        this.capacity=capacity;
//        map=new HashMap<Long, DataModel<T>>(this.capacity);
//    }
//
//    /**
//     * this function read all of the file and put it in the map of the class
//     * @throws FileNotFoundException f the reading  to the Hard Disk (file) with fails
//     */
//
//    private void read() throws FileNotFoundException{
//        Type arrayType = new TypeToken<HashMap<Long, DataModel<T>>>() {
//        }.getType();
//        File file = new File(filePath);
//        try (FileReader fileReader = new FileReader(file)) {
//            Gson gson = new Gson();
//            HashMap<Long, DataModel<T>> fileContent = gson.fromJson(fileReader, arrayType);
//            if (fileContent != null) {
//                map = fileContent;
//            }
//        } catch (FileNotFoundException exception) {
//            throw exception;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * this function writes all of the map in to the file
//     * @throws Exception
//     */
//    private void write() throws Exception{
//        File file = new File(filePath);
//        try (FileWriter fileWriter = new FileWriter(file)) {
//            Gson gson = new Gson();
//            String jsonEntity = gson.toJson(map);
//            fileWriter.write(jsonEntity);
//        } catch (FileNotFoundException exception) {
//            throw exception;
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//    }
//
//    /**
//     * the function gets a page and delete it from the hard disk
//     * @param entity the page to delete from the Hard Disk
//     * @throws Exception
//     */
//    @Override
//    public void delete(DataModel<T> entity) throws Exception {
//        read();
//        if(entity!=null){
//            map.remove(entity.getDataModelId());
//        }
//        write();
//    }
//
//    /**
//     * the function gets a id of a page and return the value of the page
//     * @param id the id of the page that you want the page
//     * @return
//     * @throws FileNotFoundException
//     */
//    @Override
//    public DataModel<T> find(Long id) throws FileNotFoundException {
//        read();
//        if(map==null){
//            return null;
//        }
//        return map.get(id);
//    }
//
//    /**
//     * the function gets a page and saves it in the hard disk
//     * @param entity the page to save from the Hard Disk
//     * @throws Exception
//     */
//    @Override
//    public void save(DataModel<T> entity) throws Exception {
//        read();
//        if(entity!=null){
//            if(entity!=find(entity.getDataModelId())){
//                map.put(entity.getDataModelId(),entity);
//            }
//        }
//        write();
//    }
//
//    /**
//     * the function clean the hard disk (file)
//     * @throws Exception
//     */
//    public void clearHradDisk() throws Exception {
//        map.clear();
//        write();
//    }
//
//}
//
//
//
