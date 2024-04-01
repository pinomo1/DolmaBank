package com.pinomo.dolmabank.models;

import java.time.LocalDate;

import kotlin.UByteArrayKt;

final public class User extends PayloadEntity{
    String name;
    String email;
    String phoneNumber;
    String address;
    String language;
    LocalDate dateOfBirth;
    UByteArrayKt photo;
}
