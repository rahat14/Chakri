package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class SearchResponse implements Serializable {
    @SerializedName("circular")
    @Expose
    public List<JobCircularReponseModel.Job_Circular_Model> circular = new ArrayList<>();
    @SerializedName("prep")
    @Expose
    public List<JobCircularReponseModel.Job_Circular_Model> prep= new ArrayList<>();

    public List<JobCircularReponseModel.Job_Circular_Model> getCircular() {
        return circular;
    }

    public void setCircular(List<JobCircularReponseModel.Job_Circular_Model> circular) {
        this.circular = circular;
    }

    public List<JobCircularReponseModel.Job_Circular_Model> getPrep() {
        return prep;
    }

    public void setPrep(List<JobCircularReponseModel.Job_Circular_Model> prep) {
        this.prep = prep;
    }
}
