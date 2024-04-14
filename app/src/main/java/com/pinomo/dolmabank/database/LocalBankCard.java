package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.pinomo.dolmabank.models.BankCard;

/**
 * LocalBankCard is a class that represents a bank card in the local database.
 */
@Entity
@Dao
public non-sealed class LocalBankCard extends LocalPayloadEntity {
    /**
     * The Bank card.
     */
    @Embedded
    public BankCard bankCard;
}
