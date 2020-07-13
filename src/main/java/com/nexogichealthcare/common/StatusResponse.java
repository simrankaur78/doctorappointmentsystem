package com.nexogichealthcare.common;

public enum StatusResponse {
    SUCCESS("Success"),
    ERROR("Error");
    
    private String status;
    
    public String getStatus() {
        return status;
    }
    
    private StatusResponse(String status) {
        this.status = status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
