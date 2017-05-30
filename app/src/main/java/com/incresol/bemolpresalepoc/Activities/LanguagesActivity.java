package com.incresol.bemolpresalepoc.Activities;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.incresol.bemolpresalepoc.R;
import com.incresol.bemolpresalepoc.Utils.MyApplication;

import java.util.Locale;

/**
 * Created by incresol-026 on 28/4/17.
 */

public class LanguagesActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_english,button_hindi;
    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_languages);
        button_english = (Button) findViewById(R.id.button_english);
        button_hindi = (Button) findViewById(R.id.button_hindi);
        button_english.setOnClickListener(this);
        button_hindi.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("Language",MODE_PRIVATE);
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
                changeLanguage();
                break;
            case R.id.button_hindi:
                changeToLanguage="hi";
                editor.clear();
                editor.putString("changeToLanguage",changeToLanguage);
                editor.commit();
                changeLanguage();
                break;
        }
    }

    public void changeLanguage(){
        sharedPreferences = getApplicationContext().getSharedPreferences("Language",MODE_PRIVATE);
        String languageToLoad = sharedPreferences.getString("changeToLanguage",null);
        if(languageToLoad==null){
            languageToLoad = "en";
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.putString("changeToLanguage",languageToLoad);
            editor.commit();

        }
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }
}
