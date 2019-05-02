package com.example.tourism.activity.user;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.adapter.user.AppraseAdapter;
import com.example.tourism.adapter.user.CommentAdapter;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.Apprise;
import com.example.tourism.tools.user.Drive;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CommentActivity extends AppCompatActivity {
    @BindView(R.id.recycler_comment)
    RecyclerView recyclerView_comment;
    @BindView(R.id.comment_back)
    ImageView comment_back;
    @BindView(R.id.not_comment)
    TextView not_comment;
    private CommentAdapter adapter;
    private List<Apprise> appriseList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initView();
        initData();
    }
    public void initView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView_comment.setLayoutManager(layoutManager);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                if(appriseList.size() == 0){
                   not_comment.setVisibility(View.VISIBLE);
                }else{
                    adapter = new CommentAdapter(appriseList);
                    recyclerView_comment.setAdapter(adapter);
                }
            }
        }
    };
    public void initData(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                BmobQuery<Apprise> appriseQuery = new BmobQuery<>();
                appriseQuery.addWhereEqualTo("userName", Global.nickName);
                appriseQuery.findObjects(new FindListener<Apprise>() {
                    @Override
                    public void done(List<Apprise> list, BmobException e) {
                        appriseList = list;
                        handler.sendEmptyMessage(1);
                    }
                });
            }
        }).start();
    }
    @OnClick(R.id.comment_back)
    public void onClick(){
        finish();
    }
}
