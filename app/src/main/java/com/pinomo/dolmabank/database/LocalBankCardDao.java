package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LocalBankCardDao extends BaseLocalEntityDao<LocalBankCard>
{
    @Query("SELECT * FROM LocalBankCard LIMIT 1")
    LocalBankCard getFirst();

    @Query("SELECT COALESCE(SUM(balance), 0) FROM LocalBankCard")
    Double getOverallBalance();

    @Override
    @Query("SELECT * FROM LocalBankCard")
    List<LocalBankCard> getAll();

    @Override
    @Query("SELECT * FROM LocalBankCard WHERE uid = :uid")
    LocalBankCard getById(Long uid);

    @Override
    @Insert
    Long insert(LocalBankCard entity);

    @Override
    @Delete
    void delete(LocalBankCard entity);

    @Override
    @Update
    void update(LocalBankCard entity);
}
