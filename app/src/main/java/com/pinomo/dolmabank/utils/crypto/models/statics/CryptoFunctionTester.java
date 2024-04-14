package com.pinomo.dolmabank.utils.crypto.models.statics;

import com.pinomo.dolmabank.utils.crypto.models.abstracts.CryptoFunction;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/**
 * The type Crypto function tester.
 */
public final class CryptoFunctionTester {
    /**
     * Test boolean.
     *
     * @param cryptoFunction the crypto function
     * @return the boolean
     */
    public static boolean test(@NotNull CryptoFunction cryptoFunction) {
            Intrinsics.checkNotNullParameter(cryptoFunction, "cryptoFunction");
            cryptoFunction.generateKey();
            String testString = "the quick brown fox jumps over the lazy dog, lorem ipsum dolor sit amet 3.1415926535, hello world! how are you?";
            String encryptedString = cryptoFunction.encrypt(testString);
            String decryptedString = cryptoFunction.decrypt(encryptedString);
            System.out.println();
            return Intrinsics.areEqual(testString, decryptedString);
        }
}
