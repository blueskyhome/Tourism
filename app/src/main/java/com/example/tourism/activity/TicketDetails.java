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
import com.example.tourism.tools.Order;
import com.example.tourism.tools.Ticket;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class TicketDetails extends AppCompatActivity implements View.OnClickListener {

    private List<Apprise> list = new ArrayList<>();
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
    private Ticket ticket;
    private int account = 1;

    private String[] userName = new String[]{"希尔顿酒店","三亚海悦湾度假酒店","万豪国际酒店","三亚文华东方酒店"};
    private String[] userContent = new String[]{
            "希尔顿国际酒店集团(HI)，为总部设于英国的希尔顿集团公司旗下分支，拥有除美国外全球范围内“希尔顿”商标的使用权。",
            "三亚海悦湾度假酒店由锦江国际酒店管理有限公司上海和平饭店管理，素有“热带滨海花园酒店”之称。位于享有“椰阳梦长廊”美誉的三亚湾中心，咫尺碧波大海仅百米，酒店置身绿茵花园、环境优美迷人，热带花草相拥，蓝蓝的大海、洁白的沙滩、清新的空气、灿烂的阳光，构成了一个热带滨海花园王国。",
            "河源市迈豪国际酒店（原河源市万豪国际酒店）坐落于新河源市新市区东江与丰江交汇的沿江东路，由广东客家女旅游集团出资建设，是河源市首家五星级酒店。",
            "三亚文华东方酒店是文华东方酒店管理集团在中国大陆管理的首家豪华度假型酒店。酒店坐落于风光旖旎的珊瑚湾，距离三亚凤凰国际机场20公里、市中心3公里。酒店依山傍海，拥有1.2公里长的珊瑚湾，和281套全海景客房、套房、阁楼和别墅。"
    };
    private int[] seeNumber = new int[]{2000, 3000, 5000, 2000};
    private String[] url = new String[]{
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ip571enj30d608s3yv.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ipewha3j30d609u74u.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22iplx3orj30d609vq3n.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ipsdbxrj30d4097t92.jpg"
    };


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
        AppraseAdapter adapter = new AppraseAdapter(list);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        ticket = intent.getParcelableExtra("ticket");
        ticketName.setText(ticket.getName());
        ticketContent.setText(ticket.getContent());
        accountMoney.setText(ticket.getPrice()+"");
        Glide.with(TicketDetails.this)
                .load(ticket.getImg_url())
                .into(ticketUrl);

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
        list.clear();

        for (int i = 0; i < userName.length; i++) {
            Apprise apprise = new Apprise();

            apprise.setImgId(url[i]);
            apprise.setUserName(userName[i]);
            apprise.setUserApprise(userContent[i]);
            apprise.setSeeNumber(seeNumber[i]);

            list.add(apprise);
        }
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
