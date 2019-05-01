package com.example.tourism.tools.user;

public class Team {
    private String cover_url;
    private String title;
    private String content;
    private String hotelContent;
    private int money;

    public String getTitle() {
        return title;
    }

    public int getMoney() {
        return money;
    }

    public String getContent() {
        return content;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getHotelContent() {
        return hotelContent;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setHotelContent(String hotelContent) {
        this.hotelContent = hotelContent;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
