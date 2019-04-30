package com.example.tourism.adapter;

import android.content.Context;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.tools.Global;
import com.example.tourism.tools.MyApplication;
import com.example.tourism.tools.Order;

import java.util.List;
import android.os.Handler;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private List<Order> mOrderList;
    private Handler handler;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView order_img;
        private TextView order_name;
        private TextView order_price;
        private TextView order_number;
        private TextView order_money;
        private TextView order_check;
        private TextView order_confirm;

        public ViewHolder(View view){
            super(view);
            order_img = (ImageView) view.findViewById(R.id.order_img);
            order_name = (TextView) view.findViewById(R.id.order_name);
            order_price = (TextView) view.findViewById(R.id.order_price);
            order_number = (TextView) view.findViewById(R.id.order_number);
            order_money = (TextView) view.findViewById(R.id.order_money);
            order_check = (TextView) view.findViewById(R.id.is_check);
            order_confirm = (TextView) view.findViewById(R.id.order_confirm);
        }
    }

    public OrderAdapter(List<Order> list, Handler handler) {
        this.mOrderList = list;
        this.handler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.order_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int opsition = holder.getAdapterPosition();
                        final Order order = mOrderList.get(opsition);
                        order.delete(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null ) {
                                    Message message = new Message();
                                    message.what = 1;
                                    Global.orderName = order.getThing_name();
                                    handler.sendMessage(message);
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
        Order order = mOrderList.get(i);
        viewHolder.order_name.setText(order.getThing_name());
        viewHolder.order_price.setText("¥" + order.getSingle_money());
        viewHolder.order_number.setText("X" + order.getThing_number()+"");
        viewHolder.order_money.setText("共" + order.getThing_number() + "件商品 合计：" + order.getMoney());
        if (order.getIs_examine()) {
            viewHolder.order_check.setText("已审核");
            viewHolder.order_confirm.setVisibility(View.VISIBLE);
        } else {
            viewHolder.order_check.setText("未审核");
        }

        Glide.with(MyApplication.getContext())
                .load(order.getThing_img())
                .into(viewHolder.order_img);
    }

    @Override
    public int getItemCount() {
        return mOrderList.size();
    }
}
