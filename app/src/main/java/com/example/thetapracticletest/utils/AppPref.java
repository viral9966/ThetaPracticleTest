package com.example.thetapracticletest.utils;

import android.content.Context;
import android.content.SharedPreferences;


public class AppPref {
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public AppPref(Context context) {
        sharedPref = context.getSharedPreferences(AppConstants.AppName, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    public String getLoggedInUser() {
        return  sharedPref.getString(AppConstants.GetLoggedInStatusKey, AppConstants.GetLoggedInStatusDefaultValue);
    }

    public void setLoggedInUser(String rememberMyAuth) {
        editor.putString(AppConstants.GetLoggedInStatusKey, rememberMyAuth);
        editor.commit();
    }

    public void clear() {
            editor.clear();
            editor.commit();
            editor.apply();
    }

}
