package com.example.abrahamnews.setting;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingSharedPrefManager {
    private static final String EXTRA_KEY_LAN = "lang";
    private SharedPreferences sharedPreferences;
    private static SettingSharedPrefManager instance;


    private SettingSharedPrefManager(Context context){
        sharedPreferences=context.getSharedPreferences("setting", Context.MODE_PRIVATE);

    }
    public static SettingSharedPrefManager getInstance(Context context){
        if(instance==null){
            instance=new SettingSharedPrefManager(context);
        }
        return instance;

    }
    public void savedDefaultLanguage(String language){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(EXTRA_KEY_LAN,language);
        editor.commit();
    }
    public String getDefaultLanguage(){
        return sharedPreferences.getString(EXTRA_KEY_LAN,"en" );
    }

}
