package com.example.thetapracticletest.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AlertDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppConstants {

    public static final String AuthUserKey = "test@123.com";
    public static final String AuthUserPassValue = "123456";
    public static final String AppName = "theta";
    public static final String GetLoggedInStatusKey = "isUserLoggedIn";
    public static final String GetLoggedInStatusValue = "1";
    public static final String GetLoggedInStatusDefaultValue = "0";

    //Set Tabbar Titles
    public static final String SetTitleHome = "Home";
    public static final String SetTitleMap = "Map";
    public static final String SetTitleProfile = "Profile";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    public static void ShowNewAlert(Context context,String message) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }

}
