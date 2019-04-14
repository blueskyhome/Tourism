package com.example.tourism.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tourism.adapter.MyFragmentAdapter;
import com.example.tourism.fragment.HotelFragment;
import com.example.tourism.fragment.SelfDrivingFragment;
import com.example.tourism.fragment.MineFragment;
import com.example.tourism.fragment.TeamFragment;
import com.example.tourism.fragment.TicketFragment;
import com.example.tourism.R;
import com.example.tourism.tools.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ViewPager myViewPage;
    private TextView tab0, tab1, tab2, tab3, tab4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        initView();
    }

    private void initView() {

        myViewPage = (ViewPager) findViewById(R.id.my_viewpage);

        TicketFragment ticketFragment = new TicketFragment();
        HotelFragment hotelFragment = new HotelFragment();
        MineFragment singleFragment = new MineFragment();
        TeamFragment teamFragment = new TeamFragment();
        SelfDrivingFragment selfDrivingFragment = new SelfDrivingFragment();

        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(ticketFragment);
        fragmentList.add(hotelFragment);
        fragmentList.add(teamFragment);
        fragmentList.add(selfDrivingFragment);
        fragmentList.add(singleFragment);

        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
        myViewPage.setAdapter(myFragmentAdapter);

        tab0 = (TextView) findViewById(R.id.tv_tab0);
        tab1 = (TextView) findViewById(R.id.tv_tab1);
        tab2 = (TextView) findViewById(R.id.tv_tab2);
        tab3 = (TextView) findViewById(R.id.tv_tab3);
        tab4 = (TextView) findViewById(R.id.tv_tab4);
        myViewPage.setCurrentItem(0);
        tab0.setTextColor(Color.RED);
        tab1.setTextColor(Color.GRAY);
        tab2.setTextColor(Color.GRAY);
        tab3.setTextColor(Color.GRAY);
        tab4.setTextColor(Color.GRAY);

        tab0.setOnClickListener(this);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);

        myViewPage.setOnPageChangeListener(this);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                tab0.setTextColor(Color.RED);
                tab1.setTextColor(Color.GRAY);
                tab2.setTextColor(Color.GRAY);
                tab3.setTextColor(Color.GRAY);
                tab4.setTextColor(Color.GRAY);
                break;
            case 1:
                tab0.setTextColor(Color.GRAY);
                tab1.setTextColor(Color.RED);
                tab2.setTextColor(Color.GRAY);
                tab3.setTextColor(Color.GRAY);
                tab4.setTextColor(Color.GRAY);
                break;
            case 2:
                tab0.setTextColor(Color.GRAY);
                tab1.setTextColor(Color.GRAY);
                tab2.setTextColor(Color.RED);
                tab3.setTextColor(Color.GRAY);
                tab4.setTextColor(Color.GRAY);
                break;
            case 3:
                tab0.setTextColor(Color.GRAY);
                tab1.setTextColor(Color.GRAY);
                tab2.setTextColor(Color.GRAY);
                tab3.setTextColor(Color.RED);
                tab4.setTextColor(Color.GRAY);
                break;
            case 4:
                tab0.setTextColor(Color.GRAY);
                tab1.setTextColor(Color.GRAY);
                tab2.setTextColor(Color.GRAY);
                tab3.setTextColor(Color.GRAY);
                tab4.setTextColor(Color.RED);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_tab0:
                myViewPage.setCurrentItem(0);
                break;
            case R.id.tv_tab1:
                myViewPage.setCurrentItem(1);
                break;
            case R.id.tv_tab2:
                myViewPage.setCurrentItem(2);
                break;
            case R.id.tv_tab3:
                myViewPage.setCurrentItem(3);
                break;
            case R.id.tv_tab4:
                myViewPage.setCurrentItem(4);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }
}
