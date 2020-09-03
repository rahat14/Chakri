package com.metacoders.cakri.Models;

public  class qusizModel {

    String qus , op1 , op2 , op3 , op4 , ans ;

    public qusizModel() {
    }

    public qusizModel(String qus, String op1, String op2, String op3, String op4, String ans) {
        this.qus = qus;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
        this.op4 = op4;
        this.ans = ans;
    }

    public String getQus() {
        return qus;
    }

    public void setQus(String qus) {
        this.qus = qus;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getOp4() {
        return op4;
    }

    public void setOp4(String op4) {
        this.op4 = op4;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
