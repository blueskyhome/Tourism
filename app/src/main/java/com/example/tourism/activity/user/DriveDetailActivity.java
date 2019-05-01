package com.example.tourism.activity.user;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.example.tourism.R;
import com.example.tourism.tools.GlideImageLoader;

import com.youth.banner.Banner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DriveDetailActivity extends AppCompatActivity {
    private Banner banner;
    NestedScrollView scrollView;
    private ArrayList arrayList;
    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive_detail);
        scrollView = (NestedScrollView) findViewById(R.id.drive_detail_scroll);
        banner = (Banner)findViewById(R.id.picture_banner);
        banner.setImageLoader(new GlideImageLoader());
        initImageArray();
        banner.setImages(arrayList);
        banner.start();
        initView();
    }

     public void  initImageArray(){
        arrayList = new ArrayList<String>();
        arrayList.clear();
        for(String str : images){
            arrayList.add(str);
        }
     }
     private void initView(){
      scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
          @Override
          public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {

          }
      });
     }

    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }
}
