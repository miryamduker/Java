package com.company.driver;

import com.company.client.CacheUnitClient;
import com.company.client.CacheUnitClientObserver;
import com.company.view.CacheUnitView;

public class CacheUnitClientDriver {

    public CacheUnitClientDriver(){

    }
    public static void main(java.lang.String[] args){
        CacheUnitClientObserver cacheUnitClientObserver = new CacheUnitClientObserver();
        CacheUnitView view = new CacheUnitView();
        view.addPropertyChangeListener(cacheUnitClientObserver);
        view.start();
    }

}
