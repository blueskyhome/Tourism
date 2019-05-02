package com.example.tourism.adapter.user;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.Apprise;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Apprise> mAppriseList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView commentName;
        private TextView userApprise;
        private TextView seeNumber;
        private View appraseButton;

        public ViewHolder(View view) {
            super(view);
            commentName = (TextView) view.findViewById(R.id.content_name);
            userApprise = (TextView) view.findViewById(R.id.user_apprise);
            seeNumber = (TextView) view.findViewById(R.id.see_number);
            appraseButton = (View) view.findViewById(R.id.apprise_button);
        }
    }

    public CommentAdapter(List<Apprise> list) {
        this.mAppriseList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.comment, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Apprise apprise = mAppriseList.get(i);
        viewHolder.commentName.setText("对"+apprise.getName()+"做出的伟大评论：");
        viewHolder.userApprise.setText(apprise.getUserApprise());
        viewHolder.seeNumber.setText(apprise.getSeeNumber()+"");

    }

    @Override
    public int getItemCount() {
        return mAppriseList.size();
    }
}
