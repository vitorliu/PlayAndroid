
package com.example.administrator.playandroid.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.example.administrator.playandroid.App;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.util.HashSet;

public class SharedPreHelper {

    public static final String PREF_COOKIES = "cookie";
    private static final String SP_NAME = "play_android";

    private static SharedPreHelper mHelper;
    private static SharedPreferences sp;

    private SharedPreHelper() {
        sp = App.getInstance().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public synchronized static SharedPreHelper getSPHelper() {
        if (null == mHelper) {
            mHelper = new SharedPreHelper();
        }
        return mHelper;
    }

    public HashSet<String> getStringSet(String pPrefCookies) {
        Gson gson = new Gson();
        HashSet<String> cookies = new HashSet<>();
        try {
            cookies = gson.fromJson(sp.getString(pPrefCookies, ""),
                    new TypeToken<HashSet<String>>() {
                    }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }

    public void putStringSet(String pPrefCookies, HashSet<String> pCookies) {
        Gson gson = new Gson();
        Editor edit = sp.edit();
        edit.putString(pPrefCookies, gson.toJson(pCookies));
        edit.apply();
    }

}
