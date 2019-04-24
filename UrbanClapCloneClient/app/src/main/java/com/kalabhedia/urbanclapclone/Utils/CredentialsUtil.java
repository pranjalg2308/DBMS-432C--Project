package com.kalabhedia.urbanclapclone.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class CredentialsUtil {

    private String CREDENTIAL = "LogCredential";

    public boolean isUserLogged(Context context) {
        if (getUsername(context) != null)
            return true;
        return false;
    }

    public void logOut(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE).edit();
        editor.putString("UserName", null);
        editor.apply();
    }

    public String getUsername(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE);
        String userName = prefs.getString("UserName", null);
        return userName;
    }

    public boolean saveLogIn(Context context, String userName) {
        SharedPreferences.Editor editor = context.getSharedPreferences(CREDENTIAL, MODE_PRIVATE).edit();
        editor.putString("UserName", userName);
        editor.apply();
        return true;
    }


}
