package com.ciphercrypt.reactiveview;

import android.content.Context;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.Locale;

/**
 * Created by user on 18/06/2018.
 */

public class ReactSetting {
    private static ReactSetting instances = null;
    private WeakReference<Context> context = null;

    private ReactSetting(WeakReference<Context> context){
        this.context = context;
    }

    public static ReactSetting getInstances(Context context){
        synchronized (ReactSetting.class){
            if(instances == null){
                synchronized (ReactSetting.class){
                    instances = new ReactSetting(new WeakReference<>(context));
                }
            }
            return instances;
        }
    }

    public ReactSetting changeLanguage(Locale locale, boolean save){
        Locale.setDefault(locale);

        if(save) {
            context.get().getSharedPreferences(ReactConstant.SHAREDPREF_FILE, Context.MODE_PRIVATE)
                    .edit()
                    .putString(ReactConstant.SETTING_LOCALE, new Gson().toJson(locale))
                    .apply();
        }

        return this;
    }

    public ReactSetting changeLanguage(Locale locale){
        return changeLanguage(locale, true);
    }

    public void loadSavedSetting(){
        Locale locale = new Gson().fromJson(context.get()
                .getSharedPreferences(ReactConstant.SHAREDPREF_FILE, Context.MODE_PRIVATE)
                .getString(ReactConstant.SETTING_LOCALE, ""), Locale.class);

        changeLanguage(locale, false);
    }
}
