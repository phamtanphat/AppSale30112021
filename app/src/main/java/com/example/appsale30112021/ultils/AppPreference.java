package com.example.appsale30112021.ultils;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppPreference {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Inject
    public AppPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(AppConstant.SHAREPREF_NAMES,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveToken(String token){
        editor.putString(AppConstant.TOKEN_KEY , token);
        editor.commit();
    }

    public String getToken(){
        return sharedPreferences.getString(AppConstant.TOKEN_KEY,"");
    }
}
