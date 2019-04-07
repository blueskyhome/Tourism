package com.example.tourism.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.tools.MyDatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView loginBack;
    private TextView register;
    private Button loginButton;
    private EditText accountText;
    private EditText passwordText;
    private MyDatabaseHelper dbHelper;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initView();
    }

    private void initView() {
        loginBack = (ImageView) findViewById(R.id.login_back);
        register = (TextView) findViewById(R.id.register);
        loginButton = (Button) findViewById(R.id.login_button);
        accountText = (EditText) findViewById(R.id.login_account);
        passwordText = (EditText) findViewById(R.id.login_password);
        dbHelper = new MyDatabaseHelper(this, "Tourism.db", null, 1);

        loginBack.setOnClickListener(this);
        register.setOnClickListener(this);
        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_back:
                finish();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                if (!(TextUtils.isEmpty(accountText.getText()) && !(TextUtils.isEmpty(passwordText.getText())))) {
                    login();
                }
                break;
            default:
                break;
        }
    }

    private void login() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Account", new String[] {"account, password"}, "account=?", new String[]{accountText.toString()}, null, null, null);
        while (cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex("password"));
            if (password.equals(passwordText.toString())) {
                finish();
            } else {
                Toast.makeText(this, "没有", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
