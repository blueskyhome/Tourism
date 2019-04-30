package com.example.tourism.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.adapter.QuestionAdapter;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.Question;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private ImageView askQuestion;
    private QuestionAdapter adapter;
    private RecyclerView recyclerView;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Question> bmobQuery = new BmobQuery<Question>();
                bmobQuery.addWhereEqualTo("thing_name", name);
                bmobQuery.findObjects(new FindListener<Question>() {
                    @Override
                    public void done(List<Question> list, BmobException e) {
                        if (e == null) {
//                            Toast.makeText(QuestionActivity.this , list.get(0).getQuestion(), Toast.LENGTH_LONG).show();
                            adapter = new QuestionAdapter(list, QuestionActivity.this, handler);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Toast.makeText(QuestionActivity.this , e.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView() {

        back = (ImageView) findViewById(R.id.back);
        askQuestion = (ImageView) findViewById(R.id.ask_question);
        recyclerView = (RecyclerView) findViewById(R.id.question_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);

        back.setOnClickListener(this);
        askQuestion.setOnClickListener(this);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.ask_question:
                ask();
                break;
            default:
                break;
        }
    }

    private void ask() {
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(QuestionActivity.this);
        final View dialogView = LayoutInflater.from(QuestionActivity.this)
                .inflate(R.layout.dialog_view,null);
        customizeDialog.setTitle("提问题");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EditText edit_text =
                                        (EditText) dialogView.findViewById(R.id.edit_text);
                                Question question = new Question();
                                question.setThing_name(name);
                                question.setQuestion(edit_text.getText().toString());
                                question.setAnswer(" ");
                                question.setType("ticket");
                                question.setIsAnswer(false);
                                question.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            initData();
                                            adapter.notifyDataSetChanged();
                                        } else {
                                            Toast.makeText(QuestionActivity.this , e.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }
                });
        customizeDialog.show();
    }
}
