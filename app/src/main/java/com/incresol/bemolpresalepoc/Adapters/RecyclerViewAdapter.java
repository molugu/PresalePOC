package com.incresol.bemolpresalepoc.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.R;
import com.incresol.bemolpresalepoc.fragments.Details_Product_Tab;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<String> imageUrls;
    Context context;

    public RecyclerViewAdapter(ArrayList<String> imageUrls, Context context){
        this.imageUrls = imageUrls;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView_product;
        public ArrayList<String> imagesUrls = new ArrayList<>();

        public ViewHolder(View itemView,Context context,ArrayList<String> imageUrls) {
            super(itemView);
            imageView_product = (ImageView) itemView.findViewById(R.id.imageView_product);
            this.imagesUrls = imageUrls;
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, context, imageUrls);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(imageUrls.get(position).toString()).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(holder.imageView_product);

        holder.imageView_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Glide.with(context).load(imageUrls.get(position).toString()).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(Details_Product_Tab.image_product);

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}
