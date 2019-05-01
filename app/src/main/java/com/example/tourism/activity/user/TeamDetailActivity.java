package com.example.tourism.activity.user;

import android.content.Intent;
import android.graphics.Color;
import android.provider.CalendarContract;
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
import com.example.tourism.tools.user.Order;
import com.example.tourism.tools.user.Team;
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

public class TeamDetailActivity extends AppCompatActivity {


    private String name;
    private Team team;
    private AppraseAdapter adapter;
    private int mount = 1;
    private int money;
    @BindView(R.id.team_detail_title)
    TextView team_title;
    @BindView(R.id.team_detail_recommend)
    TextView team_recommend;
    @BindView(R.id.team_detail_money_one)
    TextView team_money_one;
    @BindView(R.id.team_detail_cover)
    ImageView team_cover;
    @BindView(R.id.team_detail_img)
    ImageView team_img;
    @BindView(R.id.team_question1)
    TextView question1;
    @BindView(R.id.team_question2)
    TextView question2;
    @BindView(R.id.team_details_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.team_detail_order)
    Button team_order;
    @BindView(R.id.team_add)
    ImageView team_add;
    @BindView(R.id.team_reduce)
    ImageView team_reduce;
    @BindView(R.id.team_mount)
    TextView team_mount;

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
        setContentView(R.layout.activity_team_detail);
        ButterKnife.bind(this);
        initView();

        initData();
    }

    public void  initView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        question1.setText("服务态度好吗");
        question2.setText("一张门票可以玩一天吗");
        team_add.setColorFilter(Color.RED);
        team_reduce.setColorFilter(Color.GREEN);

    }
    @OnClick({R.id.team_question1,R.id.team_question2,R.id.team_detail_order,R.id.team_add,R.id.team_reduce})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.team_question1:
            case R.id.team_question2:
                Intent intent = new Intent(this,QuestionActivity.class);
                intent.putExtra("name", team.getTitle());
                startActivity(intent);
                break;
            case R.id.team_detail_order:
                addOrder();
                break;
            case R.id.team_add:
                mount += 1;
                team_mount.setText(String.valueOf(mount));
                team_money_one.setText(String.valueOf(mount*money)+"元");
                break;
            case R.id.team_reduce:
                if(mount > 1){
                    mount -= 1;
                    team_mount.setText(String.valueOf(mount));
                    team_money_one.setText(String.valueOf(mount*money)+"元");
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

                BmobQuery<Team> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("title", name);
                bmobQuery.findObjects(new FindListener<Team>() {
                    @Override
                    public void done(List<Team> list, BmobException e) {
                        team = list.get(0);
                        team_title.setText(team.getTitle());
                        team_money_one.setText("￥"+team.getMoney()+"元起");
                        money = team.getMoney();
                        team_recommend.setText(team.getWord());
                        Glide.with(MyApplication.getContext())
                                .load(team.getCover_url())
                                .asBitmap()
                                .into(team_img);
                        Glide.with(MyApplication.getContext())
                                .load(team.getCover_url())
                                .asBitmap()
                                .into(team_cover);
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
        tickerOrder.setThing_img(team.getCover_url());
        tickerOrder.setUser(Global.userName);
        tickerOrder.setThing_name(team.getTitle());
        tickerOrder.setThing_number(mount);
        tickerOrder.setSingle_money(team.getMoney());
        tickerOrder.setMoney(money);
        tickerOrder.setIs_examine(false);
        tickerOrder.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(TeamDetailActivity.this, "下单成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TeamDetailActivity.this, OrderActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(TeamDetailActivity.this, "下单失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
