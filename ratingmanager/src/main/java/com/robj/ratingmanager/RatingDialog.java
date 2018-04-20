package com.robj.ratingmanager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
/**
 * Created by Rob J on 16/09/17.
 */

class RatingDialog {

    private final RatingDialogOptions ratingDialogOptions;
    
    private String feedbackEmailAddress;
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setMessage(ratingDialogOptions.initialPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.initialPopupPositiveBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onInitialPositiveClickListener != null)
                ratingDialogOptions.onInitialPositiveClickListener.onClick();
            showLeaveRatingPopup(context);
        });
        builder.setNegativeButton(ratingDialogOptions.initialPopupNegativeBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onInitialNegativeClickListener != null)
                ratingDialogOptions.onInitialNegativeClickListener.onClick();
            showFeedbackPopup(context);
        });
        builder.setNeutralButton(ratingDialogOptions.initialPopupLaterBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onInitialLaterClickListener != null)
                ratingDialogOptions.onInitialLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setCancelable(false);
        builder.show();
    }

    public void showLeaveRatingPopup(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setTitle(ratingDialogOptions.ratingPopupTitle);
        builder.setMessage(ratingDialogOptions.ratingPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.ratingPopupPositiveBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onRatingPositiveClickListener != null)
                ratingDialogOptions.onRatingPositiveClickListener.onClick();
            DataManager.setRatingLeft(context);
            launchUrl(context, ratingDialogOptions.ratingUrl);
        });
        builder.setNegativeButton(ratingDialogOptions.ratingPopupLaterBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onRatingLaterClickListener != null)
                ratingDialogOptions.onRatingLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setNeutralButton(ratingDialogOptions.ratingPopupNeverBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onRatingNegativeClickListener != null)
                ratingDialogOptions.onRatingNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
        });
        builder.setCancelable(false);
        builder.show();
    }

    private void showFeedbackPopup(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, ratingDialogOptions.dialogThemeResId);
        builder.setTitle(ratingDialogOptions.feedbackPopupTitle);
        builder.setMessage(ratingDialogOptions.feedbackPopupMessage);
        builder.setPositiveButton(ratingDialogOptions.feedbackPopupPositiveBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onFeedbackPositiveClickListener != null)
                ratingDialogOptions.onFeedbackPositiveClickListener.onClick();
            DataManager.setFeedbackLeft(context);
            launchEmailIntent(context, feedbackEmailAddress, ratingDialogOptions.feedbackEmailSubject, ratingDialogOptions.feedbackEmailBody);
        });
        builder.setNegativeButton(ratingDialogOptions.feedbackPopupLaterBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onFeedbackLaterClickListener != null)
                ratingDialogOptions.onFeedbackLaterClickListener.onClick();
            DataManager.setAskLater(context);
        });
        builder.setNeutralButton(ratingDialogOptions.feedbackPopupNegativeBtnText, (dialog, which) -> {
            if(ratingDialogOptions.onFeedbackNegativeClickListener != null)
                ratingDialogOptions.onFeedbackNegativeClickListener.onClick();
            DataManager.setNeverAsk(context);
        });
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
        this.ratingDialogOptions = ratingDialogOptions;
        
    }

}
