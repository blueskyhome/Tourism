package com.example.tourism.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.activity.LoginActivity;
import com.example.tourism.activity.OrderActivity;
import com.example.tourism.activity.SettingActivity;
import com.example.tourism.adapter.OrderAdapter;
import com.example.tourism.tools.Global;

public class MineFragment extends Fragment implements View.OnClickListener {

    private ImageView settingImg;
    private TextView name;
    private RelativeLayout layout;
    private View myOrder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.mine_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        settingImg = (ImageView) view.findViewById(R.id.setting_button);
        name = (TextView) view.findViewById(R.id.name);
        myOrder = (View) view.findViewById(R.id.my_order);
        layout = (RelativeLayout) view.findViewById(R.id.login_button);
        if (Global.isLogin) {
            if (Global.isManager) {
                name.setText(Global.managerName);
            } else {
                name.setText(Global.nickName);
            }
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.VISIBLE);
            layout.setOnClickListener(this);
        }

        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.isLogin) {
                    Intent intent = new Intent(getActivity(), OrderActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        settingImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_button:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.login_button:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }
}
