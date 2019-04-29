package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Team  extends BmobObject{
    private String cover_url;
    private String title;
    private String content;
    private String hotel;
    private Integer money;

    public String getTitle() {
        return title;
    }

    public Integer getMoney() {
        return money;
    }

    public String getContent() {
        return content;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getHotel() {
        return hotel;
    }

    public Team setMoney(Integer money) {
        this.money = money;
        return this;
    }


    public Team setTitle(String title) {
        this.title = title;
        return this;
    }

    public Team setContent(String content) {
        this.content = content;
        return this;
    }

    public Team setCover_url(String cover_url) {
        this.cover_url = cover_url;
        return this;
    }

    public Team setHotel(String hotel) {
        this.hotel = hotel;
        return this;
    }

}
