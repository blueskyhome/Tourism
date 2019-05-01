package com.example.tourism.activity.manager;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.tourism.R;
import com.example.tourism.activity.BaseActivity;
import com.example.tourism.fragment.manager.GoodsManagerFragment;
import com.example.tourism.fragment.manager.OrderManagerFragment;
import com.example.tourism.fragment.manager.UserManagerFragment;
import com.example.tourism.tools.ActivityCollector;

public class ManagerActivity extends BaseActivity implements View.OnClickListener {

    private UserManagerFragment userManagerFragment;
    private OrderManagerFragment orderManagerFragment;
    private GoodsManagerFragment goodsManagerFragment;

    private View userLayout;
    private View orderLayout;
    private View goodsLayout;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity);

        initView();
        fragmentManager = getSupportFragmentManager();
        setTabSelect(0);
    }

    private void initView() {
        userLayout = (View) findViewById(R.id.user_manager);
        orderLayout = (View) findViewById(R.id.order_manager);
        goodsLayout = (View) findViewById(R.id.goods_manager);

        userLayout.setOnClickListener(this);
        orderLayout.setOnClickListener(this);
        goodsLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_manager:
                setTabSelect(0);
                break;
            case R.id.order_manager:
                setTabSelect(1);
                break;
            case R.id.goods_manager:
                setTabSelect(2);
                break;
            default:
                break;
        }
    }

    private void setTabSelect(int i) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (userManagerFragment == null) {
                    userManagerFragment = new UserManagerFragment();
                    transaction.add(R.id.manager_content, userManagerFragment);
                } else {
                    transaction.show(userManagerFragment);
                }
                break;
            case 1:
                if (orderManagerFragment == null) {
                    orderManagerFragment = new OrderManagerFragment();
                    transaction.add(R.id.manager_content, orderManagerFragment);
                } else {
                    transaction.show(orderManagerFragment);
                }
                break;
            case 2:
                if (goodsManagerFragment == null) {
                    goodsManagerFragment = new GoodsManagerFragment();
                    transaction.add(R.id.manager_content, goodsManagerFragment);
                } else {
                    transaction.show(goodsManagerFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (userManagerFragment!= null) {
            transaction.hide(userManagerFragment);
        }
        if (orderManagerFragment != null) {
            transaction.hide(orderManagerFragment);
        }
        if (goodsManagerFragment != null) {
            transaction.hide(goodsManagerFragment);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finishAll();
    }

}
