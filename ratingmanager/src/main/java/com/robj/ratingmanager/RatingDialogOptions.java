package com.robj.ratingmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.StyleRes;

public class RatingDialogOptions {

    public final int dialogThemeResId;

    public final String initialPopupMessage;
    public final String initialPopupPositiveBtnText;
    public final String initialPopupNegativeBtnText;
    public final String initialPopupLaterBtnText;

    public final String ratingPopupTitle;
    public final String ratingPopupMessage;
    public final String ratingPopupPositiveBtnText;
    public final String ratingPopupLaterBtnText;
    public final String ratingPopupNeverBtnText;
    public final String ratingUrl;

    public final String feedbackPopupTitle;
    public final String feedbackPopupMessage;
    public final String feedbackPopupNegativeBtnText;
    public final String feedbackPopupPositiveBtnText;
    public final String feedbackPopupLaterBtnText;
    public final String feedbackEmailSubject;
    public final String feedbackEmailBody;
    
    public final OnRatingClickListener onInitialPositiveClickListener;
    public final OnRatingClickListener onInitialNegativeClickListener;
    public final OnRatingClickListener onInitialLaterClickListener;
    public final OnRatingClickListener onRatingPositiveClickListener;
    public final OnRatingClickListener onRatingNegativeClickListener;
    public final OnRatingClickListener onRatingLaterClickListener;
    public final OnRatingClickListener onFeedbackPositiveClickListener;
    public final OnRatingClickListener onFeedbackNegativeClickListener;
    public final OnRatingClickListener onFeedbackLaterClickListener;

    public RatingDialogOptions(int dialogThemeResId, String initialPopupMessage, String initialPopupPositiveBtnText, String initialPopupNegativeBtnText, String initialPopupLaterBtnText, String ratingPopupTitle, String ratingPopupMessage, String ratingPopupPositiveBtnText, String ratingPopupLaterBtnText, String ratingPopupNeverBtnText, String ratingUrl, String feedbackPopupTitle, String feedbackPopupMessage, String feedbackPopupNegativeBtnText, String feedbackPopupPositiveBtnText, String feedbackPopupLaterBtnText, String feedbackEmailSubject, String feedbackEmailBody, OnRatingClickListener onInitialPositiveClickListener, OnRatingClickListener onInitialNegativeClickListener, OnRatingClickListener onInitialLaterClickListener, OnRatingClickListener onRatingPositiveClickListener, OnRatingClickListener onRatingNegativeClickListener, OnRatingClickListener onRatingLaterClickListener, OnRatingClickListener onFeedbackPositiveClickListener, OnRatingClickListener onFeedbackNegativeClickListener, OnRatingClickListener onFeedbackLaterClickListener) {
        this.dialogThemeResId = dialogThemeResId;
        this.initialPopupMessage = initialPopupMessage;
        this.initialPopupPositiveBtnText = initialPopupPositiveBtnText;
        this.initialPopupNegativeBtnText = initialPopupNegativeBtnText;
        this.initialPopupLaterBtnText = initialPopupLaterBtnText;
        this.ratingPopupTitle = ratingPopupTitle;
        this.ratingPopupMessage = ratingPopupMessage;
        this.ratingPopupPositiveBtnText = ratingPopupPositiveBtnText;
        this.ratingPopupLaterBtnText = ratingPopupLaterBtnText;
        this.ratingPopupNeverBtnText = ratingPopupNeverBtnText;
        this.ratingUrl = ratingUrl;
        this.feedbackPopupTitle = feedbackPopupTitle;
        this.feedbackPopupMessage = feedbackPopupMessage;
        this.feedbackPopupNegativeBtnText = feedbackPopupNegativeBtnText;
        this.feedbackPopupPositiveBtnText = feedbackPopupPositiveBtnText;
        this.feedbackPopupLaterBtnText = feedbackPopupLaterBtnText;
        this.feedbackEmailSubject = feedbackEmailSubject;
        this.feedbackEmailBody = feedbackEmailBody;
        this.onInitialPositiveClickListener = onInitialPositiveClickListener;
        this.onInitialNegativeClickListener = onInitialNegativeClickListener;
        this.onInitialLaterClickListener = onInitialLaterClickListener;
        this.onRatingPositiveClickListener = onRatingPositiveClickListener;
        this.onRatingNegativeClickListener = onRatingNegativeClickListener;
        this.onRatingLaterClickListener = onRatingLaterClickListener;
        this.onFeedbackPositiveClickListener = onFeedbackPositiveClickListener;
        this.onFeedbackNegativeClickListener = onFeedbackNegativeClickListener;
        this.onFeedbackLaterClickListener = onFeedbackLaterClickListener;
    }

    public static class Builder {

        private int dialogThemeResId = R.style.RatingManagerTheme_Dialog;

        private String initialPopupMessage;
        private String initialPopupPositiveBtnText;
        private String initialPopupNegativeBtnText;
        private String initialPopupLaterBtnText;

        private String ratingPopupTitle;
        private String ratingPopupMessage;
        private String ratingPopupPositiveBtnText;
        private String ratingPopupLaterBtnText;
        private String ratingPopupNeverBtnText;
        private String ratingUrl;

        private String feedbackPopupTitle;
        private String feedbackPopupMessage;
        private String feedbackPopupNegativeBtnText;
        private String feedbackPopupPositiveBtnText;
        private String feedbackPopupLaterBtnText;
        private String feedbackEmailSubject;
        private String feedbackEmailBody;

        private OnRatingClickListener onInitialPositiveClickListener;
        private OnRatingClickListener onInitialNegativeClickListener;
        private OnRatingClickListener onInitialLaterClickListener;
        private OnRatingClickListener onRatingPositiveClickListener;
        private OnRatingClickListener onRatingNegativeClickListener;
        private OnRatingClickListener onRatingLaterClickListener;
        private OnRatingClickListener onFeedbackPositiveClickListener;
        private OnRatingClickListener onFeedbackNegativeClickListener;
        private OnRatingClickListener onFeedbackLaterClickListener;

        private String getAppLabel(Context context) {
            try {
                ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
                if (appInfo != null) {
                    return appInfo.loadLabel(context.getPackageManager()).toString();
                }
            } catch (Exception var3) {
                var3.printStackTrace();
            }
            return "Unknown";
        }

        public Builder(Context context) {
            String appName = getAppLabel(context);
            initialPopupMessage = context.getString(R.string.initial_rating_title, appName);
            initialPopupPositiveBtnText = context.getString(R.string.yes);
            initialPopupNegativeBtnText = context.getString(R.string.no);
            initialPopupLaterBtnText = context.getString(R.string.ask_me_later);

            ratingPopupTitle = context.getString(R.string.rating_title);
            ratingPopupMessage = context.getString(R.string.rating_text);
            ratingPopupPositiveBtnText = context.getString(R.string.rate_us);
            ratingPopupLaterBtnText = context.getString(R.string.maybe_later);
            ratingPopupNeverBtnText = context.getString(R.string.never);
            ratingUrl = "https://play.google.com/store/apps/details?id=" + context.getPackageName();

            feedbackPopupTitle = context.getString(R.string.feedback_title);
            feedbackPopupMessage = context.getString(R.string.feedback_text);
            feedbackPopupPositiveBtnText = context.getString(R.string.leave_feedback);
            feedbackPopupLaterBtnText = context.getString(R.string.maybe_later);
            feedbackPopupNegativeBtnText = context.getString(R.string.never);
            feedbackEmailSubject = context.getString(R.string.email_feedback_subject, getAppLabel(context));
            feedbackEmailBody = null;
        }

        public Builder setDialogThemeResId(@StyleRes int dialogThemeResId) {
            this.dialogThemeResId = dialogThemeResId;
            return this;
        }

        public Builder setInitialPopupMessage(String initialPopupMessage) {
            this.initialPopupMessage = initialPopupMessage;
            return this;
        }

        public Builder setInitialPopupNegativeBtnText(String initialPopupNegativeBtnText) {
            this.initialPopupNegativeBtnText = initialPopupNegativeBtnText;
            return this;
        }

        public Builder setInitialPopupPositiveBtnText(String initialPopupPositiveBtnText) {
            this.initialPopupPositiveBtnText = initialPopupPositiveBtnText;
            return this;
        }

        public Builder setInitialPopupLaterBtnText(String initialPopupLaterBtnText) {
            this.initialPopupLaterBtnText = initialPopupLaterBtnText;
            return this;
        }

        public Builder setRatingPopupTitle(String ratingPopupTitle) {
            this.ratingPopupTitle = ratingPopupTitle;
            return this;
        }

        public Builder setRatingPopupMessage(String ratingPopupMessage) {
            this.ratingPopupMessage = ratingPopupMessage;
            return this;
        }

        public Builder setRatingPopupLaterBtnText(String ratingPopupLaterBtnText) {
            this.ratingPopupLaterBtnText = ratingPopupLaterBtnText;
            return this;
        }

        public Builder setRatingPopupPositiveBtnText(String ratingPopupPositiveBtnText) {
            this.ratingPopupPositiveBtnText = ratingPopupPositiveBtnText;
            return this;
        }

        public Builder setRatingPopupNeverBtnText(String ratingPopupNeverBtnText) {
            this.ratingPopupNeverBtnText = ratingPopupNeverBtnText;
            return this;
        }

        public Builder setRatingUrl(String ratingUrl) {
            this.ratingUrl = ratingUrl;
            return this;
        }

        public Builder setFeedbackPopupTitle(String feedbackPopupTitle) {
            this.feedbackPopupTitle = feedbackPopupTitle;
            return this;
        }

        public Builder setFeedbackPopupMessage(String feedbackPopupMessage) {
            this.feedbackPopupMessage = feedbackPopupMessage;
            return this;
        }

        public Builder setFeedbackPopupNegativeBtnText(String feedbackPopupNegativeBtnText) {
            this.feedbackPopupNegativeBtnText = feedbackPopupNegativeBtnText;
            return this;
        }

        public Builder setFeedbackPopupPositiveBtnText(String feedbackPopupPositiveBtnText) {
            this.feedbackPopupPositiveBtnText = feedbackPopupPositiveBtnText;
            return this;
        }

        public Builder setFeedbackPopupLaterBtnText(String feedbackPopupLaterBtnText) {
            this.feedbackPopupLaterBtnText = feedbackPopupLaterBtnText;
            return this;
        }

        public Builder setFeedbackEmailSubject(String feedbackEmailSubject) {
            this.feedbackEmailSubject = feedbackEmailSubject;
            return this;
        }

        public Builder setFeedbackEmailBody(String feedbackEmailBody) {
            this.feedbackEmailBody = feedbackEmailBody;
            return this;
        }

        public Builder setOnInitialPositiveClickListener(OnRatingClickListener onInitialPositiveClickListener) {
            this.onInitialPositiveClickListener = onInitialPositiveClickListener;
            return this;
        }

        public Builder setOnInitialNegativeClickListener(OnRatingClickListener onInitialNegativeClickListener) {
            this.onInitialNegativeClickListener = onInitialNegativeClickListener;
            return this;
        }

        public Builder setOnInitialLaterClickListener(OnRatingClickListener onInitialLaterClickListener) {
            this.onInitialLaterClickListener = onInitialLaterClickListener;
            return this;
        }

        public Builder setOnRatingPositiveClickListener(OnRatingClickListener onPopupPositiveClickListener) {
            this.onRatingPositiveClickListener = onPopupPositiveClickListener;
            return this;
        }

        public Builder setOnRatingNegativeClickListener(OnRatingClickListener onPopupNegativeClickListener) {
            this.onRatingNegativeClickListener = onPopupNegativeClickListener;
            return this;
        }

        public Builder setOnRatingLaterClickListener(OnRatingClickListener onPopupLaterClickListener) {
            this.onRatingLaterClickListener = onPopupLaterClickListener;
            return this;
        }

        public Builder setOnFeedbackPositiveClickListener(OnRatingClickListener onFeedbackPositiveClickListener) {
            this.onFeedbackPositiveClickListener = onFeedbackPositiveClickListener;
            return this;
        }

        public Builder setOnFeedbackNegativeClickListener(OnRatingClickListener onFeedbackNegativeClickListener) {
            this.onFeedbackNegativeClickListener = onFeedbackNegativeClickListener;
            return this;
        }

        public Builder setOnFeedbackLaterClickListener(OnRatingClickListener onFeedbackLaterClickListener) {
            this.onFeedbackLaterClickListener = onFeedbackLaterClickListener;
            return this;
        }

        public RatingDialogOptions build() {
            return new RatingDialogOptions(dialogThemeResId, initialPopupMessage, initialPopupPositiveBtnText, initialPopupNegativeBtnText, initialPopupLaterBtnText, ratingPopupTitle, ratingPopupMessage, ratingPopupPositiveBtnText, ratingPopupLaterBtnText, ratingPopupNeverBtnText, ratingUrl, feedbackPopupTitle, feedbackPopupMessage, feedbackPopupNegativeBtnText, feedbackPopupPositiveBtnText, feedbackPopupLaterBtnText, feedbackEmailSubject, feedbackEmailBody, onInitialPositiveClickListener, onInitialNegativeClickListener, onInitialLaterClickListener, onRatingPositiveClickListener, onRatingNegativeClickListener, onRatingLaterClickListener, onFeedbackPositiveClickListener, onFeedbackNegativeClickListener, onFeedbackLaterClickListener);
        }

    }
}