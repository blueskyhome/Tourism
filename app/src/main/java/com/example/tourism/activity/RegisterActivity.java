package com.example.tourism.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.tools.MyDatabaseHelper;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private ImageView registerBack;
    private Button registerButton;
    private EditText accountText;
    private EditText passwordText;
    private EditText checkPasswordText;
    private EditText nameText;
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
        nameText = (EditText) findViewById(R.id.name);
        checkPasswordText = (EditText) findViewById(R.id.check_password);
        dbHelper = new MyDatabaseHelper(this, "Tourism.db", null, 1);

        registerBack.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        accountText.addTextChangedListener(new EditChangeListener());
        passwordText.addTextChangedListener(new EditChangeListener());
        checkPasswordText.addTextChangedListener(new EditChangeListener());
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
        if (!(TextUtils.isEmpty(accountText.getText().toString()) || TextUtils.isEmpty(passwordText.getText().toString()) || TextUtils.isEmpty(checkPasswordText.getText()))) {
            if(passwordText.getText().toString().equals(checkPasswordText.getText().toString())) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("account", accountText.getText().toString());
                values.put("password", passwordText.getText().toString());
                values.put("name", nameText.getText().toString());
                db.insert("Account", null, values);
                finish();
            } else {
                Toast.makeText(this, "密码确认错误", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请正确输入", Toast.LENGTH_SHORT).show();
        }

    }

    class EditChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!(TextUtils.isEmpty(accountText.getText().toString()) || TextUtils.isEmpty(passwordText.getText().toString()) || TextUtils.isEmpty(checkPasswordText.getText()))) {
                registerButton.setBackgroundResource(R.drawable.pink_button);
            } else {
                registerButton.setBackgroundResource(R.drawable.login_in);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
