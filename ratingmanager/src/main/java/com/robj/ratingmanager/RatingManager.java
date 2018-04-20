package com.robj.ratingmanager;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rob J on 16/10/17.
 */

public class RatingManager {

    private static final String TAG = RatingManager.class.getSimpleName();

    private final Context context;
    private final int minDaysSinceInstall;
    private final int minDaysSinceFeedback;
    private final int minDaysSinceAskLater;
    private final RatingDialog ratingDialog;

    private List<Rule> rules = new ArrayList();

    private RatingManager(Builder builder) {
        this.context = builder.context;
        this.minDaysSinceInstall = builder.minDaysSinceInstall;
        this.minDaysSinceFeedback = builder.minDaysSinceFeedback;
        this.minDaysSinceAskLater = builder.minDaysSinceAskLater;
        this.rules = builder.rules;
        if(builder.ratingDialogOptions == null) {
            RatingDialogOptions.Builder ratingDialogOptionsBuilder = new RatingDialogOptions.Builder(context);
            builder.ratingDialogOptions = ratingDialogOptionsBuilder.build();
        }
        this.ratingDialog = new RatingDialog(builder.ratingDialogOptions);
        this.ratingDialog.setShowFeedbackOption(builder.showFeedbackOptionFirst, builder.feedbackEmailAddress);
    }

    private Context getContext() {
        return context;
    }

    public static void setRatingLeft(Context context) {
        DataManager.setRatingLeft(context);
    }

    public static void setAskLater(Context context) {
        DataManager.setAskLater(context);
    }

    public boolean showDialogIfRequired() {
        if(shouldShowRatingDialog()) {
            ratingDialog.showRatingPopup(getContext());
            return true;
        }
        return false;
    }

    public void forceShowDialog() {
        ratingDialog.showRatingPopup(getContext());
    }

    public boolean shouldShowRatingDialog() {
        if(DataManager.wasRatingLeft(getContext())) {
            Log.i(TAG, "Rating already left, nothing doing..");
            return false;
        }
        if(DataManager.isNeverAsk(getContext())) {
            Log.i(TAG, "Never ask was checked, nothing doing..");
            return false;
        }
        if(DataManager.getDaysSinceInstall(getContext()) < minDaysSinceInstall) {
            Log.i(TAG, "Days since install not met, nothing doing..");
            return false;
        }
        if(DataManager.wasFeedbackLeft(getContext())) {
            int feedbackLeftBuild = DataManager.getFeedbackLeftBuild(getContext());
            if(feedbackLeftBuild >= DataManager.getCurrentVersionCode(getContext())) {
                Log.i(TAG, "Feedback left for build " + feedbackLeftBuild + ", nothing doing..");
                return false;
            }
            if(DataManager.getDaysSinceFeedbackLeft(getContext()) < minDaysSinceFeedback) {
                Log.i(TAG, "Days since feedback left not met, nothing doing..");
                return false;
            }
        }
        if(DataManager.wasAskLater(getContext()) && DataManager.getDaysSinceAskLater(getContext()) < minDaysSinceAskLater) {
            Log.i(TAG, "Days since ask later not met, nothing doing..");
            return false;
        }

        for (Rule rule : rules)
            if (!rule.isRuleMet()) {
                Log.i(TAG, "A custom rule was not met..");
                return false;
            }

        Log.i(TAG, "Should ask for rating..");
        return true;
    }

    public static class Builder {
        private final Context context;
        private int minDaysSinceInstall = 7;
        private int minDaysSinceFeedback = 7;
        private int minDaysSinceAskLater = 7;
        private RatingDialogOptions ratingDialogOptions;
        private final List<Rule> rules = new ArrayList();
        private boolean showFeedbackOptionFirst = false;
        public String feedbackEmailAddress;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMinDaysSinceInstall(int minDaysSinceInstall) {
             this.minDaysSinceInstall = minDaysSinceInstall;
             return this;
        }

        public Builder setMinDaysSinceFeedback(int minDaysSinceFeedback) {
            this.minDaysSinceFeedback = minDaysSinceFeedback;
            return this;
        }

        public Builder setMinDaysSinceAskLater(int minDaysSinceAskLater) {
            this.minDaysSinceAskLater = minDaysSinceAskLater;
            return this;
        }

        public Builder addCustomRule(Rule rule) {
            rules.add(rule);
            return this;
        }

        public Builder setRatingDialogOptions(RatingDialogOptions ratingDialogOptions) {
            this.ratingDialogOptions = ratingDialogOptions;
            return this;
        }

        public Builder showFeedbackOption(boolean showFeedbackOptionFirst, String feedbackEmailAddress) {
            this.showFeedbackOptionFirst = showFeedbackOptionFirst;
            this.feedbackEmailAddress = feedbackEmailAddress;
            return this;
        }

        public RatingManager build() {
            return new RatingManager(this);
        }

    }

}
