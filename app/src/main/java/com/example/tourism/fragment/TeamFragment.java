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

import com.example.tourism.R;
import com.example.tourism.adapter.TeamAdapter;
import com.example.tourism.tools.Team;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TeamFragment extends Fragment {
    private static final int UPDATE = 2;
    private List<Team> teamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.team_fragment, null);
        teamList.clear();
        initView(view);
        initData();
        return view;
    }
    private void initData(){
        final Handler handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == 1){
                    adapter = new TeamAdapter(teamList);
                    recyclerView.setAdapter(adapter);
                }
                return true;
            }
        });
       BmobQuery<Team> teamBmobQuery = new BmobQuery<>();
       teamBmobQuery.findObjects(new FindListener<Team>() {
           @Override
           public void done(List<Team> list, BmobException e) {
               if(e == null){
                   teamList = list;
                   handler.sendEmptyMessage(1);
               }else{
                   Log.d("haha",e.toString());
               }
           }
       });

    }
    private void initView(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.team_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);


    }
}
