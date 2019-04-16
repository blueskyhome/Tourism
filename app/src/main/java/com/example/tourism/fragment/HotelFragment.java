package com.example.tourism.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tourism.R;
import com.example.tourism.adapter.HotelAdapter;
import com.example.tourism.tools.Hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelFragment extends Fragment {

    private List<Hotel> mHotelList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HotelAdapter hotelAdapter;

    private String[] hotelName = new String[]{"希尔顿酒店","三亚海悦湾度假酒店","万豪国际酒店","三亚文华东方酒店"};
    private String[] hotelContent = new String[]{
            "希尔顿国际酒店集团(HI)，为总部设于英国的希尔顿集团公司旗下分支，拥有除美国外全球范围内“希尔顿”商标的使用权。",
            "三亚海悦湾度假酒店由锦江国际酒店管理有限公司上海和平饭店管理，素有“热带滨海花园酒店”之称。位于享有“椰阳梦长廊”美誉的三亚湾中心，咫尺碧波大海仅百米，酒店置身绿茵花园、环境优美迷人，热带花草相拥，蓝蓝的大海、洁白的沙滩、清新的空气、灿烂的阳光，构成了一个热带滨海花园王国。",
            "河源市迈豪国际酒店（原河源市万豪国际酒店）坐落于新河源市新市区东江与丰江交汇的沿江东路，由广东客家女旅游集团出资建设，是河源市首家五星级酒店。",
            "三亚文华东方酒店是文华东方酒店管理集团在中国大陆管理的首家豪华度假型酒店。酒店坐落于风光旖旎的珊瑚湾，距离三亚凤凰国际机场20公里、市中心3公里。酒店依山傍海，拥有1.2公里长的珊瑚湾，和281套全海景客房、套房、阁楼和别墅。"
    };
    private int[] hotelPrice = new int[]{2000, 3000, 5000, 2000};
    private String[] url = new String[]{
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ip571enj30d608s3yv.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ipewha3j30d609u74u.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22iplx3orj30d609vq3n.jpg",
            "https://ws1.sinaimg.cn/large/0077HGE3ly1g22ipsdbxrj30d4097t92.jpg"
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.hotel_fragment, null);
        initData();
        initView(view);
        return view;
    }

    private void initData() {
        mHotelList.clear();

        for (int i = 0; i < hotelName.length; i++) {
            Hotel hotel = new Hotel();

            hotel.setCover_url(url[i]);
            hotel.setName(hotelName[i]);
            hotel.setContent(hotelContent[i]);
            hotel.setPrice(hotelPrice[i]);

            mHotelList.add(hotel);
        }
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.hotel_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        hotelAdapter = new HotelAdapter(mHotelList);
        recyclerView.setAdapter(hotelAdapter);
    }
}
