package com.robj.ratingmanager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by JJ on 23/05/15.
 */
class PrefUtils {

    private final static String PREFS = "_RatingManagerPrefs";

    public static void writeLongPref(Context context, String name, long l) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        editor.putLong(name, l);
        editor.apply();
    }

    public static void writeIntPref(Context context, String name, int i) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        editor.putInt(name, i);
        editor.apply();
    }

    public static void writeBoolPref(Context context, String name, boolean b) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        editor.putBoolean(name, b);
        editor.apply();
    }

    public static int readIntPref(Context context, String name) {
        SharedPreferences sp = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
        return sp.getInt(name, 0);
    }

    public static long readLongPref(Context context, String name) {
        SharedPreferences sp = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
        return sp.getLong(name, 0);
    }

    public static boolean readBooleanPref(Context context, String name) {
        SharedPreferences sp = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
        return sp.getBoolean(name, false);
    }

    public static void writeStringPref(Context context, String name, String s) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        editor.putString(name, s);
        editor.apply();
    }

    public static void writeFloatPref(Context context, String name, float f) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        editor.putFloat(name, f);
        editor.apply();
    }

    public static String readStringPref(Context context, String name) {
        SharedPreferences sp = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
        return sp.getString(name, "");
    }

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE);
    }

    public static void removePrefs(Context context, String[] prefs) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(context.getPackageName() + PREFS, Context.MODE_PRIVATE).edit();
        for(String pref : prefs)
            editor.remove(pref);
        editor.apply();
    }

}
