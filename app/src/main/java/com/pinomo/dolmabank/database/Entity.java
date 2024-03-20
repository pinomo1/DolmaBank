package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pinomo.dolmabank.models.BankTransaction;

sealed class LocalPayloadEntity {}

@Entity
@Dao
abstract non-sealed class LocalBankTransaction extends LocalPayloadEntity {
    @PrimaryKey(autoGenerate = true)
    Long uid = 0L;

    @Embedded
    BankTransaction transaction;
}
