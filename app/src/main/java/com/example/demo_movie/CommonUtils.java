package com.example.demo_movie;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class CommonUtils {
    //private static final String PREF_FILE = "pref_file";
    private static final String FILE_NAME = "file_share_pref";
    public static final int LEVEL_DEBUG = 1;
    private static final int LEVEL_INFO = 2;
    private static CommonUtils instance;
    public static int logLevel = LEVEL_INFO;

    private CommonUtils() {
        //for singleton
    }

    public static CommonUtils getInstance() {
        if (instance == null) {
            instance = new CommonUtils();
        }
        return instance;
    }

    public static void logI(String tag, String msg) {
        if (logLevel == LEVEL_DEBUG) {
            System.out.println(tag + ": " + msg);
        } else {
            Log.i(tag, msg);
        }
    }

    //SHARE PREFERENCE
    public void savePref(String key, String reqToken) {
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(key, reqToken).apply();
    }

    public void clearPref(String key) { // xoa du lieu
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        pref.edit().remove(key).apply();
    }

    public boolean isExistPref(String key) { // kiem tra ton tai
        SharedPreferences pref = App.getInstance().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return pref.contains(key);
    }
}
