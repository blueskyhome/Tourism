package com.example.tourism.activity.user;

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
import com.example.tourism.adapter.user.AppraseAdapter;
import com.example.tourism.tools.user.Apprise;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.user.Order;
import com.example.tourism.tools.user.Ticket;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class TicketDetails extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button subtractButton;
    private Button addButton;
    private Button buyButton;
    private ImageView ticketUrl;
    private TextView buyNumber;
    private TextView accountMoney;
    private TextView ticketName;
    private TextView ticketContent;
    private TextView questionAccount;
    private TextView question1;
    private TextView question2;
    private View questionView;
    private String name;
    private Ticket ticket;
    private AppraseAdapter adapter;
    private int account = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_details);
        initData();
        initView();
    }

    private void initView() {
        ticketUrl = (ImageView) findViewById(R.id.details_img);
        ticketName = (TextView) findViewById(R.id.details_name);
        ticketContent = (TextView) findViewById(R.id.details_content);
        questionAccount = (TextView) findViewById(R.id.questionAccount);
        question1 = (TextView) findViewById(R.id.question1);
        question2 = (TextView) findViewById(R.id.question2);
        subtractButton = (Button) findViewById(R.id.subtract_button);
        addButton = (Button) findViewById(R.id.add_button);
        buyButton = (Button) findViewById(R.id.buy_button);
        buyNumber = (TextView) findViewById(R.id.buy_number);
        accountMoney = (TextView) findViewById(R.id.account_price);
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
                    accountMoney.setText(String.valueOf(account*ticket.getPrice())+"元");
                }
                break;
            case R.id.add_button:
                account += 1;
                buyNumber.setText(String.valueOf(account));
                accountMoney.setText(String.valueOf(account*ticket.getPrice())+"元");
                break;
            case R.id.buy_button:
                addOrder();
                break;
            case R.id.question:
                Intent intent = new Intent(this, QuestionActivity.class);
                intent.putExtra("name", ticket.getName());
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
                BmobQuery<Ticket> bmobQuery = new BmobQuery<>();
                bmobQuery.addWhereEqualTo("name", name);
                bmobQuery.findObjects(new FindListener<Ticket>() {
                    @Override
                    public void done(List<Ticket> list, BmobException e) {
                        if (e == null ) {
                            ticket = list.get(0);
                            ticketName.setText(ticket.getName());
                            ticketContent.setText(ticket.getContent());
                            accountMoney.setText(ticket.getPrice()+"");
                            Glide.with(TicketDetails.this)
                                    .load(ticket.getImg_url())
                                    .into(ticketUrl);
                        } else {
                            Toast.makeText(TicketDetails.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }

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
        tickerOrder.setThing_img(ticket.getImg_url());
        tickerOrder.setUser(Global.userName);
        tickerOrder.setThing_name(ticket.getName());
        tickerOrder.setThing_number(account);
        tickerOrder.setSingle_money(ticket.getPrice());
        tickerOrder.setMoney(ticket.getPrice() * account);
        tickerOrder.setIs_examine(false);
        tickerOrder.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Toast.makeText(TicketDetails.this, "下单成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TicketDetails.this, OrderActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(TicketDetails.this, "下单失败"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
