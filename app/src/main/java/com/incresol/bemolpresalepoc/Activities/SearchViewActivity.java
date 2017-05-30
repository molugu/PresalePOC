package com.incresol.bemolpresalepoc.Activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.incresol.bemolpresalepoc.Adapters.ListAdapterSearchActivity;
import com.incresol.bemolpresalepoc.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchViewActivity extends AppCompatActivity {

    ListAdapterSearchActivity adapter;
    List arrayList= new ArrayList();
    ListView listView ;
    SearchView search;
    ImageView imageView_actionback;
    public static String searchTerm;
    public static SharedPreferences sharedPreferences;
    public static ArrayList<String> globalHistory = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        arrayList.add("samsung");
        arrayList.add("iphone");
        arrayList.add("television");
        arrayList.add("smart tv");
        arrayList.add("electronix");
        arrayList.add("earphones");
        arrayList.add("washing machine");
        arrayList.add("vaccum cleaner");
        arrayList.add("nokia");
        arrayList.add("oppo");
        arrayList.add("phone cover");
        arrayList.add("shoe");
        arrayList.add("mens collection");
        arrayList.add("dress");
        arrayList.add("laptop");
        arrayList.add("speakers");
        arrayList.add("shirts");
        arrayList.add("bat");
        arrayList.add("jeans");
        arrayList.add("women collection");

        sharedPreferences = getSharedPreferences("QueryHistory",MODE_PRIVATE);

        listView = (ListView)findViewById(R.id.list_view);
        search = (SearchView) findViewById(R.id.search);
        imageView_actionback = (ImageView)findViewById(R.id.action_back);
        imageView_actionback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if(searchTerm!=null && searchTerm.length() !=0){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    ArrayList<String> historyString = new ArrayList<String>();
                    Map map = sharedPreferences.getAll();
                    if(map!=null && map.size()!=0) {
                        Set keySet = map.keySet();
                        Iterator iterator = keySet.iterator();

                        while (iterator.hasNext()) {
                            String key = (String) iterator.next();
                            String value = (String) map.get(key);
                            historyString.add(value);
                        }
                        boolean contains = historyString.contains(listView.getItemAtPosition(position).toString());
                        if (!contains) {
                            editor.putString(listView.getItemAtPosition(position).toString(), listView.getItemAtPosition(position).toString());
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"Added to History",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getApplicationContext(),"Already in History",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        editor.putString(listView.getItemAtPosition(position).toString(), listView.getItemAtPosition(position).toString());
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Added to History",Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        ImageView magImage = (ImageView) search.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        magImage.setVisibility(View.GONE);
        magImage.setImageDrawable(null);

        TextView textView = (TextView) search.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(getResources().getColor(R.color.colorLightBlue));

        search.setActivated(true);
        search.setQueryHint("Buscar");
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                searchTerm = newText;
                if(newText!=null && newText.length() !=0) {
                    adapter= new ListAdapterSearchActivity(arrayList);
                    listView.setAdapter(adapter);
                    adapter.getFilter().filter(newText);
                }else{
                    adapter=null;
                    listView.setAdapter(null);

                    ArrayList<String> history = loadHistoryQueries();
                    adapter = new ListAdapterSearchActivity(history);
                    listView.setAdapter(adapter);
                }
                return false;
            }
        });

        ArrayList<String> historyArray = loadHistoryQueries();
        if(historyArray!=null && historyArray.size() !=0){
            adapter = new ListAdapterSearchActivity(historyArray);
            listView.setAdapter(adapter);
        }
    }

    public static ArrayList<String> loadHistoryQueries(){

        Map map = sharedPreferences.getAll();
        if(map!=null && map.size()!=0) {
            globalHistory.clear();
            Set keySet = map.keySet();
            Iterator iterator = keySet.iterator();

            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = (String) map.get(key);

                globalHistory.add(value);
            }
        }
        return globalHistory;

    }


}