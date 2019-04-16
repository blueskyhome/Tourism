/**
 * 自驾Adapter
 */
package com.example.tourism.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.SelfDrive;
import com.example.tourism.tools.Team;
import com.example.tourism.R;
import java.util.List;

public class SelfDriveAdapter extends RecyclerView.Adapter<SelfDriveAdapter.ViewHolder> {
    private List<SelfDrive>  mSelfDriveList;
    private String[] mSpotList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coverImage;
        TextView titleText;
        TextView hotelContent;
        TextView moneyText;

        public ViewHolder(View view){
            super(view);
            coverImage = (ImageView)view.findViewById(R.id.self_drive_item_image_cover);
            titleText = (TextView)view.findViewById(R.id.self_drive_item_text_title);
            hotelContent = (TextView)view.findViewById(R.id.self_drive_item_text_house_content);
            moneyText = (TextView)view.findViewById(R.id.self_drive_item_text_money_context);
        }
    }
    public SelfDriveAdapter(List<SelfDrive> selfDriveList){
        mSelfDriveList = selfDriveList;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SelfDrive selfDrive = mSelfDriveList.get(i);
        viewHolder.titleText.setText(selfDrive.getTitle());
        viewHolder.hotelContent.setText(selfDrive.getHouseText());
        viewHolder.moneyText.setText(selfDrive.getMoney()+"元");
        Glide.with(MyApplication.getContext())
                .load(selfDrive.getCover_url())
                .into(viewHolder.coverImage);
    }

    @Override
    public int getItemCount() {
        return mSelfDriveList.size();
    }
}
