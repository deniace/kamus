package com.deni.kamusbahasajawa.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Deni Supriyatna on 13 - Nov - 2019.
 */
public class SharedPref {
    private static final String IS_INIT = "is_init";
    private boolean isInit = false;

    private Context context;

    private SharedPreferences preferences;

    public SharedPref(Context context){
        this.context = context;
        preferences = context.getSharedPreferences("IndoJawa", Context.MODE_PRIVATE);
    }


    public boolean isInit(){
        isInit = preferences.getBoolean(IS_INIT,false);
        return isInit;
    }

    public void setIsInit(boolean isInit){
        this.isInit = isInit;
        preferences.edit().putBoolean(IS_INIT,isInit).apply();
    }
}
