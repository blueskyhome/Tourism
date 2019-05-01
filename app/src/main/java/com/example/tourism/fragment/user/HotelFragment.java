package com.example.tourism.fragment.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tourism.R;
import com.example.tourism.adapter.user.HotelAdapter;
import com.example.tourism.tools.user.Hotel;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class HotelFragment extends Fragment {
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.hotel_fragment, null);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BmobQuery<Hotel> bmobQuery = new BmobQuery<Hotel>();
                bmobQuery.findObjects(new FindListener<Hotel>() {
                    @Override
                    public void done(List<Hotel> list, BmobException e) {
                        if (e == null ) {
                            hotelAdapter = new HotelAdapter(list, getActivity());
                            recyclerView.setAdapter(hotelAdapter);
                        } else {
                            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.hotel_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
