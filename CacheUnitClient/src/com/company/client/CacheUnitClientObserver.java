package com.company.client;

import com.company.view.CacheUnitView;

public class CacheUnitClientObserver implements java.beans.PropertyChangeListener{

    public CacheUnitView view;
    private CacheUnitClient client;
    public CacheUnitClientObserver(){
        client = new CacheUnitClient();
    }

    public void propertyChange(java.beans.PropertyChangeEvent evt){
        String propertyName = (String)evt.getNewValue();
        view = (CacheUnitView) evt.getSource();
        String content = client.send(propertyName);
        view.updateUIData(content);

    }

}