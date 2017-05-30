package com.incresol.bemolpresalepoc.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.incresol.bemolpresalepoc.R;
import com.incresol.bemolpresalepoc.Utils.MyApplication;

import static android.content.Context.MODE_PRIVATE;

public class LanguagesFragment extends Fragment implements View.OnClickListener {

    Button button_english,button_hindi,button_marathi;
    public static SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_languages, container, false);
        button_english = (Button) view.findViewById(R.id.button_english);
        button_hindi = (Button) view.findViewById(R.id.button_hindi);
        button_marathi = (Button) view.findViewById(R.id.button_marathi);
        button_english.setOnClickListener(this);
        button_hindi.setOnClickListener(this);
        button_marathi.setOnClickListener(this);
        sharedPreferences = getActivity().getSharedPreferences("Language",MODE_PRIVATE);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View view) {
        String changeToLanguage;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int id = view.getId();
        switch (id){
            case R.id.button_english:
                changeToLanguage="en";
                editor.clear();
                editor.putString("changeToLanguage",changeToLanguage);
                editor.commit();
                MyApplication.changeLanguage();
                break;
            case R.id.button_hindi:
                changeToLanguage="hi";
                editor.clear();
                editor.putString("changeToLanguage",changeToLanguage);
                editor.commit();
                MyApplication.changeLanguage();
                break;
            case R.id.button_marathi:
                changeToLanguage="mr";
                editor.clear();
                editor.putString("changeToLanguage",changeToLanguage);
                editor.commit();
                MyApplication.changeLanguage();
                break;
        }
    }
}

