package com.incresol.bemolpresalepoc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.Adapters.RecyclerViewAdapter;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;


public class Details_Product_Tab extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> imageUrls;
    public static ImageView image_product;
    RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_product_tab, container, false);
        image_product = (ImageView) view.findViewById(R.id.image_product);
        recyclerView = (RecyclerView) view.findViewById(R.id.m_recycler_view);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        imageUrls = new ArrayList<>();
        imageUrls.add("https://www.sugarsync.com/images/samsung-led-5300.png");
        imageUrls.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");
        imageUrls.add("http://samsung-tvs.co.uk/wp-content/uploads/2012/01/samsung-ue55d8000-televisions.png");
        imageUrls.add("https://www.sugarsync.com/images/samsung-led-5300.png");
        imageUrls.add("http://wirelesstradersinc.com/wp-content/uploads/2016/02/LED-TV-From-Samsung.png");
        imageUrls.add("http://samsung-tvs.co.uk/wp-content/uploads/2012/01/samsung-ue55d8000-televisions.png");
        imageUrls.add("https://www.sugarsync.com/images/samsung-led-5300.png");

        Glide.with(getActivity()).load(imageUrls.get(0).toString()).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(image_product);

        recyclerViewAdapter = new RecyclerViewAdapter(imageUrls,getActivity());
        recyclerView.setAdapter(recyclerViewAdapter);

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setRating(4f);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
