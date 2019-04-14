package com.example.tourism.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourism.R;
import com.example.tourism.adapter.TeamAdapter;
import com.example.tourism.tools.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamFragment extends Fragment {
    private List<Team> teamList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamAdapter adapter;
    private String[] title = new String[]{"武夷山一日游","三亚暑期营","九寨沟三日游","台湾环岛游"};
    private String[] content = new String[]{
        "武夷山，武夷山位于江西与福建西北部两省交界处，武夷山脉北段东南麓总面积999.75平方公里，是中国著名的风景旅游区和避暑胜地。属典型的丹霞地貌，是首批国家级重点风景名胜区之一。",
        "三亚，简称崖，古称崖州，别称鹿城。是海南省下辖地级市，位于海南岛的最南端。三亚东邻陵水县，西接乐东县，北毗保亭县，南临南海，三亚市陆地总面积1919.58平方千米，海域总面积6000平方千米。",
        "九寨沟：世界自然遗产、国家重点风景名胜区、国家AAAAA级旅游景区、国家级自然保护区、国家地质公园、世界生物圈保护区网络，是中国第一个以保护自然风景为主要目的的自然保护区。",
        "台湾（Taiwan），简称“台”，是中华人民共和国省级行政区，省会台北，位于中国大陆东南海域，东临太平洋，西隔台湾海峡与福建省相望，南界巴士海峡与菲律宾群岛相对。台湾省由中国第一大岛台湾岛和周围属岛以及澎湖列岛两大岛群，共80余个岛屿所组成，总面积3.6万平方公里。"
    };
    private String[] url = new String[]{
            "http://ww1.sinaimg.cn/large/005T39qaly1g22ab3gxr0j321g19w000.jpg",
            "http://ww1.sinaimg.cn/large/005T39qaly1g229g5rr0fj30ku0dw0uq.jpg",
            "http://ww1.sinaimg.cn/large/005T39qaly1g229hevx3lj30sc0f01kx.jpg",
            "http://ww1.sinaimg.cn/large/005T39qaly1g229j6pfs9j30go0b3acr.jpg"
    };
    private String[] hotel = new String[]{"希尔顿酒店、万达酒店","三亚滨海酒店","川枫酒店、特色民宿","台北大酒店，帆船酒店"};
    private int[] money = new int[]{2000,15000,6000,20000};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.team_fragment, null);
        initData();
        initView(view);
        return view;
    }
    private void initData(){
       for(int i = 0 ; i < title.length ; i++){
           Team team = new Team();

           team.setTitle(title[i]);
           team.setContent(content[i]);
           team.setCover_url(url[i]);
           team.setHotelContent(hotel[i]);
           team.setMoney(money[i]);

           teamList.add(team);
       }
    }
    private void initView(View view){
        recyclerView = (RecyclerView)view.findViewById(R.id.team_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TeamAdapter(teamList);
        recyclerView.setAdapter(adapter);
    }
}
