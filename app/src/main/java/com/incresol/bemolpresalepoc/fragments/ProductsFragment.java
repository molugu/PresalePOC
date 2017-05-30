package com.incresol.bemolpresalepoc.fragments;

import android.app.Activity;
import android.media.JetPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.incresol.bemolpresalepoc.Adapters.ListAdapterProductsFragment;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;

public class ProductsFragment extends Fragment {

    ListAdapterProductsFragment listAdapterProductsFragment;
    ArrayList arrayList_products = new ArrayList();
    public static ArrayList arrayList_selectedItems;
    ListView listView;
    RelativeLayout toplayout;
    RelativeLayout cl_fab_resultsCount;
    Button button_filter,button_productsCompare;
    FloatingActionButton fab_more,fab_resultsCount;
    DrawerLayout drawer;
    private static BottomSheetBehavior mBottomSheetBehavior;
    static ImageView image_sheet_down, image_productCompare_1, image_productCompare_2;
    TextView header_resultsCount,bottom_resultsCount;
    static Activity activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);

        arrayList_products.add("Android");
        arrayList_products.add("samsung");
        arrayList_products.add("iphone");
        arrayList_products.add("Smart TV 4 LED Full HD Samsung UN40J5200 Entradas 2 HDMI 1 USB RCA");
        arrayList_products.add("television");
        arrayList_products.add("smart tv");
        arrayList_products.add("electronix");
        arrayList_products.add("earphones");
        arrayList_products.add("washing machine");
        arrayList_products.add("vaccum cleaner");
        arrayList_products.add("nokia");
        arrayList_products.add("oppo");
        arrayList_products.add("phone cover");
        arrayList_products.add("shoe");
        arrayList_products.add("mens collection");
        arrayList_products.add("dress");
        arrayList_products.add("laptop");
        arrayList_products.add("speakers");
        arrayList_products.add("shirts");
        arrayList_products.add("bat");
        arrayList_products.add("jeans");
        arrayList_products.add("women collection");

        activity = getActivity();
        arrayList_selectedItems = new ArrayList();

        listView = (ListView)view.findViewById(R.id.list_view);
        listAdapterProductsFragment= new ListAdapterProductsFragment(arrayList_products,getActivity());
        listView.setAdapter(listAdapterProductsFragment);

        toplayout = (RelativeLayout) view.findViewById(R.id.toplayout);
        fab_more = (FloatingActionButton) view.findViewById(R.id.fab_more);
        fab_resultsCount = (FloatingActionButton) view.findViewById(R.id.fab_resultsCount);
        cl_fab_resultsCount = (RelativeLayout) view.findViewById(R.id.cl_fab_resultsCount);
        header_resultsCount = (TextView) view.findViewById(R.id.header_resultsCount);
        bottom_resultsCount = (TextView) view.findViewById(R.id.bottom_resultsCount);
        image_productCompare_1 = (ImageView) view.findViewById(R.id.image_productCompare_1);
        image_productCompare_2 = (ImageView) view.findViewById(R.id.image_productCompare_2);

        header_resultsCount.setText(arrayList_products.size()+" Resultados");
        bottom_resultsCount.setText(arrayList_products.size()+"");

        button_filter = (Button) view.findViewById(R.id.filter);
        button_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);
                drawer.openDrawer(Gravity.END);
            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

               if(scrollState == SCROLL_STATE_IDLE){
                   button_filter.setVisibility(View.VISIBLE);
                   toplayout.setVisibility(View.VISIBLE);
                   cl_fab_resultsCount.setVisibility(View.INVISIBLE);
               }else {
                   toplayout.setVisibility(View.GONE);
                   fab_more.setVisibility(View.GONE);
                   cl_fab_resultsCount.setVisibility(View.VISIBLE);
               }
            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        //  Bottom sheet init
        View bottomSheet = view.findViewById( R.id.bottom_sheet );
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setPeekHeight(0);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        image_sheet_down = (ImageView) bottomSheet.findViewById(R.id.image_sheet_down);
        button_productsCompare = (Button) bottomSheet.findViewById(R.id.button_productsCompare);
        button_productsCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.support.v4.app.Fragment fragment = null;
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                fragment = new CompareFragment();
                ft.addToBackStack("CompareFragment");

                if (fragment != null && ft != null) {
                    ft.replace(R.id.content_frame, fragment);
                    ft.commit();
                }

            }
        });

        image_sheet_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomSheetBehavior.setPeekHeight(130);
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                image_sheet_down.setVisibility(View.GONE);
            }
        });

        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    mBottomSheetBehavior.setPeekHeight(130);
                    mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    image_sheet_down.setVisibility(View.GONE);
                }
                if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    image_sheet_down.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void showBottomSheet(String productId){

        if(arrayList_selectedItems.size()!=0){
            if(!arrayList_selectedItems.contains(productId) && arrayList_selectedItems.size()!=2){
                arrayList_selectedItems.add(productId);
            }
        }else{
            arrayList_selectedItems.add(productId);
        }
        String url= "https://www.sugarsync.com/images/samsung-led-5300.png";
         Glide.with(activity).load(url).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(image_productCompare_1);


        /*if(arrayList_selectedItems.size()==1){
            String product_id = arrayList_selectedItems.get(0).toString();
            String url= "https://www.sugarsync.com/images/samsung-led-5300.png";
           // Glide.with().load(url).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(image_productCompare_1);

        }else{
            String product_id = arrayList_selectedItems.get(1).toString();
            String url1= "https://www.sugarsync.com/images/samsung-led-5300.png";
            //Glide.with().load(url1).placeholder(R.drawable.ic_action_download).error(R.drawable.ic_action_error).into(image_productCompare_2);

        }*/
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        image_sheet_down.setVisibility(View.VISIBLE);
    }

}
