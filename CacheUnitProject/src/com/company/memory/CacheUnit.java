package com.company.memory;

import com.company.dm.DataModel;
import project1.IAlgoCache;

public class CacheUnit <T>{

    private IAlgoCache<Long, DataModel<T>> algoType;

    /**
     * constructor
     * @param algo is the type of algorithm that should be used (e.g. LRU)
     */
    public CacheUnit(IAlgoCache<Long, DataModel<T>> algo){

        this.algoType = algo;
    }

    /**
     * returns the data models of ids that where sent
     * @param ids of which we are going to return the data models for
     * @return the data models
     */
    public DataModel<T>[] getDataModels(java.lang.Long[] ids){
        DataModel<T>[] dataModels = new DataModel[ids.length];
        for(int i = 0; i< ids.length; i++){
            dataModels[i] = algoType.getElement(ids[i]);
        }
        return dataModels;
    }

    /**
     *inserts data models to algoType
     * @param datamodels that will be inserted
     * @return the array of data models that was inserted
     */
    public DataModel<T>[] putDataModels(DataModel<T>[] datamodels){
        DataModel<T>[] dm = new DataModel[datamodels.length];
        for (int i=0; i< datamodels.length; i++){
            dm[i]= algoType.putElement(datamodels[i].getDataModelId(),datamodels[i]);
        }
        return dm;
    }

    /**
     * removes the data models from algoType that match the ids that were received
     * @param ids of the data models that will be removed
     */
    public void removeDataModels(java.lang.Long[] ids){
        for (Long id : ids) {
            algoType.removeElement(id);
        }
    }

}
