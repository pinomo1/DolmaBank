package com.pinomo.dolmabank.utils.crypto.models.classes;

import androidx.annotation.NonNull;

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction;

import java.util.Random;

/**
 * The type Vigenere function.
 */
public class VigenereFunction extends CryptoFunction<String> {

    @NonNull
    @Override
    public String crypt(String str, boolean isEncrypt) {
        String copyStr = str.toLowerCase();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < copyStr.length(); i++) {
            char c = copyStr.charAt(i);
            if (c >= 'a' && c <= 'z') {
                int keyInt = getKey().charAt(i % getKey().length()) - 97;
                char newChar = (char) (c + (isEncrypt ? 1 : -1) * keyInt);
                if ((newChar < 'a' && !isEncrypt) || (newChar > 'z' && isEncrypt)) {
                    newChar -= (char) ((isEncrypt ? 1 : -1) * 26);
                }
                result.append(newChar);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public String generateKey() {
        StringBuilder rand = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            rand.append((char) (random.nextInt(26) + 'a'));
        }
        try {
            loadKey(rand.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return getKey();
    }

    @Override
    public boolean isValidForKey(String potentialKey) {
        for (char c : potentialKey.toCharArray()) {
            if (c < 'a' || c > 'z') {
                return false;
            }
        }
        return true;
    }
}