package com.example.tourism.tools;

public class Hotel {
    private String cover_url;
    private String name;
    private String content;
    private int price;

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }
}
