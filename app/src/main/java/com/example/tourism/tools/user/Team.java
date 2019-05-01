package com.example.tourism.tools.user;

import cn.bmob.v3.BmobObject;

public class Team extends BmobObject {
    private String title;
    private String cover_url;
    private String hotel;
    private int money;
    private String word;
    private String type;

    public String getTitle() {
        return title;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getHotel() {
        return hotel;
    }

    public String getWord() {
        return word;
    }

    public int getMoney() {
        return money;
    }

    public String getType() {
        return type;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setType(String type) {
        this.type = type;
    }
}
