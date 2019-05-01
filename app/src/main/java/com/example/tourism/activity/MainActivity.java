package com.example.tourism.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.activity.manager.ManagerActivity;
import com.example.tourism.activity.user.HomeActivity;
import com.example.tourism.tools.Global;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button userButton;
    private Button touristButton;
    private Button managerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_avtivity);
        Bmob.initialize(this, "206b6ec8f0b88827852e2abc8368b5b7");

        initData();

        userButton = (Button) findViewById(R.id.user);
        touristButton = (Button) findViewById(R.id.tourist);
        managerButton = (Button) findViewById(R.id.manager);

        userButton.setOnClickListener(this);
        touristButton.setOnClickListener(this);
        managerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user:
                Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                intent1.putExtra("isManager", false);
                startActivity(intent1);
                break;
            case R.id.tourist:
                Intent intent2 = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent2);
                break;
            case R.id.manager:
                Intent intent3 = new Intent(MainActivity.this, LoginActivity.class);
                intent3.putExtra("isManager", true);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    private void initData() {
        if (Global.isLogin) {
            if (Global.isManager) {
                Intent intent = new Intent(MainActivity.this, ManagerActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

        }
    }
}
