package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.Piggy;
import com.pinomo.dolmabank.models.User;

@Entity
@Dao
public non-sealed class LocalPiggy extends LocalPayloadEntity {
    @Embedded
    public Piggy piggy;
}
