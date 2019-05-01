package com.example.tourism.fragment.manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourism.R;
import com.example.tourism.adapter.manager.UserManagerAdapter;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class UserManagerFragment extends Fragment {

    private RecyclerView recyclerView;
    private UserManagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_manager_fragment, container, false);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<User> bmobQuery = new BmobQuery<>();
                bmobQuery.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e == null) {
                            adapter = new UserManagerAdapter(list, handler);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });
            }
        }).start();
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.user_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        recyclerView.setLayoutManager(layoutManager);
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
