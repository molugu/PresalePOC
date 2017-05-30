package com.incresol.bemolpresalepoc.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.R;
import com.incresol.bemolpresalepoc.fragments.ProductsFragment;

import java.net.URL;
import java.util.ArrayList;

public class ListAdapterProductsFragment extends BaseAdapter {

    ArrayList arrayList_products;
    private LayoutInflater inflater;
    Context context;

    public ListAdapterProductsFragment(ArrayList list_products,Context context){
        this.arrayList_products = list_products;
        this.context = context;
    }


    @Override
    public int getCount() {
        return arrayList_products.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList_products.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView product_name,discount,loja_store,cd_store;
        ImageView imageView_product,compare_product;
        RatingBar ratingBar;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        if(inflater == null){
            inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        final ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.row_item_product_display, null);

            holder.product_name = (TextView) view.findViewById(R.id.product_name);
            holder.imageView_product = (ImageView) view.findViewById(R.id.imageView_product);
            holder.discount = (TextView) view.findViewById(R.id.discount);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            holder.compare_product = (ImageView)view.findViewById(R.id.compare_product);
            holder.loja_store = (TextView) view.findViewById(R.id.loja_store);
            holder.cd_store = (TextView) view.findViewById(R.id.cd_store);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.product_name.setText(arrayList_products.get(position).toString());
        holder.discount.setPaintFlags(holder.discount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Double i=3.000000;
        holder.ratingBar.setRating((float)i.doubleValue());

        holder.compare_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"item clicked -> "+position,Toast.LENGTH_SHORT).show();
                ProductsFragment.showBottomSheet("id");
            }
        });

        holder.loja_store.setText("01");
        holder.loja_store.setTextColor(Color.RED);
        holder.cd_store.setText("12");

        String url= "https://www.sugarsync.com/images/samsung-led-5300.png";
        Glide.with(view.getContext()).load(url).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(holder.imageView_product);

        return view;
    }
}
