package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class qusizModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("qustion_id")
    @Expose
    private String qustionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("option_one")
    @Expose
    private String optionOne;
    @SerializedName("option_three")
    @Expose
    private String optionThree;
    @SerializedName("option_four")
    @Expose
    private String optionFour;
    @SerializedName("right_ans")
    @Expose
    private String rightAns;
    @SerializedName("option_two")
    @Expose
    private String optionTwo;
    @SerializedName("option_five")
    @Expose
    private String optionFive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQustionId() {
        return qustionId;
    }

    public void setQustionId(String qustionId) {
        this.qustionId = qustionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionOne() {
        return optionOne;
    }

    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    public String getOptionThree() {
        return optionThree;
    }

    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    public String getOptionFour() {
        return optionFour;
    }

    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }

    public String getOptionTwo() {
        return optionTwo;
    }

    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    public String getOptionFive() {
        return optionFive;
    }

    public void setOptionFive(String optionFive) {
        this.optionFive = optionFive;
    }
}
