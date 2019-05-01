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
import com.example.tourism.R;
import com.example.tourism.activity.user.HotelDetails;
import com.example.tourism.tools.user.Hotel;
import com.example.tourism.tools.MyApplication;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<Hotel> mHotelList;
    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelImg;
        private TextView hotelName;
        private TextView hotelContent;
        private TextView hotelPrice;
        private View hotelItem;

        public ViewHolder (View view) {
            super(view);
            hotelImg = (ImageView) view.findViewById(R.id.hotel_item_image_cover);
            hotelName = (TextView) view.findViewById(R.id.hotel_item_text_title);
            hotelContent = (TextView) view.findViewById(R.id.hotel_item_text_context);
            hotelPrice = (TextView) view.findViewById(R.id.hotel_item_text_money_context);
            hotelItem = (View) view.findViewById(R.id.hotel_item);
        }
    }

    public HotelAdapter (List<Hotel> hotelList, Context context) {
        mHotelList = hotelList;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotel_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.hotelItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Hotel hotel = mHotelList.get(position);
                Intent intent = new Intent(mContext, HotelDetails.class);
                intent.putExtra("name", hotel.getName());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hotel hotel = mHotelList.get(i);
        viewHolder.hotelName.setText(hotel.getName());
        viewHolder.hotelContent.setText(hotel.getContent());
        viewHolder.hotelPrice.setText(hotel.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(hotel.getImg_url())
                .into(viewHolder.hotelImg);
    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }
}
