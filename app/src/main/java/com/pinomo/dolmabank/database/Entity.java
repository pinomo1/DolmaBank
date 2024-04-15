package com.pinomo.dolmabank.database;

import androidx.room.PrimaryKey;

/**
 * Base class for all local entities.
 */
sealed class LocalPayloadEntity permits LocalBankCard, LocalBankTransaction, LocalPiggy, LocalUser, LocalUserContact {

    /**
     * Unique identifier for the entity.
     */
    @PrimaryKey(autoGenerate = true)
    Long uid;
}
