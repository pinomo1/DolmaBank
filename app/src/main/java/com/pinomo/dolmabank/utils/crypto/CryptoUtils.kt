package com.pinomo.dolmabank.utils.crypto

import android.app.Activity
import android.content.Context
import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction
import com.pinomo.dolmabank.utils.crypto.models.classes.MonoalphabeticFunction
import com.pinomo.dolmabank.utils.crypto.models.classes.VigenereFunction

public class CryptoUtils {
    companion object{
        lateinit var INSTANCE : CryptoFunction<*>

        public fun loadCryptoFunction(activity: Activity){
            INSTANCE = MonoalphabeticFunction()
            loadKeyFromMemory(activity)
        }

        public fun loadKeyFromMemory(activity : Activity) {
            var k : String = activity.getPreferences(Context.MODE_PRIVATE).getString("cryptoKey", "")!!
            if (k == ""){
                (INSTANCE as MonoalphabeticFunction).generateKey()
                k = (INSTANCE as MonoalphabeticFunction).keyToString()
                saveKeyToMemory(activity)
            }
            loadKey(k)
        }

        public fun loadKey(key: String){
            (INSTANCE as MonoalphabeticFunction).stringToKey(key)
        }

        public fun saveKeyToMemory(activity : Activity){
            val editor = activity.getPreferences(Context.MODE_PRIVATE).edit()
            editor.putString("cryptoKey", (INSTANCE as MonoalphabeticFunction).keyToString())
            editor.apply()
        }

        public fun encrypt(str: String): String{
            return INSTANCE.encrypt(str)
        }

        public fun decrypt(str: String): String{
            return INSTANCE.decrypt(str)
        }
    }
}