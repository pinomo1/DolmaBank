package com.pinomo.dolmabank.utils.crypto.models.classes

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction

class NoCryptoFunction : CryptoFunction<Unit>() {
    override fun crypt(str: String, isEncrypt: Boolean): String{
        return str
    }

    override fun generateKey() {
        loadKey(Unit)
    }

    override fun isValidForKey(potentialKey: Unit): Boolean {
        return true
    }
}