package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
interface LocalBankTransactionDao extends BaseLocalEntityDao<LocalBankTransaction>
{
    @Override
    @Query("SELECT * FROM LocalBankTransaction")
    ArrayList<LocalBankTransaction> getAll();

    @Override
    @Query("SELECT * FROM LocalBankTransaction WHERE uid = :uid")
    LocalBankTransaction getById(Long uid);

    @Override
    @Insert
    Long insert(LocalBankTransaction entity);

    @Override
    @Delete
    void delete(LocalBankTransaction entity);
}
