package com.example.tourism.adapter;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.activity.MainActivity;
import com.example.tourism.tools.Apprise;
import com.example.tourism.tools.MyApplication;

import java.util.List;

public class AppraseAdapter extends RecyclerView.Adapter<AppraseAdapter.ViewHolder> {

    private List<Apprise> mAppriseList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView useName;
        private TextView userApprise;
        private TextView seeNumber;
        private View appraseButton;

        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.user_img);
            useName = (TextView) view.findViewById(R.id.user_name);
            userApprise = (TextView) view.findViewById(R.id.user_apprise);
            seeNumber = (TextView) view.findViewById(R.id.see_number);
            appraseButton = (View) view.findViewById(R.id.apprise_button);
        }
    }

    public AppraseAdapter(List<Apprise> list) {
        this.mAppriseList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.appraise, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Apprise apprise = mAppriseList.get(i);
        viewHolder.useName.setText(apprise.getUserName());
        viewHolder.userApprise.setText(apprise.getUserApprise());
        viewHolder.seeNumber.setText(apprise.getSeeNumber()+"");
        Glide.with(MyApplication.getContext())
                .load(apprise.getImgUrl())
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return mAppriseList.size();
    }
}
