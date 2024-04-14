package com.pinomo.dolmabank.database;

import androidx.room.PrimaryKey;

sealed class LocalPayloadEntity permits LocalBankCard, LocalBankTransaction, LocalUser, LocalUserContact {
    @PrimaryKey(autoGenerate = true)
    Long uid;
}
