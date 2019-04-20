/**
 * 自驾游Item的数据List
 */
package com.example.tourism.tools;

public class SelfDrive {
    private String title;
    private String cover_url;
    private String houseText;
    private String spot;
    private int money;

    public int getMoney() {
        return money;
    }

    public String getCover_url() {
        return cover_url;
    }

    public String getHouseText() {
        return houseText;
    }

    public String getTitle() {
        return title;
    }

    public String getSpot() {
        return spot;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public void setHouseText(String houseText) {
        this.houseText = houseText;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
