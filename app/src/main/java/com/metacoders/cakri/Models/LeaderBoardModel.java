package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class LeaderBoardModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("qus_id")
    @Expose
    private String qusId;
    @SerializedName("score")
    @Expose
    private String score;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("mark_sheet")
    @Expose
    private String markSheet;
    @SerializedName("user_name")
    @Expose
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQusId() {
        return qusId;
    }

    public void setQusId(String qusId) {
        this.qusId = qusId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMarkSheet() {
        return markSheet;
    }

    public void setMarkSheet(String markSheet) {
        this.markSheet = markSheet;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
