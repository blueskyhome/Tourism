package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Hotel extends BmobObject{
    private String img_url;
    private String name;
    private String content;
    private int price;

    public void setImg_url(String img_url) {
        this.img_url = img_url;
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

    public String getImg_url() {
        return img_url;
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
