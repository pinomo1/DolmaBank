package com.pinomo.dolmabank.utils;

import android.app.Activity;
import android.content.Context;

/**
 * The type App utils.
 */
public class AppUtils {
    /**
     * Format balance string.
     *
     * @param balance the balance
     * @return the string
     */
    public static String formatBalance(double balance) {
        return "$" + String.format("%1$,.2f", balance);
    }

    /**
     * Is first launch boolean.
     *
     * @param activity the activity
     * @return the boolean
     */
    public static boolean isFirstLaunch(Activity activity) {
        return activity.getPreferences(Context.MODE_PRIVATE).getBoolean("firstLaunch", true);
    }

    /**
     * Sets first launch.
     *
     * @param activity    the activity
     * @param firstLaunch the first launch
     */
    public static void setFirstLaunch(Activity activity, boolean firstLaunch) {
        activity.getPreferences(Context.MODE_PRIVATE).edit().putBoolean("firstLaunch", firstLaunch).apply();
    }
}
