package com.example.day09.data.model.register;

import com.google.gson.annotations.SerializedName;

public class Register{

    @SerializedName("data")
    private RegsisterData regsisterData;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private boolean status;

    public void setData(RegsisterData regsisterData){
        this.regsisterData = regsisterData;
    }

    public RegsisterData getData(){
        return regsisterData;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isStatus(){
        return status;
    }
}