package com.example.tourism.activity;

import android.content.Intent;
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
import com.example.tourism.adapter.AppraseAdapter;
import com.example.tourism.tools.Apprise;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.Hotel;
import com.example.tourism.tools.Order;
import com.example.tourism.tools.Ticket;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class HotelDetails extends AppCompatActivity implements View.OnClickListener{
    private List<Apprise> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private AppraseAdapter adapter;
    private Button subtractButton;
    private Button addButton;
    private Button buyButton;
    private ImageView hotelImg;
    private TextView buyNumber;
    private TextView accountMoney;
    private TextView hotelName;
    private TextView hotelContent;
    private TextView questionAccount;
    private TextView question1;
    private TextView question2;
    private View questionView;
    private Hotel hotel;
    private String name;
    private int account = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotel_details);
        initData();
        initView();
    }

    private void initView() {
        hotelImg = (ImageView) findViewById(R.id.hotel_img);
        hotelName = (TextView) findViewById(R.id.hotel_name);
        hotelContent = (TextView) findViewById(R.id.hotel_content);
        subtractButton = (Button) findViewById(R.id.subtract_button);
        addButton = (Button) findViewById(R.id.add_button);
        buyButton = (Button) findViewById(R.id.buy_button);
        buyNumber = (TextView) findViewById(R.id.buy_number);
        accountMoney = (TextView) findViewById(R.id.account_price);
        questionAccount = (TextView) findViewById(R.id.questionAccount);
        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        questionView = (View) findViewById(R.id.question);

        recyclerView = (RecyclerView) findViewById(R.id.details_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        questionAccount.setText("("+"31"+")");
        question1.setText("服务态度好吗");
        question2.setText("一张门票可以玩一天吗");

        subtractButton.setOnClickListener(this);
        addButton.setOnClickListener(this);
        buyButton.setOnClickListener(this);
        questionView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.subtract_button:
                if (account > 0) {
                    account -= 1;
                    buyNumber.setText(String.valueOf(account));
                    accountMoney.setText(String.valueOf(account*hotel.getPrice())+"元");
                }
                break;
            case R.id.add_button:
                account += 1;
                buyNumber.setText(String.valueOf(account));
                accountMoney.setText(String.valueOf(account*hotel.getPrice())+"元");
                break;
            case R.id.buy_button:
                addOrder();
                break;
            case R.id.question:
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.putExtra("name", hotel.getName());
                startActivity(intent);
                break;
        }
    }

    private void initData() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Hotel> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("name", name);
                bmobQuery.findObjects(new FindListener<Hotel>() {
                    @Override
                    public void done(List<Hotel> list, BmobException e) {
                        hotel = list.get(0);
                        hotelName.setText(hotel.getName());
                        hotelContent.setText(hotel.getContent());
                        accountMoney.setText(hotel.getPrice()+"");
                        Glide.with(HotelDetails.this)
                                .load(hotel.getImg_url())
                                .into(hotelImg);
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
        tickerOrder.setThing_img(hotel.getImg_url());
        tickerOrder.setUser(Global.userName);
        tickerOrder.setThing_name(hotel.getName());
        tickerOrder.setThing_number(account);
        tickerOrder.setSingle_money(hotel.getPrice());
        tickerOrder.setMoney(hotel.getPrice() * account);
        tickerOrder.setIs_examine(false);
        tickerOrder.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(HotelDetails.this, "下单成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HotelDetails.this, OrderActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(HotelDetails.this, "下单失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
