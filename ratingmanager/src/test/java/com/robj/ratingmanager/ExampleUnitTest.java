package com.robj.ratingmanager;

import android.content.Context;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testBuilder() {
        Context context = null; //Mock context for testing build
        RatingDialogBuilder ratingDialogBuilder = new RatingDialogBuilder(context);
        new RatingManager.Builder(context)
                .setMinDaysSinceInstall(1)
                .setMinDaysSinceAskLater(3)
                .setMinDaysSinceFeedback(3)
                .setRatingDialog(ratingDialogBuilder);
    }

//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }

}