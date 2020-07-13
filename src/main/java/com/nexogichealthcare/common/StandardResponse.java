package com.nexogichealthcare.common;

import com.google.gson.JsonObject;

public class StandardResponse {
    private StatusResponse status;
    private String message;
    private JsonObject data;
    
    public StandardResponse(StatusResponse status) {
        this.status = status;
    }
    
    public StandardResponse(StatusResponse status, String message) {
        this.status = status;
        this.message =  message;
    }
    public StandardResponse(StatusResponse status,String message, JsonObject data) {
        this.status = status;
        this.message =  message;
        this.data = data;
    }
    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }

    
    
}
