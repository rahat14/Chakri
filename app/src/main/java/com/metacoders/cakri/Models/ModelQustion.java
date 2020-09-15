package com.metacoders.cakri.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class ModelQustion implements Serializable {

    private List<userAnsModel>  userAnsList = new ArrayList<>();
    private  List<qusizModel> qusList = new ArrayList<>() ;

    public ModelQustion(List<userAnsModel> userAnsList, List<qusizModel> qusList) {
        this.userAnsList = userAnsList;
        this.qusList = qusList;
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
