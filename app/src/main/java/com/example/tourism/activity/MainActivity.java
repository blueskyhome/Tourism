package com.example.tourism.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tourism.R;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private Button userButton;
    private Button touristButton;
    private Button managerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_avtivity);

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
}
