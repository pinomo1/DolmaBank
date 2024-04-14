package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.UserContact;

/**
 * The type Local user contact.
 */
@Entity
@Dao
public non-sealed class LocalUserContact extends LocalPayloadEntity {
    /**
     * The User contacts.
     */
    @Embedded
    UserContact userContacts;
}