package com.example.tourism.tools;

import cn.bmob.v3.BmobObject;

public class Order extends BmobObject {
    private String user;             //用户
    private String thing_name;       //商品名
    private int thing_number;        //数量
    private Boolean is_examine;      //是否审核
    private int money;
    private int single_money;

    public void setUser(String user) {
        this.user = user;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public void setThing_number(int thing_number) {
        this.thing_number = thing_number;
    }

    public void setIs_examine(Boolean is_examine) {
        this.is_examine = is_examine;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSingle_money(int single_money) {
        this.single_money = single_money;
    }

    public String getUser() {
        return user;
    }

    public String getThing_name() {
        return thing_name;
    }

    public int getThing_number() {
        return thing_number;
    }

    public Boolean getIs_examine() {
        return is_examine;
    }

    public int getSingle_money() {
        return single_money;
    }

    public int getMoney() {
        return money;
    }
}
