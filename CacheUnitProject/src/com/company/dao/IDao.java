package com.company.dao;

import java.io.FileNotFoundException;

public interface IDao <ID extends java.io.Serializable,T>{

    public void save(T entity) throws FileNotFoundException, Exception;

    public void delete(T entity) throws Exception;

    public T find(ID id) throws FileNotFoundException;

}
