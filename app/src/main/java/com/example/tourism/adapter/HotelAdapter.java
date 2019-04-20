package com.example.tourism.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tourism.R;
import com.example.tourism.tools.Hotel;
import com.example.tourism.tools.MyApplication;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

    private List<Hotel> mHotelList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView hotelImg;
        private TextView hotelName;
        private TextView hotelContent;
        private TextView hotelPrice;

        public ViewHolder (View view) {
            super(view);
            hotelImg = (ImageView) view.findViewById(R.id.hotel_item_image_cover);
            hotelName = (TextView) view.findViewById(R.id.hotel_item_text_title);
            hotelContent = (TextView) view.findViewById(R.id.hotel_item_text_context);
            hotelPrice = (TextView) view.findViewById(R.id.hotel_item_text_money_context);
        }
    }

    public HotelAdapter (List<Hotel> hotelList) {
        mHotelList = hotelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotel_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Hotel hotel = mHotelList.get(i);
        viewHolder.hotelName.setText(hotel.getName());
        viewHolder.hotelContent.setText(hotel.getContent());
        viewHolder.hotelPrice.setText(hotel.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(hotel.getCover_url())
                .into(viewHolder.hotelImg);
    }

    @Override
    public int getItemCount() {
        return mHotelList.size();
    }
}
