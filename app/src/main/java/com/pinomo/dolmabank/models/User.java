package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

import java.time.LocalDate;

import kotlin.UByteArrayKt;

final public class User extends PayloadEntity{
    public String name;
    @Nullable
    public String email;
    @Nullable
    public String phoneNumber;
    @Nullable
    public String address;
    public int yearOfBirth;
    public int monthOfBirth;
    public int dayOfBirth;
}
