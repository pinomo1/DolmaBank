package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
interface LocalBankCardDao extends BaseLocalEntityDao<LocalBankCard>
{
    @Override
    @Query("SELECT * FROM LocalBankCard")
    ArrayList<LocalBankCard> getAll();

    @Override
    @Query("SELECT * FROM LocalBankCard WHERE uid = :uid")
    LocalBankCard getById(Long uid);

    @Override
    @Insert
    Long insert(LocalBankCard entity);

    @Override
    @Delete
    void delete(LocalBankCard entity);
}
