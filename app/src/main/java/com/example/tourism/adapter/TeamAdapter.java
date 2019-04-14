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
import com.example.tourism.tools.Team;
import com.example.tourism.R;
import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<Team>  mTeamList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView coverImage;
        TextView titleText;
        TextView contentText;
        TextView hotelContent;
        TextView moneyText;

        public ViewHolder(View view){
            super(view);
            coverImage = (ImageView)view.findViewById(R.id.team_item_image_cover);
            titleText = (TextView)view.findViewById(R.id.team_item_text_title);
            contentText = (TextView)view.findViewById(R.id.team_item_text_context);
            hotelContent = (TextView)view.findViewById(R.id.team_item_text_hotel_content);
            moneyText = (TextView)view.findViewById(R.id.team_item_text_money_context);
        }
    }
    public TeamAdapter(List<Team> teamList){
        mTeamList = teamList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.team_item,viewGroup,false);
         ViewHolder holder = new ViewHolder(view);
         return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Team team = mTeamList.get(i);
        viewHolder.titleText.setText(team.getTitle());
        viewHolder.contentText.setText(team.getContent());
        viewHolder.hotelContent.setText(team.getHotelContent());
        viewHolder.moneyText.setText(team.getMoney()+"元");
        Glide.with(MyApplication.getContext())
                .load(team.getCover_url())
                .into(viewHolder.coverImage);
    }

    @Override
    public int getItemCount() {
        return mTeamList.size();
    }
}
