package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface Local user contact dao.
 */
@Dao
public interface LocalUserContactDao extends BaseLocalEntityDao<LocalUserContact>
{
    @Override
    @Query("SELECT * FROM LocalUserContact")
    List<LocalUserContact> getAll();

    @Override
    @Query("SELECT * FROM LocalUserContact WHERE uid = :uid")
    LocalUserContact getById(Long uid);

    @Override
    @Insert
    Long insert(LocalUserContact entity);

    @Override
    @Delete
    void delete(LocalUserContact entity);

    @Override
    @Update
    void update(LocalUserContact entity);
}
