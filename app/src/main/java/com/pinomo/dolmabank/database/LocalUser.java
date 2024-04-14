package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.User;

@Entity
@Dao
public non-sealed class LocalUser extends LocalPayloadEntity {
    @Embedded
    public User user;
}
