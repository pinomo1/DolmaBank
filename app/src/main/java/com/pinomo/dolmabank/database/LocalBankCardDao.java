package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for LocalBankCard entity
 */
@Dao
public interface LocalBankCardDao extends BaseLocalEntityDao<LocalBankCard>
{
    /**
     * Get the first bank card in the database
     *
     * @return The first bank card
     */
    @Query("SELECT * FROM LocalBankCard LIMIT 1")
    LocalBankCard getFirst();

    /**
     * Get the overall balance of all bank cards
     *
     * @return The overall balance
     */
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
