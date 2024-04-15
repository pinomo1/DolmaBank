package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocalPiggyDao extends BaseLocalEntityDao<LocalPiggy>
{

    @Override
    @Query("SELECT * FROM LocalPiggy")
    List<LocalPiggy> getAll();

    @Override
    @Query("SELECT * FROM LocalPiggy WHERE uid = :uid")
    LocalPiggy getById(Long uid);

    @Override
    @Insert
    Long insert(LocalPiggy entity);

    @Override
    @Delete
    void delete(LocalPiggy entity);

    @Override
    @Update
    void update(LocalPiggy entity);
}
