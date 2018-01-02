package com.robj.ratingmanager;

import android.content.Context;

import java.util.concurrent.TimeUnit;

/**
 * Created by jj on 01/01/18.
 */

final class DataManager {

    protected static final String RATED = "RATED";
    protected static final String INSTALL_DATE = "INSTALL_DATE";
    protected static final String LEFT_FEEDBACK_DATE = "LEFT_FEEDBACK_DATE";
    protected static final String LEFT_FEEDBACK_BUILD = "LEFT_FEEDBACK_BUILD";
    protected static final String ASK_LATER_DATE = "ASK_LATER_DATE";
    protected static final String NEVER_ASK = "NEVER_ASK";

    private static long getNow() {
        return System.currentTimeMillis();
    }

    private static long getDaysSince(Context context, long date) {
        long today = getNow();
        long diff = today - date;
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public static void setRatingLeft(Context context) {
        PrefUtils.writeBoolPref(context, RATED, true);
    }

    public static boolean wasRatingLeft(Context context) {
        return PrefUtils.readBooleanPref(context, RATED);
    }

    public static long getDaysSinceInstall(Context context) {
        long installDate = PrefUtils.readLongPref(context, INSTALL_DATE);
        if(installDate == 0) {
            installDate = getNow();
            PrefUtils.writeLongPref(context, INSTALL_DATE, installDate);
        }
        return getDaysSince(context, installDate);
    }

    public static boolean isNeverAsk(Context context) {
        return PrefUtils.readBooleanPref(context, NEVER_ASK);
    }

    public static void setNeverAsk(Context context) {
        PrefUtils.writeBoolPref(context, NEVER_ASK, true);
    }

    public static boolean wasFeedbackLeft(Context context) {
        return PrefUtils.readLongPref(context, LEFT_FEEDBACK_DATE) > 0;
    }

    public static long getDaysSinceFeedbackLeft(Context context) {
        long feedbackLeftDate = PrefUtils.readLongPref(context, LEFT_FEEDBACK_DATE);
        return getDaysSince(context, feedbackLeftDate);
    }

    public static int getFeedbackLeftBuild(Context context) {
        return PrefUtils.readIntPref(context, LEFT_FEEDBACK_BUILD);
    }

    public static void setFeedbackLeft(Context context) {
        PrefUtils.writeLongPref(context, LEFT_FEEDBACK_DATE, getNow());
        int versionCode = getCurrentVersionCode(context);
        PrefUtils.writeIntPref(context, LEFT_FEEDBACK_BUILD, versionCode);
    }

    public static int getCurrentVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getDaysSinceAskLater(Context context) {
        long askLaterDate = PrefUtils.readLongPref(context, ASK_LATER_DATE);
        return getDaysSince(context, askLaterDate);
    }

    public static boolean wasAskLater(Context context) {
        return PrefUtils.readLongPref(context, ASK_LATER_DATE) > 0;
    }

    public static void setAskLater(Context context) {
        PrefUtils.writeLongPref(context, ASK_LATER_DATE, getNow());
    }

}
