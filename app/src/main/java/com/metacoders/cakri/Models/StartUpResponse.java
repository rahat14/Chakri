package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public  class StartUpResponse implements Serializable {
    @SerializedName("circular")
    @Expose
    private List<JobCircularReponseModel.Job_Circular_Model> circular = null;
    @SerializedName("preparations")
    @Expose
    private List<JobCircularReponseModel.Job_Circular_Model> preparations = null;

    public List<JobCircularReponseModel.Job_Circular_Model> getCircular() {
        return circular;
    }

    public void setCircular(List<JobCircularReponseModel.Job_Circular_Model> circular) {
        this.circular = circular;
    }

    public List<JobCircularReponseModel.Job_Circular_Model> getPreparations() {
        return preparations;
    }

    public void setPreparations(List<JobCircularReponseModel.Job_Circular_Model> preparations) {
        this.preparations = preparations;
    }
}
