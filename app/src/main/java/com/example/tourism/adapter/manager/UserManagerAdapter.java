package com.example.tourism.adapter.manager;

import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tourism.R;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.User;

import java.util.List;
import android.os.Handler;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserManagerAdapter extends RecyclerView.Adapter<UserManagerAdapter.ViewHolder> {

    private List<User> mUserList;
    private Handler handler;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userName;
        private TextView nickname;
        private TextView deleterUser;

        public ViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.username);
            nickname = (TextView) view.findViewById(R.id.nickname);
            deleterUser = (TextView) view.findViewById(R.id.deleter_user);
        }
    }

    public UserManagerAdapter(List<User> list, Handler handler) {
        this.mUserList = list;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_manager_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.deleterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int position = holder.getAdapterPosition();
                        User user = new User();
                        user.delete(mUserList.get(position).getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Message message = new Message();
                                    message.what = 1;
                                    handler.sendMessage(message);
                                } else {
                                    Toast.makeText(MyApplication.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }).start();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = mUserList.get(i);
        viewHolder.userName.setText(user.getUsername());
        viewHolder.nickname.setText(user.getNickname());
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
