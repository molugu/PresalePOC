package com.incresol.bemolpresalepoc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.incresol.bemolpresalepoc.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button products;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        products = (Button) view.findViewById(R.id.products);
        products.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        android.support.v4.app.Fragment fragment = null;
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        switch (id){
            case R.id.products:
                fragment = new ProductsFragment();
                ft.addToBackStack("ProductsFragment");
                break;
        }
        if (fragment != null && ft != null) {
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }
}
