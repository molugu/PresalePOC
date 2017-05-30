package com.incresol.bemolpresalepoc.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incresol.bemolpresalepoc.R;

/**
 * Created by incresol-026 on 8/5/17.
 */

public class Sales_Product_Tab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sales_product_tab, container, false);
        return view;
    }
}
