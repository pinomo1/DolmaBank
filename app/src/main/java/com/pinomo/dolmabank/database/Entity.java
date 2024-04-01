package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.models.BankTransaction;
import com.pinomo.dolmabank.models.User;
import com.pinomo.dolmabank.models.UserContacts;

sealed class LocalPayloadEntity {
    @PrimaryKey(autoGenerate = true)
    Long uid = 0L;
}

@Entity
@Dao
abstract non-sealed class LocalBankTransaction extends LocalPayloadEntity {
    @Embedded
    BankTransaction transaction;
}

@Entity
@Dao
abstract non-sealed class LocalUser extends LocalPayloadEntity {
    @Embedded
    User user;
}

@Entity
@Dao
abstract non-sealed class LocalUserContacts extends LocalPayloadEntity {
    @Embedded
    UserContacts userContacts;
}

@Entity
@Dao
abstract non-sealed class LocalBankCard extends LocalPayloadEntity {
    @Embedded
    BankCard bankCard;
}
