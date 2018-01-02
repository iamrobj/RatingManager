package com.robj.ratingmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;
/**
 * Created by Rob J on 16/09/17.
 */

class RatingDialog {

    @StyleRes
    private int dialogThemeResId;

    private String initialPopupMessage;
    private String intialPopupNegativeBtnText;
    private String intialPopupPositiveBtnText;
    private String intialPopupLaterBtnText;

    private String ratingPopupTitle;
    private String ratingPopupMessage;
    private String ratingPopupLaterBtnText;
    private String ratingPopupPositiveBtnText;
    private String ratingPopupNeverBtnText;
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

    protected void setShowFeedbackOption(boolean showFeedbackOption, String feedbackEmail) {
        this.showFeedbackOption = showFeedbackOption;
        this.feedbackEmailAddress = feedbackEmail;
    }

    public void showRatingPopup(final Context context) {
        if(!showFeedbackOption) {
            showLeaveRatingPopup(context);
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context, dialogThemeResId);
        builder.setMessage(initialPopupMessage);
        builder.setPositiveButton(intialPopupPositiveBtnText, (dialog, which) -> showLeaveRatingPopup(context));
        builder.setNegativeButton(intialPopupNegativeBtnText, (dialog, which) -> showFeedbackPopup(context));
        builder.setNeutralButton(intialPopupLaterBtnText, (dialog, which) -> DataManager.setAskLater(context));
        builder.setCancelable(false);
        builder.show();
    }

    private void showLeaveRatingPopup(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, dialogThemeResId);
        builder.setTitle(ratingPopupTitle);
        builder.setMessage(ratingPopupMessage);
        builder.setPositiveButton(ratingPopupPositiveBtnText, (dialog, which) -> {
            DataManager.setRatingLeft(context);
            launchUrl(context, ratingUrl);
        });
        builder.setNegativeButton(ratingPopupLaterBtnText, (dialog, which) -> DataManager.setAskLater(context));
        builder.setNeutralButton(ratingPopupNeverBtnText, (dialog, which) -> DataManager.setNeverAsk(context));
        builder.setCancelable(false);
        builder.show();
    }

    private void showFeedbackPopup(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, dialogThemeResId);
        builder.setTitle(feedbackPopupTitle);
        builder.setMessage(feedbackPopupMessage);
        builder.setPositiveButton(feedbackPopupPositiveBtnText, (dialog, which) -> {
            DataManager.setFeedbackLeft(context);
            launchEmailIntent(context, feedbackEmailAddress, feedbackEmailSubject, feedbackEmailBody);
        });
        builder.setNegativeButton(feedbackPopupNegativeBtnText, (dialog, which) -> DataManager.setNeverAsk(context));
        builder.setNeutralButton(feedbackPopupLaterBtnText, (dialog, which) -> DataManager.setAskLater(context));
        builder.setCancelable(false);
        builder.show();
    }

    private static void launchEmailIntent(Context context, String email, String subject, String body) {
        Intent i = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        context.startActivity(i);
    }

    private static void launchUrl(Context context, String url) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_NEW_TASK);
        i = Intent.createChooser(i, context.getString(R.string.dialog_open_with));
        context.startActivity(i);
    }

    RatingDialog(RatingDialogOptions ratingDialogOptions) {
        this.dialogThemeResId = ratingDialogOptions.dialogThemeResId;
        this.initialPopupMessage = ratingDialogOptions.initialPopupMessage;
        this.intialPopupNegativeBtnText = ratingDialogOptions.initialPopupNegativeBtnText;
        this.intialPopupPositiveBtnText = ratingDialogOptions.initialPopupPositiveBtnText;
        this.intialPopupLaterBtnText = ratingDialogOptions.initialPopupLaterBtnText;
        this.ratingPopupTitle = ratingDialogOptions.ratingPopupTitle;
        this.ratingPopupMessage = ratingDialogOptions.ratingPopupMessage;
        this.ratingPopupLaterBtnText = ratingDialogOptions.ratingPopupLaterBtnText;
        this.ratingPopupPositiveBtnText = ratingDialogOptions.ratingPopupPositiveBtnText;
        this.ratingPopupNeverBtnText = ratingDialogOptions.ratingPopupNeverBtnText;
        this.ratingUrl = ratingDialogOptions.ratingUrl;
        this.feedbackPopupTitle = ratingDialogOptions.feedbackPopupTitle;
        this.feedbackPopupMessage = ratingDialogOptions.feedbackPopupMessage;
        this.feedbackPopupNegativeBtnText = ratingDialogOptions.feedbackPopupNegativeBtnText;
        this.feedbackPopupPositiveBtnText = ratingDialogOptions.feedbackPopupPositiveBtnText;
        this.feedbackPopupLaterBtnText = ratingDialogOptions.feedbackPopupLaterBtnText;
        this.feedbackEmailSubject = ratingDialogOptions.feedbackEmailSubject;
        this.feedbackEmailBody = ratingDialogOptions.feedbackEmailBody;
    }

}
