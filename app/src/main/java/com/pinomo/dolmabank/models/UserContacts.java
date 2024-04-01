package com.pinomo.dolmabank.models;

import kotlin.UByteArrayKt;

final public class UserContacts extends PayloadEntity {
    String name;
    String email;
    String phoneNumber;
    String address;
    UByteArrayKt photo;
}
