package com.example.tourism.tools.user;

import cn.bmob.v3.BmobObject;

public class Drive extends BmobObject {
    private String title;
    private String cover_url;
    private String hotel;
    private Integer money;
    private String recommend;
    private String type;
    private String word;

    public Integer getMoney() {
        return money;
    }

    public String getRecommend() {
        return recommend;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getHotel() {
        return hotel;
    }

    public String getType() {
        return type;
    }

    public String getWord() {
        return word;
    }

    public String getTitle() {
        return title;
    }

    public Drive setRecommend(String recommend) {
        this.recommend = recommend;
        return this;
    }

    public Drive setCover_url(String cover_url) {
        this.cover_url = cover_url;
        return this;
    }

    public Drive setHotel(String hotel) {
        this.hotel = hotel;
        return this;
    }

    public Drive setMoney(Integer money) {
        this.money = money;
        return this;
    }


    public Drive setTitle(String title) {
        this.title = title;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
