package com.robj.ratingmanager;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by Rob J on 01/01/18.
 */

public class RatingManagerMockBuilder {

    private final Context context;

    private Boolean isRated;
    private Boolean isNeverAsk;
    private Integer daysSinceInstall;
    private Integer daysSinceFeedback;
    private Integer feedbackBuild;
    private Integer daysSinceAskLater;

    public RatingManagerMockBuilder(Context context) {
        this.context = context;
    }

    public RatingManagerMockBuilder setDaysSinceInstall(int daysSinceInstall) {
        this.daysSinceInstall = daysSinceInstall;
        return this;
    }

    public RatingManagerMockBuilder setRated(boolean rated) {
        isRated = rated;
        return this;
    }

    public RatingManagerMockBuilder setNeverAsk(boolean neverAsk) {
        isNeverAsk = neverAsk;
        return this;
    }

    public RatingManagerMockBuilder setFeedbackBuild(int feedbackBuild) {
        this.feedbackBuild = feedbackBuild;
        return this;
    }

    public RatingManagerMockBuilder setDaysSinceAskLater(int daysSinceAskLater) {
        this.daysSinceAskLater = daysSinceAskLater;
        return this;
    }

    public RatingManagerMockBuilder setDaysSinceFeedback(Integer daysSinceFeedback) {
        this.daysSinceFeedback = daysSinceFeedback;
        return this;
    }

    public void build() {
        if(isRated != null)
            PrefUtils.writeBoolPref(context, DataManager.RATED, isRated);
        if(isNeverAsk != null)
            PrefUtils.writeBoolPref(context, DataManager.NEVER_ASK, isNeverAsk);
        if(feedbackBuild != null)
            PrefUtils.writeIntPref(context, DataManager.LEFT_FEEDBACK_BUILD, feedbackBuild);
        if(daysSinceInstall != null)
            setDaysSince(DataManager.INSTALL_DATE, daysSinceInstall);
        if(daysSinceFeedback != null)
            setDaysSince(DataManager.LEFT_FEEDBACK_DATE, daysSinceFeedback);
        if(daysSinceAskLater != null)
            setDaysSince(DataManager.ASK_LATER_DATE, daysSinceAskLater);

    }

    private void setDaysSince(String key, int daysSince) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -daysSince);
        PrefUtils.writeLongPref(context, key, calendar.getTimeInMillis());
    }

}
