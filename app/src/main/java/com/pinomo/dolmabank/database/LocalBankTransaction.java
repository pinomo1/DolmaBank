package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.BankTransaction;

@Entity
@Dao
public non-sealed class LocalBankTransaction extends LocalPayloadEntity {
    @Embedded
    BankTransaction transaction;
}
