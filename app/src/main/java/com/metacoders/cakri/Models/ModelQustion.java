package com.metacoders.cakri.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class ModelQustion implements Serializable {

    private List<userAnsModel>  userAnsList = new ArrayList<>();
    private  List<qusizModel> qusList = new ArrayList<>() ;
    private  int id  ;
    private  int qusType  ;


    public ModelQustion(List<userAnsModel> userAnsList, List<qusizModel> qusList, int id, int qusType) {
        this.userAnsList = userAnsList;
        this.qusList = qusList;
        this.id = id;
        this.qusType = qusType;
    }

    public int getQusType() {
        return qusType;
    }

    public void setQusType(int qusType) {
        this.qusType = qusType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<userAnsModel> getUserAnsList() {
        return userAnsList;
    }

    public void setUserAnsList(List<userAnsModel> userAnsList) {
        this.userAnsList = userAnsList;
    }

    public List<qusizModel> getQusList() {
        return qusList;
    }

    public void setQusList(List<qusizModel> qusList) {
        this.qusList = qusList;
    }
}
