package com.example.tourism.tools;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }
}
