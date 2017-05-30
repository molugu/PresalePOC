package com.incresol.bemolpresalepoc.Utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;


public class MyApplication extends Application {

    static Context context;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        changeLanguage();
    }

    public static void changeLanguage(){
        sharedPreferences = context.getApplicationContext().getSharedPreferences("Language",MODE_PRIVATE);
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
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

}
