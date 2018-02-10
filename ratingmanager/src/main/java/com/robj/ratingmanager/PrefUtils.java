package com.robj.ratingmanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rob J on 23/05/15.
 */
class PrefUtils {

    private final static String PREFS = "_RatingManagerPrefs";

    public static void writeLongPref(Context context, String name, long l) {
        SharedPreferences.Editor editor = getSharedPrefs(context).edit();
        editor.putLong(name, l);
        editor.apply();
    }

    public static void writeIntPref(Context context, String name, int i) {
        SharedPreferences.Editor editor = getSharedPrefs(context).edit();
        editor.putInt(name, i);
        editor.apply();
    }

    public static void writeBoolPref(Context context, String name, boolean b) {
        SharedPreferences.Editor editor = getSharedPrefs(context).edit();
        editor.putBoolean(name, b);
        editor.apply();
    }

    public static int readIntPref(Context context, String name) {
        SharedPreferences sp = getSharedPrefs(context);
        return sp.getInt(name, 0);
    }

    public static long readLongPref(Context context, String name) {
        SharedPreferences sp = getSharedPrefs(context);
        return sp.getLong(name, 0);
    }

    public static boolean readBooleanPref(Context context, String name) {
        SharedPreferences sp = getSharedPrefs(context);
        return sp.getBoolean(name, false);
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
    }

}
