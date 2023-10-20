package com.grapplermodule1.GrapplerEnhancement.customxception;

import org.springframework.http.HttpStatus;

public class CustomResponse <T>{
    private boolean status;
    private String message;
    private  T data;

    public CustomResponse() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getT() {
        return data;
    }

    public void setT(T t) {
        this.data = t;
    }

    public CustomResponse(Boolean status, String message, T t) {
        this.status = status;
        this.message = message;
        this.data = t;
    }

}
