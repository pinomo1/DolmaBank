package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.UserContact;

@Entity
@Dao
public non-sealed class LocalUserContact extends LocalPayloadEntity {
    @Embedded
    UserContact userContacts;
}