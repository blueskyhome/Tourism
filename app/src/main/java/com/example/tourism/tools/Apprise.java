package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Apprise extends BmobObject {

    private String name;
    private String imgUrl;
    private String userName;
    private String userApprise;
    private int seeNumber;

    public void setName(String name) {
        this.name = name;
    }

    public void setImgId(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserApprise(String userApprise) {
        this.userApprise = userApprise;
    }

    public void setSeeNumber(int seeNumber) {
        this.seeNumber = seeNumber;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserApprise() {
        return userApprise;
    }

    public int getSeeNumber() {
        return seeNumber;
    }
}
