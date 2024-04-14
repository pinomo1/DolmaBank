package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

import kotlin.UByteArrayKt;

final public class UserContact extends PayloadEntity {
    public String name;
    @Nullable
    public String email;
    public String phoneNumber;
    @Nullable
    public String address;
}
