package com.example.tourism.tools;

public class Apprise {

    private String imgUrl;
    private String userName;
    private String userApprise;
    private int seeNumber;

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
