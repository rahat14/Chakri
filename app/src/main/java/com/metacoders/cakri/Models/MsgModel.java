package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class MsgModel {
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("error")
    @Expose
    private Boolean error;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }
}