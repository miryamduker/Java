package com.company.server;

import java.io.Serializable;
import java.util.Map;

public class Request<T> implements Serializable {

    private Map<String, String> headers;
    private T body;

    /**
     * constructor
     * @param headers of request
     * @param body of request
     */
    public Request(Map<String, String> headers, T body){
        this.headers = headers;
        this.body = body;
    }

    /**
     * @return value is the headers
     */
    public Map<String,String> getHeaders(){

        return headers;
    }

    /**
     * sets the headers
     * @param headers to be set
     */
    public void setHeaders(Map<String,String> headers){

        this.headers = headers;
    }

    /**
     * @return value is the body
     */
    public T getBody() {

        return body;
    }

    /**
     * sets the body
     * @param body to be set
     */
    public void setBody(T body){

        this.body = body;
    }

    /**
     * @return value is the request as a string
     */
    @Override
    public String toString(){

        return "headers: " + this.headers + " body: " + this.body ;
    }

}
