package com.metacoders.cakri.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Response_login {

    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("details")
    @Expose
    private List<UserModel> details = null;

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

    public List<UserModel> getDetails() {
        return details;
    }

    public void setDetails(List<UserModel> details) {
        this.details = details;
    }

    public  class  UserModel{
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("adress")
        @Expose
        private String adress;
        @SerializedName("is_verified")
        @Expose
        private String isVerified;
        @SerializedName("is_ban")
        @Expose
        private String isBan;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("user_name")
        @Expose
        private String userName;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("email")
        @Expose
        private String email;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(String isVerified) {
            this.isVerified = isVerified;
        }

        public String getIsBan() {
            return isBan;
        }

        public void setIsBan(String isBan) {
            this.isBan = isBan;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    }
    }

