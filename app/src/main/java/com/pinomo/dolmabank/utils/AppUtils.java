package com.pinomo.dolmabank.utils;

import android.app.Activity;
import android.content.Context;

public class AppUtils {
    public static String formatBalance(double balance) {
        return "$" + String.format("%1$,.2f", balance);
    }

    public static boolean isFirstLaunch(Activity activity) {
        return activity.getPreferences(Context.MODE_PRIVATE).getBoolean("firstLaunch", true);
    }

    public static void setFirstLaunch(Activity activity, boolean firstLaunch) {
        activity.getPreferences(Context.MODE_PRIVATE).edit().putBoolean("firstLaunch", firstLaunch).apply();
    }
}
