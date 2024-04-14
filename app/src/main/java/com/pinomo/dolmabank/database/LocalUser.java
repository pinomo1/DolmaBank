package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.User;

/**
 * The type Local user.
 */
@Entity
@Dao
public non-sealed class LocalUser extends LocalPayloadEntity {
    /**
     * The User.
     */
    @Embedded
    public User user;
}
