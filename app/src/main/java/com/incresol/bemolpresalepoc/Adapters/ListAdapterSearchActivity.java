package com.incresol.bemolpresalepoc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.incresol.bemolpresalepoc.R;
import com.incresol.bemolpresalepoc.Activities.SearchViewActivity;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterSearchActivity extends BaseAdapter implements Filterable {

    List mData;
    List mStringFilterList;
    ValueFilter valueFilter;
    private LayoutInflater inflater;

    public ListAdapterSearchActivity(List cancel_type) {
        mData=cancel_type;
        mStringFilterList = cancel_type;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

public class ViewHolder {
    TextView name;
    ImageView image_history;
}

    public View getView(final int position, View view, ViewGroup parent)
    {
        if (inflater == null) {
            inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        final ViewHolder holder;
        if (view == null)
        {
                holder = new ViewHolder();
                view = inflater.inflate(R.layout.row_item_search_results, null);
                // Locate the TextViews in listview_item.xml
                 holder.name = (TextView) view.findViewById(R.id.stringName);
            holder.image_history = (ImageView) view.findViewById(R.id.image_history);

            if(SearchViewActivity.searchTerm != null && SearchViewActivity.searchTerm.length() !=0){
                holder.image_history.setVisibility(View.INVISIBLE);
            }else{
                holder.image_history.setVisibility(View.VISIBLE);
            }
                view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
            // Set the results into TextViews
             holder.name.setText(mData.get(position).toString());
            return view;
    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List filterList = new ArrayList();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).toString().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List) results.values;
            notifyDataSetChanged();
        }

    }

}