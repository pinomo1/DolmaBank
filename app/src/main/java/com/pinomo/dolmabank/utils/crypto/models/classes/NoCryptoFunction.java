package com.pinomo.dolmabank.utils.crypto.models.classes;

import androidx.annotation.NonNull;

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction;

/**
 * The type No crypto function.
 */
public class NoCryptoFunction extends CryptoFunction<Void> {

    @NonNull
    @Override
    public String crypt(@NonNull String str, boolean isEncrypt) {
        return str;
    }

    @Override
    public Void generateKey() {
        try {
            loadKey(null);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isValidForKey(Void potentialKey) {
        return true;
    }
}