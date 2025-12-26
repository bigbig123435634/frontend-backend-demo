package com.example.demo.model;

import lombok.Data;

@Data
public class AResponse {

    private boolean isok;
    private int code;
    private String message;
    private Object data;

    public void setIsok(boolean isok){
        this.isok = isok;
    }

    public void setCode(int code){
        this.code = code;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setData(Object data){
        this.data = data;
    }


    public static AResponse success(){
        AResponse aResponse = new AResponse();
        aResponse.setIsok(true);
        aResponse.setCode(200);
        aResponse.setMessage("success");
        aResponse.setData(null);

        return aResponse;
    }
    
    public static AResponse success(Object data){

        AResponse aResponse = new AResponse();
        aResponse.setIsok(true);
        aResponse.setCode(200);
        aResponse.setMessage("success");
        aResponse.setData(data);

        return aResponse;
    }
    public static AResponse success(Object data, String message){

        AResponse aResponse = new AResponse();
        aResponse.setIsok(true);
        aResponse.setCode(200);
        aResponse.setMessage(message);
        aResponse.setData(data);

        return aResponse;
    }

    public static AResponse error(Object data, String message){
        AResponse aResponse = new AResponse();
        aResponse.setIsok(false);
        aResponse.setCode(404);
        aResponse.setMessage(message);
        aResponse.setData(data);

        return aResponse;
    }
}


