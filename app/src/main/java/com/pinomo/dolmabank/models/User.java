package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import kotlin.UByteArrayKt;

/**
 * User entity class
 * Must be 18 years old or older to be able to register.
 */
final public class User extends PayloadEntity{
    /**
     * User's name + maybe surname.
     */
    public String name;

    /**
     * User email (may be null).
     */
    @Nullable
    public String email;

    /**
     * User's phone number (may be null).
     */
    @Nullable
    public String phoneNumber;

    /**
     * User's address (may be null).
     */
    @Nullable
    public String address;

    /**
     * User's year of birth.
     */
    public int yearOfBirth;

    /**
     * User's month of birth.
     */
    public int monthOfBirth;

    /**
     * User's day of birth.
     */
    public int dayOfBirth;
}
