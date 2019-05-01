package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Team extends BmobObject {
    private String title;
    private String cover_url;
    private String hotel;
    private int money;
    private String word;

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
}
