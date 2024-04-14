package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.BankCard;

@Entity
@Dao
public non-sealed class LocalBankCard extends LocalPayloadEntity {
    @Embedded
    public BankCard bankCard;
}
