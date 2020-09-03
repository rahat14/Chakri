package com.metacoders.cakri.Models;

public  class userAnsModel {

    int pos  ;
    double mark ;
    String selectedAns ;
    int  choosePos ;

    public userAnsModel() {
    }

    public userAnsModel(int pos, double mark, String selectedAns, int choosePos) {
        this.pos = pos;
        this.mark = mark;
        this.selectedAns = selectedAns;
        this.choosePos = choosePos;
    }

    public int getChoosePos() {
        return choosePos;
    }

    public void setChoosePos(int choosePos) {
        this.choosePos = choosePos;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getSelectedAns() {
        return selectedAns;
    }

    public void setSelectedAns(String selectedAns) {
        this.selectedAns = selectedAns;
    }
}
