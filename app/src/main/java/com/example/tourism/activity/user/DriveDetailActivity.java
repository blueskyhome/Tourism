package com.example.tourism.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.adapter.user.AppraseAdapter;
import com.example.tourism.tools.GlideImageLoader;

import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.Apprise;
import com.example.tourism.tools.user.Drive;
import com.example.tourism.tools.user.Hotel;
import com.example.tourism.tools.user.Order;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class DriveDetailActivity extends AppCompatActivity {

    private Banner banner;
    private ArrayList arrayList;
    private int mount = 1;
    private int money;
    private String name;
    private Drive drive;
    private AppraseAdapter adapter;
    @BindView(R.id.drive_detail_title)
    TextView drive_title;
    @BindView(R.id.drive_detail_recommend)
    TextView drive_recommend;
    @BindView(R.id.drive_detail_money_one)
    TextView drive_money_one;
    @BindView(R.id.drive_detail_img)
    ImageView drive_img;
    @BindView(R.id.drive_question1)
    TextView question1;
    @BindView(R.id.drive_question2)
    TextView question2;
    @BindView(R.id.drive_details_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.drive_detail_order)
    Button drive_order;
    @BindView(R.id.drive_add)
    ImageView drive_add;
    @BindView(R.id.drive_reduce)
    ImageView drive_reduce;
    @BindView(R.id.drive_mount)
    TextView drive_mount;
    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_detail);
        ButterKnife.bind(this);
        initView();

        initData();
    }

     public void  initView(){
         LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
         recyclerView.setLayoutManager(layoutManager);
         question1.setText("服务态度好吗");
         question2.setText("一张门票可以玩一天吗");
         drive_add.setColorFilter(Color.RED);
         drive_reduce.setColorFilter(Color.GREEN);
         banner = (Banner)findViewById(R.id.picture_banner);
        banner.setImageLoader(new GlideImageLoader());
        arrayList = new ArrayList<String>();
        arrayList.clear();
        for(String str : images){
            arrayList.add(str);
        }
         banner.setImages(arrayList);
         banner.start();
     }
     @OnClick({R.id.drive_question1,R.id.drive_question2,R.id.drive_detail_order,R.id.drive_add,R.id.drive_reduce})
     public void onClick(View v){
        switch (v.getId()){
            case R.id.drive_question1:
            case R.id.drive_question2:
                Intent intent = new Intent(this,QuestionActivity.class);
                intent.putExtra("name", drive.getTitle());
                startActivity(intent);
                break;
            case R.id.drive_detail_order:
                addOrder();
                break;
            case R.id.drive_add:
                mount += 1;
                drive_mount.setText(String.valueOf(mount));
                drive_money_one.setText(String.valueOf(mount*money)+"元");
                break;
            case R.id.drive_reduce:
                if(mount > 1){
                    mount -= 1;
                    drive_mount.setText(String.valueOf(mount));
                    drive_money_one.setText(String.valueOf(mount*money)+"元");
                }
                break;
            default:
                break;
        }
     }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        new Thread(new Runnable() {
            @Override
            public void run() {

                BmobQuery<Drive> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("title", name);
                bmobQuery.findObjects(new FindListener<Drive>() {
                    @Override
                    public void done(List<Drive> list, BmobException e) {
                        drive = list.get(0);
                        drive_title.setText(drive.getTitle());
                        drive_money_one.setText("￥"+drive.getMoney()+"元起");
                        money = drive.getMoney();
                        drive_recommend.setText(drive.getRecommend());
                        Glide.with(MyApplication.getContext())
                                .load(drive.getCover_url())
                                .asBitmap()
                                .into(drive_img);
                        list.clear();
                    }
                });

                BmobQuery<Apprise> appriseQuery = new BmobQuery<>();
                appriseQuery.addWhereEqualTo("name", name);
                appriseQuery.findObjects(new FindListener<Apprise>() {
                    @Override
                    public void done(List<Apprise> list, BmobException e) {
                        adapter = new AppraseAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        }).start();
    }
    private void addOrder() {
        final Order tickerOrder = new Order();
        tickerOrder.setThing_img(drive.getCover_url());
        tickerOrder.setUser(Global.userName);
        tickerOrder.setThing_name(drive.getTitle());
        tickerOrder.setThing_number(mount);
        tickerOrder.setSingle_money(drive.getMoney());
        tickerOrder.setMoney(money*mount);
        tickerOrder.setIs_examine(false);
        tickerOrder.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(DriveDetailActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DriveDetailActivity.this, OrderActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DriveDetailActivity.this, "下单失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
