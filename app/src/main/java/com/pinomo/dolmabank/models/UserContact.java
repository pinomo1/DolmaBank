package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

import kotlin.UByteArrayKt;

/**
 * Represents a contact's information.
 */
final public class UserContact extends PayloadEntity {
    /**
     * The contact's name.
     */
    public String name;

    /**
     * The contact's email (may be null).
     */
    @Nullable
    public String email;

    /**
     * The contact's phone number.
     */
    public String phoneNumber;

    /**
     * The contact's address (may be null).
     */
    @Nullable
    public String address;
}
