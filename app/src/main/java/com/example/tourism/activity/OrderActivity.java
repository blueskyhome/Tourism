package com.example.tourism.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.tourism.R;
import com.example.tourism.adapter.OrderAdapter;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OrderActivity extends AppCompatActivity {

    private ImageView back;
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private List<Order> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);

        initData();
        initView();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Order> bmobQuery = new BmobQuery<Order>();
                bmobQuery.addWhereEqualTo("user", Global.userName);
                bmobQuery.findObjects(new FindListener<Order>() {
                    @Override
                    public void done(List<Order> list, BmobException e) {
                        if (e == null) {
                            adapter = new OrderAdapter(list, handler);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        }).start();

    }

    private void initView() {
        back = (ImageView) findViewById(R.id.back);

        recyclerView = (RecyclerView) findViewById(R.id.order_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initData();
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
}