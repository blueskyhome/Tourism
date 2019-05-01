package com.example.tourism.activity.user;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.adapter.user.OrderAdapter;
import com.example.tourism.tools.user.Apprise;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.Order;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

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
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initData();
                    adapter.notifyDataSetChanged();
                    AlertDialog.Builder dialog =
                            new AlertDialog.Builder(OrderActivity.this);
                    final View dialogView = LayoutInflater.from(OrderActivity.this)
                            .inflate(R.layout.dialog_view,null);
                    dialog.setTitle("评价");
                    dialog.setView(dialogView);
                    dialog.setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            EditText edit_text =
                                                    (EditText) dialogView.findViewById(R.id.edit_text);
                                            Apprise apprise = new Apprise();

                                            apprise.setName(Global.orderName);
                                            apprise.setImgId("https://ws1.sinaimg.cn/large/0077HGE3ly1g2kz1uyx7tj305k05kglh.jpg");
                                            apprise.setUserName(Global.userName);
                                            apprise.setUserApprise(edit_text.getText().toString());
                                            apprise.setSeeNumber(0);
                                            apprise.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {
                                                    if ( e == null ) {
                                                        Toast.makeText(OrderActivity.this, "评价成功", Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(OrderActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                    break;
                default:
                    break;
            }
        }
    };

}