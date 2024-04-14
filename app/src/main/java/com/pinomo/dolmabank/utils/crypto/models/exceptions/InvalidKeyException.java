package com.pinomo.dolmabank.utils.crypto.models.exceptions;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/**
 * The type Invalid key exception.
 */
public final class InvalidKeyException extends Exception {
    @NotNull
    public String getMessage() {
        return "Invalid key";
    }
}

