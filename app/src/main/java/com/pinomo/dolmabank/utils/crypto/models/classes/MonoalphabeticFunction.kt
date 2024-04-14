package com.pinomo.dolmabank.utils.crypto.models.classes

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction

class MonoalphabeticFunction : CryptoFunction<Map<Char, Char>>() {
    override fun crypt(str: String, isEncrypt: Boolean): String {
        val copyStr = str
        var result = ""
        var dict = key
        if (isEncrypt) {
            dict = dict.entries.associate { (k, v) -> v to k }
        }
        copyStr.forEach { c ->
            result += if (c in ('a'..'z') || c in ('A'..'Z') || c in ('0'..'9')){
                dict[c]
            } else {
                c
            }
        }
        return result
    }

    override fun generateKey(): Map<Char, Char> {
        val alphabet = ('a'..'z').toList() + ('A'..'Z').toList() + ('0'..'9').toList()
        val scrambledAlphabet = alphabet.shuffled()
        val key = alphabet.zip(scrambledAlphabet).toMap()
        loadKey(key)
        return this.key
    }

    fun keyToString(): String {
        return key.entries.joinToString(separator = "") { "${it.key}${it.value}" }
    }

    fun stringToKey(str: String) {
        val key = mutableMapOf<Char, Char>()
        for (i in str.indices step 2) {
            key[str[i]] = str[i + 1]
        }
        loadKey(key)
    }

    override fun isValidForKey(potentialKey: Map<Char, Char>): Boolean {
        val alphabet = ('a'..'z').toList() + ('A'..'Z').toList() + ('0'..'9').toList()
        return potentialKey.keys.all { it in alphabet } && potentialKey.values.all { it in alphabet } && potentialKey.keys.size == 62
    }

}