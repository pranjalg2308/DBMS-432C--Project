package com.kalabhedia.urbanclapclone.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CredentialsUtil {

    private static String CREDENTIAL = "LogCredential";

    public static boolean isUserLogged(Context context) {
        if (getUsername(context) != null)
            return true;
        return false;
    }

    public static void logOut(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE).edit();
        editor.putString("UserName", null);
        editor.apply();
    }

    public static String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE);
        //TODO
        String userName = prefs.getString("UserName", null);
        return userName;
    }

    public static boolean saveLogIn(Context context, String userName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE).edit();
        editor.putString("UserName", userName);
        editor.apply();
        return true;
    }

    public static String getCity(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE);
        String city = prefs.getString("city", null);
        return city;
    }

    public static void saveCity(Context context, String city) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE).edit();
        editor.putString("city", city);
        editor.apply();
    }

}
