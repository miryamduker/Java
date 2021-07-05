package com.company;

import com.company.dao.DaoFileImpl;
import com.company.dm.DataModel;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        DataModel<String> oo = new DataModel<String>(433L, "aaa");
        DataModel<String> oo1 = new DataModel<String>(420L, "bbb");


        DaoFileImpl<String> obj = new DaoFileImpl("dataResource",5);
        obj.save(oo);
        obj.save(oo);
        obj.save(oo1);


        System.out.println(obj.find(433L));
        System.out.println(obj.find(420L));
    }
}
