package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * The interface Local bank transaction dao.
 */
@Dao
public interface LocalBankTransactionDao extends BaseLocalEntityDao<LocalBankTransaction>
{
    @Query("SELECT COALESCE(SUM(amount), 0) FROM LocalBankTransaction")
    Double totalAmount();

    @Override
    @Query("SELECT * FROM LocalBankTransaction")
    List<LocalBankTransaction> getAll();

    @Override
    @Query("SELECT * FROM LocalBankTransaction WHERE uid = :uid")
    LocalBankTransaction getById(Long uid);

    @Override
    @Insert
    Long insert(LocalBankTransaction entity);

    @Override
    @Delete
    void delete(LocalBankTransaction entity);

    @Override
    @Update
    void update(LocalBankTransaction entity);
}
