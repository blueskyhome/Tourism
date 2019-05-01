package com.example.tourism.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.adapter.SelfDriveAdapter;
import com.example.tourism.tools.Drive;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.Team;
import com.example.tourism.tools.Ticket;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SelfDrivingFragment extends Fragment {

    private List<Drive> selfDriveList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelfDriveAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.self_driving_fragment, null);
        selfDriveList.clear();
        initView(view);
        initData();

        return view;
    }

    private void initData() {
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new SelfDriveAdapter(selfDriveList,getActivity());
                    recyclerView.setAdapter(adapter);
                }
                return true;
            }
        });
        BmobQuery<Drive> driveBmobQuery = new BmobQuery<>();
        driveBmobQuery.findObjects(new FindListener<Drive>() {
            @Override
            public void done(List<Drive> list, BmobException e) {
                if(e==null){
                  selfDriveList =list;
                  Message message =new Message();
                  message.what = 1;
                  handler.sendMessage(message);
                }else{
                    Log.d("haha",e.toString());
                }
            }
        });


        //Log.d("haha","hahaha");

    }
    private void initView(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.self_drive_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

    }

}
