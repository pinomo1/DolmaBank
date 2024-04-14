package com.pinomo.dolmabank.utils.crypto;

import android.app.Activity;
import android.content.Context;
import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction;
import com.pinomo.dolmabank.utils.crypto.models.classes.MonoalphabeticFunction;

/**
 * CryptoUtils is a utility class that provides a static interface to the currently loaded CryptoFunction
 */
public class CryptoUtils {
    private static CryptoFunction<?> INSTANCE;

    /**
     * loadCryptoFunction is a static function that loads a new CryptoFunction
     *
     * @param activity is the activity that the CryptoFunction is loaded in
     */
    public static void loadCryptoFunction(Activity activity) {
        INSTANCE = new MonoalphabeticFunction();
        loadKeyFromMemory(activity);
    }

    /**
     * loadKeyFromMemory is a static function that loads the key from memory
     */
    private static void loadKeyFromMemory(Activity activity) {
        String k = activity.getPreferences(Context.MODE_PRIVATE).getString("cryptoKey", "");
        if (k.isEmpty()) {
            ((MonoalphabeticFunction) INSTANCE).generateKey();
            k = ((MonoalphabeticFunction) INSTANCE).keyToString();
            saveKeyToMemory(activity);
        }
        loadKey(k);
    }

    /**
     * loadKey is a static function that loads a key into the currently loaded CryptoFunction
     */
    private static void loadKey(String key) {
        ((MonoalphabeticFunction) INSTANCE).stringToKey(key);
    }

    /**
     * saveKeyToMemory is a static function that saves the key to memory
     */
    private static void saveKeyToMemory(Activity activity) {
        android.content.SharedPreferences.Editor editor = activity.getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("cryptoKey", ((MonoalphabeticFunction) INSTANCE).keyToString());
        editor.apply();
    }

    /**
     * encrypt is a static function that encrypts a string using the currently loaded CryptoFunction
     *
     * @param str the str
     * @return the string
     */
    public static String encrypt(String str) {
        return INSTANCE.encrypt(str);
    }

    /**
     * decrypt is a static function that decrypts a string using the currently loaded CryptoFunction
     *
     * @param str the str
     * @return the string
     */
    public static String decrypt(String str) {
        return INSTANCE.decrypt(str);
    }
}

