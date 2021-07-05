package com.company.services;

import com.company.dm.DataModel;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CacheUnitController<T> {

    private CacheUnitService<T> service;

    /**
     * constructor
     */
    public CacheUnitController(){
        service = new CacheUnitService<T>();
    }

    /**
     * updates the data models the were sent
     * @param datas to be updated
     * @return true if managed, false otherwise
     * @throws Exception
     */
    public boolean update(DataModel<T>[] datas) throws Exception {
        return service.update(datas);
    }

    /**
     * deletes the data models the were sent
     * @param datas to be deleted
     * @return true if managed, false otherwise
     * @throws Exception
     */
    public boolean delete(DataModel<T>[] datas){
        return service.delete(datas);
    }

    /**
     * returns the data models by the id's that were sent
     * @param datas
     * @throws Exception
     */
    public DataModel<T>[] get(DataModel<T>[] datas) throws Exception {
        return service.get(datas);
    }


}
