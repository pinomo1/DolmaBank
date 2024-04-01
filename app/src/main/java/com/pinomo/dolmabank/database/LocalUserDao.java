package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
interface LocalUserDao extends BaseLocalEntityDao<LocalUser>
{
    @Override
    @Query("SELECT * FROM LocalUser")
    ArrayList<LocalUser> getAll();

    @Override
    @Query("SELECT * FROM LocalUser WHERE uid = :uid")
    LocalUser getById(Long uid);

    @Override
    @Insert
    Long insert(LocalUser entity);

    @Override
    @Delete
    void delete(LocalUser entity);
}
