package com.example.tourism.tools;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

public class Ticket extends BmobObject implements Parcelable{
    private String img_url;
    private String name;
    private String content;
    //写错懒得改了
    private int price;



    public String getContent() {
        return content;
    }

    public int getPrice() {
        return price;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getName() {
        return name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(img_url);
        dest.writeString(content);
        dest.writeInt(price);
    }

    public static final Parcelable.Creator<Ticket> CREATOR = new Parcelable.Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel source) {
            Ticket ticket = new Ticket();
            ticket.name = source.readString();
            ticket.img_url = source.readString();
            ticket.content = source.readString();
            ticket.price = source.readInt();
            return ticket;
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };
}
