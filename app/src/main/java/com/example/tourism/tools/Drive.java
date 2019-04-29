package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class TeamTest extends BmobObject {
    private String title;
    private String content;
    private String cover_url;
    private String hotel;
    private Integer money;
    private String objectId;

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

    @Override
    public String getObjectId() {
        return objectId;
    }

    public String getTitle() {
        return title;
    }

    public TeamTest setContent(String content) {
        this.content = content;
        return this;
    }

    public TeamTest setCover_url(String cover_url) {
        this.cover_url = cover_url;
        return this;
    }

    public TeamTest setHotel(String hotel) {
        this.hotel = hotel;
        return this;
    }

    public TeamTest setMoney(Integer money) {
        this.money = money;
        return this;
    }

    @Override
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public TeamTest setTitle(String title) {
        this.title = title;
        return this;
    }
}
