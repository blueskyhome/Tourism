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
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.adapter.TicketAdapter;
import com.example.tourism.tools.Ticket;
import com.example.tourism.tools.Tikes;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;

public class TicketFragment extends Fragment {

    private List<Ticket> mTicketList = new ArrayList<>();
    private List<BmobObject> categories = new ArrayList<>();
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.ticket_fragment, null);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Ticket> bmobQuery = new BmobQuery<Ticket>();
                bmobQuery.findObjects(new FindListener<Ticket>() {
                    @Override
                    public void done(List<Ticket> list, BmobException e) {
                        if (e == null ) {
                            ticketAdapter = new TicketAdapter(list, getActivity());
                            recyclerView.setAdapter(ticketAdapter);
                        } else {
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.ticket_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
    }
}
