package com.incresol.bemolpresalepoc.Utils;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

/**
 * Created by incresol-026 on 26/4/17.
 */

public class Utils {

    public static void changeLanguage(Context context, String languageToLoad){
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config,
                context.getResources().getDisplayMetrics());
    }

}
