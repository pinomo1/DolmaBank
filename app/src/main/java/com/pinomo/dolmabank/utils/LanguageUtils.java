package com.pinomo.dolmabank.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.pinomo.dolmabank.R;

import java.util.Locale;

/**
 * The type Language utils.
 */
public class LanguageUtils {
    /**
     * Gets language code.
     *
     * @param language the language
     * @return the language code
     */
    public static String getLanguageCode(String language) {
        return switch (language) {
            case "Russian" -> "ru";
            case "English" -> "en";
            case "Ukrainian" -> "uk";
            case "Azerbaijani" -> "az";
            case "French" -> "fr";
            case "Deutsch" -> "de";
            default -> "en"; // Provide a default language code if the selected item doesn't have an associated language code
        };
    }

    /**
     * Gets icon resource.
     *
     * @param selectedItem the selected item
     * @return the icon resource
     */
    public static int getIconResource(String selectedItem) {
        return switch (selectedItem) {
            case "Russian" -> R.drawable.ic_russian;
            case "English" -> R.drawable.ic_english;
            case "Ukrainian" -> R.drawable.ic_ukrainian;
            case "Azerbaijani" -> R.drawable.ic_azerbaijani;
            case "French" -> R.drawable.ic_french;
            case "Deutsch" -> R.drawable.ic_deutch;
            default -> R.drawable.ic_english; // Provide a default icon if the selected item doesn't have an associated icon
        };
    }

    /**
     * Gets name by code.
     *
     * @param code the code
     * @return the name by code
     */
    public static String getNameByCode(String code) {
        return switch (code) {
            case "ru" -> "Russian";
            case "en" -> "English";
            case "uk" -> "Ukrainian";
            case "az" -> "Azerbaijani";
            case "fr" -> "French";
            case "de" -> "Deutsch";
            default -> "English"; // Provide a default language name if the selected item doesn't have an associated language name
        };
    }

    /**
     * Gets current language code.
     *
     * @param activity the activity
     * @return the current language code
     */
    public static String getCurrentLanguageCode(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("language", "en");
    }

    /**
     * Gets current language.
     *
     * @param activity the activity
     * @return the current language
     */
    public static String getCurrentLanguage(Activity activity) {
        return getNameByCode(getCurrentLanguageCode(activity));
    }

    /**
     * Get languages string [ ].
     *
     * @return the string [ ]
     */
    public static String[] getLanguages() {
        return new String[] {"Russian","English","Ukrainian","Azerbaijani","French","Deutsch"};
    }

    /**
     * Sets language.
     *
     * @param activity the activity
     */
    public static void setLanguage(Activity activity) {
        setLanguage(activity, getCurrentLanguageCode(activity));
    }

    /**
     * Sets language.
     *
     * @param activity the activity
     * @param language the language
     */
    public static void setLanguage(Activity activity, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    /**
     * Save language.
     *
     * @param activity the activity
     * @param language the language
     */
    public static void saveLanguage(Activity activity, String language) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("language", language);
        editor.apply();
    }

    /**
     * Gets string.
     *
     * @param activity the activity
     * @param resId    the res id
     * @return the string
     */
    public static String getString(Activity activity, int resId) {
        return activity.getResources().getString(resId);
    }
}
