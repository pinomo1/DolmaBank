package com.pinomo.dolmabank.utils.crypto.models.classes;

import androidx.annotation.NonNull;

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction;

import java.util.*;

/**
 * The type Monoalphabetic function.
 */
public class MonoalphabeticFunction extends CryptoFunction<Map<Character, Character>> {

    @NonNull
    @Override
    public String crypt(@NonNull String str, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();
        Map<Character, Character> dict = getKey();
        if (isEncrypt) {
            dict = invertMap(dict);
        }
        for (char c : str.toCharArray()) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
                result.append(dict.get(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    @Override
    public Map<Character, Character> generateKey() {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) alphabet.add(c);
        for (char c = 'A'; c <= 'Z'; c++) alphabet.add(c);
        for (char c = '0'; c <= '9'; c++) alphabet.add(c);
        Collections.shuffle(alphabet);
        Map<Character, Character> key = new LinkedHashMap<>();
        int i = 0;
        for (char c : alphabet) {
            key.put(c, alphabet.get(i++));
        }
        try {
            loadKey(key);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return getKey();
    }

    /**
     * Key to string string.
     *
     * @return the string
     */
    public String keyToString() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Character> entry : getKey().entrySet()) {
            result.append(entry.getKey()).append(entry.getValue());
        }
        return result.toString();
    }

    /**
     * String to key.
     *
     * @param str the str
     */
    public void stringToKey(String str) {
        Map<Character, Character> key = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i += 2) {
            key.put(str.charAt(i), str.charAt(i + 1));
        }
        try {
            loadKey(key);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public boolean isValidForKey(Map<Character, Character> potentialKey) {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) alphabet.add(c);
        for (char c = 'A'; c <= 'Z'; c++) alphabet.add(c);
        for (char c = '0'; c <= '9'; c++) alphabet.add(c);
        return potentialKey.keySet().containsAll(alphabet) && potentialKey.values().containsAll(alphabet) && potentialKey.size() == 62;
    }

    private Map<Character, Character> invertMap(Map<Character, Character> map) {
        Map<Character, Character> invertedMap = new HashMap<>();
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            invertedMap.put(entry.getValue(), entry.getKey());
        }
        return invertedMap;
    }
}