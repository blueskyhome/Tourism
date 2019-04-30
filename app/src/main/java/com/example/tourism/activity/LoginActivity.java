package com.example.tourism.activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyDatabaseHelper;
import com.example.tourism.tools.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private ImageView loginBack;
    private TextView register;
    private Button loginButton;
    private EditText accountText;
    private EditText passwordText;
    private MyDatabaseHelper dbHelper;
    private String password;
    private RadioButton radioButton_is;
    private int is_save = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Global.isManager = getIntent().getBooleanExtra("isManager", false);
        initView();
    }

    private void initView() {
        loginBack = (ImageView) findViewById(R.id.login_back);
        register = (TextView) findViewById(R.id.register);
        loginButton = (Button) findViewById(R.id.login_button);
        accountText = (EditText) findViewById(R.id.login_account);
        passwordText = (EditText) findViewById(R.id.login_password);
        radioButton_is = (RadioButton)findViewById(R.id.btn_is);
        dbHelper = new MyDatabaseHelper(this, "Tourism.db", null, 1);

        loginBack.setOnClickListener(this);
        register.setOnClickListener(this);
        loginButton.setOnClickListener(this);
        radioButton_is.setOnClickListener(this);
        accountText.addTextChangedListener(new EditChangeListener());
        passwordText.addTextChangedListener(new EditChangeListener());

        getData();
        radioButton_is.setChecked(true);
        if (Global.isManager) {
            register.setVisibility(View.GONE);
        }
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
            case R.id.btn_is:
                if(radioButton_is.isChecked()){
                    is_save = 1;
                    radioButton_is.setChecked(false);
                }else{
                    is_save = 2;
                    radioButton_is.setChecked(true);
                }
            default:
                break;
        }
    }

    private void login() {
        if (!Global.isManager) {
            save();
            BmobUser.loginByAccount(accountText.getText().toString(), passwordText.getText().toString(), new LogInListener<User>() {
                @Override
                public void done(User user, BmobException e) {
                    if (e == null) {
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Global.isLogin = true;
                        Global.userName = user.getUsername();
                        Global.nickName = user.getNickName();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            if (Global.managerAccount.equals(accountText.getText().toString()) && Global.managerPassword.equals(passwordText.getText().toString())) {
                Global.isLogin = true;
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "请输入正确的账号密码", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void save(){
        SharedPreferences.Editor editor = getSharedPreferences("account",MODE_PRIVATE).edit();
        editor.putString("accountName",accountText.getText().toString());
        editor.putString("password",passwordText.getText().toString());
        if(is_save == 2){
            editor.putBoolean("isRemember",true);
        }else{
            editor.putBoolean("isRemember",false);
        }
        editor.apply();
    }

    private void getData(){
        SharedPreferences preferences = getSharedPreferences("account",MODE_PRIVATE);
        String accountName = preferences.getString("accountName","");
        String password = preferences.getString("password","");
        accountText.setText(accountName);
        if(preferences.getBoolean("isRemember",false)){
            passwordText.setText(password);
        }
    }

    class EditChangeListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!(TextUtils.isEmpty(accountText.getText().toString()) || TextUtils.isEmpty(passwordText.getText().toString()))) {
                loginButton.setBackgroundResource(R.drawable.pink_button);
            } else {
                loginButton.setBackgroundResource(R.drawable.login_in);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}


