package models.statics

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction

class CryptoFunctionTester {
    companion object{
        fun test(cryptoFunction: CryptoFunction<*>) : Boolean{
            cryptoFunction.generateKey()
            val testString : String = CryptoFunction.SAMPLE_TEXT
            val encryptedString = cryptoFunction.encrypt(testString)
            val decryptedString = cryptoFunction.decrypt(encryptedString)
            println()
            return testString == decryptedString
        }
    }
}