package com.robj.ratingmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.annotation.StyleRes;
import android.text.TextUtils;

public class RatingDialogOptionsBuilder {

    private int dialogThemeResId = R.style.RatingManagerTheme_Dialog;

    private String initialPopupMessage;
    private String intialPopupPositiveBtnText;
    private String intialPopupNegativeBtnText;
    private String intialPopupLaterBtnText;

    private String ratingPopupTitle;
    private String ratingPopupMessage;
    private String ratingPopupPositiveBtnText;
    private String ratingPopupNegativeBtnText;
    private String ratingPopupLaterBtnText;
    private String ratingUrl;

    private String feedbackPopupTitle;
    private String feedbackPopupMessage;
    private String feedbackPopupNegativeBtnText;
    private String feedbackPopupPositiveBtnText;
    private String feedbackPopupLaterBtnText;
    private String feedbackEmailAddress;
    private String feedbackEmailSubject;
    private String feedbackEmailBody;

    private boolean showFeedbackOption;

    private String getAppLabel(Context context) {
        try {
            ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            if(appInfo != null) {
                return appInfo.loadLabel(context.getPackageManager()).toString();
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }
        return "Unknown";
    }

    public RatingDialogOptionsBuilder(Context context) {
        String appName = getAppLabel(context);
        initialPopupMessage = context.getString(R.string.initial_rating_title, appName);
        intialPopupPositiveBtnText = context.getString(R.string.yes);
        intialPopupNegativeBtnText = context.getString(R.string.no);
        intialPopupLaterBtnText = context.getString(R.string.ask_me_later);

        ratingPopupTitle = context.getString(R.string.rating_title);
        ratingPopupMessage = context.getString(R.string.rating_text);
        ratingPopupPositiveBtnText = context.getString(R.string.rate_us);
        ratingPopupNegativeBtnText = context.getString(R.string.no);
        ratingPopupLaterBtnText = context.getString(R.string.maybe_later);
        ratingUrl = "https://play.google.com/store/apps/details?id=" + context.getPackageName();

        feedbackPopupTitle = context.getString(R.string.feedback_title);
        feedbackPopupMessage = context.getString(R.string.feedback_text);
        feedbackPopupPositiveBtnText = context.getString(R.string.leave_feedback);
        feedbackPopupNegativeBtnText = context.getString(R.string.no);
        feedbackPopupLaterBtnText = context.getString(R.string.maybe_later);
        feedbackEmailAddress = null;
        feedbackEmailSubject = context.getString(R.string.email_feedback_subject, getAppLabel(context));
        feedbackEmailBody = null;

        showFeedbackOption = false;
    }

    public RatingDialogOptionsBuilder setDialogThemeResId(@StyleRes int dialogThemeResId) {
        this.dialogThemeResId = dialogThemeResId;
        return this;
    }

    public RatingDialogOptionsBuilder setInitialPopupMessage(String initialPopupMessage) {
        this.initialPopupMessage = initialPopupMessage;
        return this;
    }

    public RatingDialogOptionsBuilder setIntialPopupNegativeBtnText(String intialPopupNegativeBtnText) {
        this.intialPopupNegativeBtnText = intialPopupNegativeBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setIntialPopupPositiveBtnText(String intialPopupPositiveBtnText) {
        this.intialPopupPositiveBtnText = intialPopupPositiveBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setIntialPopupLaterBtnText(String intialPopupLaterBtnText) {
        this.intialPopupLaterBtnText = intialPopupLaterBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingPopupTitle(String ratingPopupTitle) {
        this.ratingPopupTitle = ratingPopupTitle;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingPopupMessage(String ratingPopupMessage) {
        this.ratingPopupMessage = ratingPopupMessage;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingPopupNegativeBtnText(String ratingPopupNegativeBtnText) {
        this.ratingPopupNegativeBtnText = ratingPopupNegativeBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingPopupPositiveBtnText(String ratingPopupPositiveBtnText) {
        this.ratingPopupPositiveBtnText = ratingPopupPositiveBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingPopupLaterBtnText(String ratingPopupLaterBtnText) {
        this.ratingPopupLaterBtnText = ratingPopupLaterBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setRatingUrl(String ratingUrl) {
        this.ratingUrl = ratingUrl;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackPopupTitle(String feedbackPopupTitle) {
        this.feedbackPopupTitle = feedbackPopupTitle;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackPopupMessage(String feedbackPopupMessage) {
        this.feedbackPopupMessage = feedbackPopupMessage;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackPopupNegativeBtnText(String feedbackPopupNegativeBtnText) {
        this.feedbackPopupNegativeBtnText = feedbackPopupNegativeBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackPopupPositiveBtnText(String feedbackPopupPositiveBtnText) {
        this.feedbackPopupPositiveBtnText = feedbackPopupPositiveBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackPopupLaterBtnText(String feedbackPopupLaterBtnText) {
        this.feedbackPopupLaterBtnText = feedbackPopupLaterBtnText;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackEmailAddress(String feedbackEmailAddress) {
        this.feedbackEmailAddress = feedbackEmailAddress;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackEmailSubject(String feedbackEmailSubject) {
        this.feedbackEmailSubject = feedbackEmailSubject;
        return this;
    }

    public RatingDialogOptionsBuilder setFeedbackEmailBody(String feedbackEmailBody) {
        this.feedbackEmailBody = feedbackEmailBody;
        return this;
    }

    public RatingDialogOptionsBuilder showFeedbackOption(boolean showFeedbackOption) {
        this.showFeedbackOption = showFeedbackOption;
        return this;
    }

    public RatingDialog build() {
        if(showFeedbackOption && TextUtils.isEmpty(feedbackEmailAddress))
            throw new RuntimeException("showFeedbackOption is true but not email address was provided to send feedback to..");
        return new RatingDialog(dialogThemeResId, initialPopupMessage, intialPopupNegativeBtnText, intialPopupPositiveBtnText, intialPopupLaterBtnText, ratingPopupTitle, ratingPopupMessage, ratingPopupNegativeBtnText, ratingPopupPositiveBtnText, ratingPopupLaterBtnText, ratingUrl, feedbackPopupTitle, feedbackPopupMessage, feedbackPopupNegativeBtnText, feedbackPopupPositiveBtnText, feedbackPopupLaterBtnText, feedbackEmailAddress, feedbackEmailSubject, feedbackEmailBody, showFeedbackOption);
    }
}