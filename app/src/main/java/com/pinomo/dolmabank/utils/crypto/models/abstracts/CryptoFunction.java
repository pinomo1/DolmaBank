package com.pinomo.dolmabank.utils.crypto.models.abstracts;

import com.pinomo.dolmabank.utils.crypto.models.exceptions.InvalidKeyException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/**
 * The type Crypto function.
 *
 * @param <T> the type parameter
 */
public abstract class CryptoFunction<T> {
    private T key = this.generateKey();

    /**
     * Gets key.
     *
     * @return the key
     */
    public final T getKey() {
        return this.key;
    }

    /**
     * Sets key.
     *
     * @param var1 the var 1
     */
    protected final void setKey(T var1) {
        this.key = var1;
    }

    /**
     * Crypt string.
     *
     * @param str       the str
     * @param isEncrypt the is encrypt
     * @return the string
     */
    @NotNull
    public String crypt(@NotNull String str, boolean isEncrypt) {
        Intrinsics.checkNotNullParameter(str, "str");
        return isEncrypt ? this.encrypt(str) : this.decrypt(str);
    }

    /**
     * Decrypt string.
     *
     * @param str the str
     * @return the string
     */
    @NotNull
    public String decrypt(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return this.crypt(str, true);
    }

    /**
     * Encrypt string.
     *
     * @param str the str
     * @return the string
     */
    @NotNull
    public String encrypt(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        return this.crypt(str, false);
    }

    /**
     * Generate key t.
     *
     * @return the t
     */
    public abstract T generateKey();

    /**
     * Is valid for key boolean.
     *
     * @param var1 the var 1
     * @return the boolean
     */
    public abstract boolean isValidForKey(T var1);

    /**
     * Load key.
     *
     * @param key the key
     * @throws Throwable the throwable
     */
    public final void loadKey(T key) throws Throwable {
        if (!this.isValidForKey(key)) {
            throw (Throwable)(new InvalidKeyException());
        } else {
            this.key = key;
        }
    }
}
