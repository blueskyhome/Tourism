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
import com.example.tourism.adapter.TicketAdapter;
import com.example.tourism.tools.Ticket;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class TicketFragment extends Fragment {
    private static final int UPDATE = 1;
    private List<Ticket> mTicketList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;

    private String[] TicketName = new String[]{"北京故宫","狮子林","沧浪亭","拙政园", "留园", "承德避暑山庄"};
    private String[] TicketContent = new String[]{
            "北京故宫是中国明清两代的皇家宫殿，旧称为紫禁城，位于北京中轴线的中心，是中国古代宫廷建筑之精华。北京故宫以三大殿为中心，占地面积72万平方米，建筑面积约15万平方米，有大小宫殿七十多座，房屋九千余间。是世界上现存规模最大、保存最为完整的木质结构古建筑之一。",
            "狮子林始建于元代至正二年（1342年），是中国古典私家园林建筑的代表之一。属于苏州四大名园之一。狮子林同时又是世界文化遗产、全国重点文物保护单位、国家AAAA级旅游景区。",
            "沧浪亭位于苏州市城南，是苏州最古老的一所园林。占地面积10800平方米。园内有一泓清水贯穿，波光倒影，景象万千。“沧浪亭”始为五代吴越王钱缪之子钱元亮的池馆。",
            "拙政园，位于江苏省苏州市，始建于明正德初年（16世纪初），是江南古典园林的代表作品。拙政园与北京颐和园、承德避暑山庄、苏州留园一起被誉为中国四大名园。",
            "留园位于苏州阊门外留园路338号，以园内建筑布置精巧、奇石众多而知名，与苏州拙政园、北京颐和园、承德避暑山庄并称中国四大名园。",
            "承德避暑山庄：世界文化遗产，国家AAAAA级旅游景区，全国重点文物保护单位，中国四大名园之一。承德避暑山庄又名“承德离宫”或“热河行宫”，位于河北省承德市中心北部，武烈河西岸一带狭长的谷地上，是清代皇帝夏天避暑和处理政务的场所。"
    };
    private int[] TicketPrice = new int[]{60, 40, 20, 50, 50, 130};
    private String[] url = new String[]{
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23kz2zjusj307g0adq8e.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23kyhzfqgj307g05l41r.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23kzs6427j307g04ztbf.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23l0d8qo4j307g05l41t.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23latztmfj307g04swgy.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g23lc7e655j307g0a7wkn.jpg"
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.ticket_fragment, null);
        mTicketList.clear();
        initData(view);
        initView(view);
        return view;
    }

    private void initData(final View view) {

        final Handler handler = new Handler() {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case UPDATE:
                        initView(view);
                        break;
                    default:
                        break;
                }
            }
        };
                BmobQuery<Ticket> ticketBmobQuery = new BmobQuery<>();
                ticketBmobQuery.findObjects(new FindListener<Ticket>() {
                    @Override
                    public void done(List<Ticket> list, BmobException e) {
                        if (e == null) {
                            mTicketList = list;
                            Message message = new Message();
                            message.what = UPDATE;
                            handler.sendMessage(message);
                        } else {
                            Log.e("haha", e.toString());
                        }
                    }
                });




    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.ticket_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        ticketAdapter = new TicketAdapter(mTicketList, getActivity());
        recyclerView.setAdapter(ticketAdapter);
    }
}
