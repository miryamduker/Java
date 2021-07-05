package com.company.dm;

import java.io.Serializable;

public class DataModel <T> implements Serializable {

    private java.lang.Long id;
    private T content;

    /**
     * constructor
     * @param id of new element
     * @param content of new element
     */
    public DataModel(java.lang.Long id, T content){
        this.id = id;
        this.content = content;
    }

    /**
     * creates a hashcode based on the ids hashcode and contents hashcode
     * @return the hash code
     */
    @Override
    public int hashCode(){

        return id.hashCode()+content.hashCode();
    }

    /**
     * compeers this object to object sent according to the objects hash codes
     * @param obj to be compared
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(java.lang.Object obj){
        if (obj == null )
            return false;
        if(obj.hashCode() == this.hashCode()){
            return true;
        }
        return false;
    }

    /**
     * @return a string with the objects info
     */
    @Override
    public java.lang.String toString(){

        return "Id: " + this.id + " Content: " + this.content;
    }

    /**
     * @return the objects id
     */
    public java.lang.Long getDataModelId(){

        return this.id;
    }

    /**
     * sets the objects id
     * @param id to be set
     */
    public void setDataModelId(java.lang.Long id){

        this.id = id;
    }

    /**
     * @return the objects content
     */
    public T getContent(){

        return this.content;
    }

    /**
     * sets the objects content
     * @param content to be set
     */
    public void setContent(T content){

        this.content = content;
    }
}
