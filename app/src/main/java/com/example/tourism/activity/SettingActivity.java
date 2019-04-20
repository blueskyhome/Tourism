package com.example.tourism.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.example.tourism.R;
import com.example.tourism.tools.Global;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewStub viewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        viewStub = (ViewStub) findViewById(R.id.quit_item);
        if (Global.isLogin) {
            View quitItem = viewStub.inflate();
            View quitView = (View) findViewById(R.id.quit);
            quitView.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.quit:
                viewStub.setVisibility(View.GONE);
                Global.isLogin = false;
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
