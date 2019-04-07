package com.example.tourism.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tourism.R;
import com.example.tourism.tools.MyDatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView registerBack;
    private Button registerButton;
    private EditText accountText;
    private EditText passwordText;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        initView();
    }

    private void initView() {
        registerBack = (ImageView) findViewById(R.id.register_back);
        registerButton = (Button) findViewById(R.id.register_button);
        accountText = (EditText) findViewById(R.id.register_account);
        passwordText = (EditText) findViewById(R.id.register_password);
        dbHelper = new MyDatabaseHelper(this, "Tourism.db", null, 1);
        registerBack.setOnClickListener(this);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_button:
                if (!(TextUtils.isEmpty(accountText.getText()) && !(TextUtils.isEmpty(passwordText.getText())))) {
                    register();
                }
                break;
            default:
                break;
        }
    }

    private void register() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("account", accountText.toString());
        values.put("password", passwordText.toString());
        db.insert("Account", null, values);
        finish();
    }
}
