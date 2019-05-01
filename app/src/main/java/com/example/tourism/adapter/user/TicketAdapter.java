package com.example.tourism.adapter.user;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.activity.LoginActivity;
import com.example.tourism.activity.user.TicketDetails;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.user.Ticket;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private List<Ticket> mTicketList ;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ticketImg;
        private TextView ticketName;
        private TextView ticketContent;
        private TextView ticketPrice;
        private View ticketView;

        public ViewHolder(View view) {
            super(view);
            ticketImg = (ImageView) view.findViewById(R.id.ticket_img);
            ticketName = (TextView) view.findViewById(R.id.ticket_title);
            ticketContent = (TextView) view.findViewById(R.id.ticket_context);
            ticketPrice = (TextView) view.findViewById(R.id.ticket_price);
            ticketView = (View) view.findViewById(R.id.ticket_item);
        }
    }

    public TicketAdapter(List<Ticket> TicketList, Context context) {
        this.mTicketList = TicketList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ticket_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.ticketView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Global.isLogin) {
                    Intent intent = new Intent(mContext, TicketDetails.class);
                    int position = holder.getAdapterPosition();
                    Ticket ticket = mTicketList.get(position);
                    intent.putExtra("name", ticket.getName());
                    intent.putExtra("style", "Ticket");
                    mContext.startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(intent);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Ticket ticket = mTicketList.get(i);
        viewHolder.ticketName.setText(ticket.getName());
        viewHolder.ticketContent.setText(ticket.getContent());
        viewHolder.ticketPrice.setText(ticket.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(ticket.getImg_url())
                .into(viewHolder.ticketImg);

    }

    @Override
    public int getItemCount() {
        return mTicketList.size();
    }

}
