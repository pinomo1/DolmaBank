package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.BankTransaction;

/**
 * LocalBankTransaction is a class that represents a bank transaction in the local database.
 * It extends LocalPayloadEntity, which is a class that represents a payload entity in the local database.
 * It has a BankTransaction object as a field.
 */
@Entity
@Dao
public non-sealed class LocalBankTransaction extends LocalPayloadEntity {
    /**
     * The Transaction.
     */
    @Embedded
    public BankTransaction transaction;
}
