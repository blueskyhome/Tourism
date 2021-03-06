/**
 * 自驾Adapter
 */
package com.example.tourism.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.example.tourism.activity.LoginActivity;
import com.example.tourism.activity.user.DriveDetailActivity;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.user.Drive;
import com.example.tourism.tools.MyApplication;

import com.example.tourism.R;
import java.util.List;

public class SelfDriveAdapter extends RecyclerView.Adapter<SelfDriveAdapter.ViewHolder> {
    private List<Drive>  mSelfDriveList;
    private String[] mSpotList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coverImage;
        TextView titleText;
        TextView hotelContent;
        TextView moneyText;
        LinearLayout linearLayout;
        LinearLayout itemLayout;
        public ViewHolder(View view){
            super(view);
            coverImage = (ImageView)view.findViewById(R.id.self_drive_item_image_cover);
            titleText = (TextView)view.findViewById(R.id.self_drive_item_text_title);
            hotelContent = (TextView)view.findViewById(R.id.self_drive_item_text_house_content);
            linearLayout = (LinearLayout)view.findViewById(R.id.self_drive_spot_layout);
            moneyText = (TextView)view.findViewById(R.id.self_drive_item_text_money_context);
            itemLayout = (LinearLayout)view.findViewById(R.id.self_drive_item);
        }
    }
    public SelfDriveAdapter(List<Drive> selfDriveList, Context context){
        mSelfDriveList = selfDriveList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.self_drive_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Drive selfDrive = mSelfDriveList.get(i);
        viewHolder.titleText.setText(selfDrive.getTitle());
        viewHolder.hotelContent.setText(selfDrive.getHotel());
        viewHolder.moneyText.setText(selfDrive.getMoney()+"元");
        Glide.with(MyApplication.getContext())
                .load(selfDrive.getCover_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.coverImage);
        TextView textViewOne = (TextView)viewHolder.linearLayout.findViewById(R.id.self_drive_spot_one);
        viewHolder.linearLayout.removeView(textViewOne);
        getStr(selfDrive.getRecommend());
        for(int j = 0 ; j < mSpotList.length;j++){
            View view1 = View.inflate(MyApplication.getContext(),R.layout.child_view,null);
            TextView textView = view1.findViewById(R.id.self_drive_spot);

            textView.setText(mSpotList[j]);
            textView.setTextColor(Color.BLACK);
            viewHolder.linearLayout.addView(view1);
        }
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.isLogin){
                    Intent intent = new Intent(context,DriveDetailActivity.class);
                    intent.putExtra("name",selfDrive.getTitle());
                    context.startActivity(intent);
                }else{
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }

            }
        });
    }

    private void getStr(String str){
        mSpotList = str.split("、");
    }

    @Override
    public int getItemCount() {
        return mSelfDriveList.size();
    }
}
