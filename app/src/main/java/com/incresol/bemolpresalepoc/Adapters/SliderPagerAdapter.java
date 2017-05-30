package com.incresol.bemolpresalepoc.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;

public class SliderPagerAdapter extends PagerAdapter {
   private LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<String> image_arraylist;
    public static ImageView im_slider;

    public SliderPagerAdapter(Activity activity, ArrayList<String> image_arraylist) {
        this.activity = activity;
        this.image_arraylist = image_arraylist;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.layout_slider, container, false);
        im_slider = (ImageView) view.findViewById(R.id.im_slider);
        Glide.with(activity.getApplicationContext())
                .load(image_arraylist.get(position))
                .placeholder(R.drawable.ic_action_download) // optional
                .error(R.drawable.ic_action_error)         // optional
                .into(im_slider);


        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return image_arraylist.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}