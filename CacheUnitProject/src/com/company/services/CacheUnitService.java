package com.company.services;

import com.company.dao.DaoFileImpl;
import com.company.dao.IDao;
import com.company.dm.DataModel;
import com.company.memory.CacheUnit;
import project1.LRUAlgoCacheImpl;


public class CacheUnitService<T> {

    private IDao<Long, DataModel<T>> hardDisk;
    private CacheUnit<T> cache;
    private static int dataModelsCounter=0;
    private static int requests = 0;
    private static int swaps = 0;
    private String chosenAlgorithm;
    private Integer cacheCapacity;

    /**
     * constructor
     */
    public CacheUnitService(){
        this.chosenAlgorithm = "LRU";
        this.cacheCapacity = 5;
        this.cache = new CacheUnit<T>(new LRUAlgoCacheImpl<Long, DataModel<T>>(this.cacheCapacity));
        this.hardDisk = new DaoFileImpl<>("dataResource");

    }

    /**
     * updateds to cache
     */
    public boolean update(DataModel<T>[] dataModels) throws Exception {
        this.requests++;
        this.dataModelsCounter += dataModels.length;
        DataModel<T>[] tempDM = cache.putDataModels(dataModels);
        for (int i = 0; i < dataModels.length; i++) {
            if (tempDM[i] != null) {
                hardDisk.save(tempDM[i]);
                this.swaps++;
            }
        }

        return true;
    }

    /**
     * deletes from cache
     */
    public boolean delete(DataModel<T>[] dataModels) {
        try {
            this.requests++;
            Long ids[] = new Long[dataModels.length];

            for (int i = 0; i < dataModels.length; i++) {
                hardDisk.delete(dataModels[i]);
            }
            for (int i = 0; i < ids.length; i++) {
                ids[i] = dataModels[i].getDataModelId();
                this.dataModelsCounter++;
            }
            cache.removeDataModels(ids);
        }catch(Exception ex) {
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    public DataModel<T>[] get(DataModel<T>[] dataModels) throws Exception {
        this.requests++;

        Long[] ids = new Long[dataModels.length];
        for (int i = 0; i < dataModels.length; i++) {
            ids[i] = dataModels[i].getDataModelId();
            this.dataModelsCounter++;
        }
        DataModel<T>[] tempDM = new DataModel[dataModels.length];
        tempDM = (DataModel<T>[]) cache.getDataModels(ids);

        for (int i = 0; i < dataModels.length; i++) {
            if (tempDM[i] == null)
                tempDM[i] = (DataModel<T>) hardDisk.find(dataModels[i].getDataModelId());
        }
        return tempDM;
    }

    /**
     * @return vlaue is a string that contains the statistics of the usage of the algorithm
     */
    public String getStatistics(){

        String statistic = "Capacity: "+this.cacheCapacity+"\nAlgorithm: "+this.chosenAlgorithm+"\nTotal number of requests: "
                +requests+"\nTotal number of DataModels (GET/UPDATE/DELETE requests): "+dataModelsCounter+"\nTotal number of DataModel swaps (from cache to disk): "+swaps;
        return statistic;
    }
}
