package com.example.tourism.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.activity.LoginActivity;
import com.example.tourism.activity.user.TeamDetailActivity;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;

import com.example.tourism.tools.user.Team;

import com.example.tourism.R;


import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<Team>  mTeamList;
    private Context context;
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
    public TeamAdapter(List<Team> teamList, Context context){
        mTeamList = teamList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
         View view = LayoutInflater.from(viewGroup.getContext())
                 .inflate(R.layout.team_item,viewGroup,false);
         ViewHolder holder = new ViewHolder(view);
         final Team team = mTeamList.get(i);
         view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(Global.isLogin){
                     Intent intent = new Intent(context, TeamDetailActivity.class);
                     intent.putExtra("name",team.getTitle());
                     context.startActivity(intent);

                 }else{
                     Intent intent = new Intent(context, LoginActivity.class);
                     context.startActivity(intent);
                 }

             }
         });
         return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Team team = mTeamList.get(i);
        viewHolder.titleText.setText(team.getTitle());
        viewHolder.contentText.setText(team.getWord());
        viewHolder.hotelContent.setText(team.getHotel());
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
