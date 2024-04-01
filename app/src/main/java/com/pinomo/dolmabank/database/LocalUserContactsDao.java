package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;

@Dao
interface LocalUserContactsDao extends BaseLocalEntityDao<LocalUserContacts>
{
    @Override
    @Query("SELECT * FROM LocalUserContacts")
    ArrayList<LocalUserContacts> getAll();

    @Override
    @Query("SELECT * FROM LocalUserContacts WHERE uid = :uid")
    LocalUserContacts getById(Long uid);

    @Override
    @Insert
    Long insert(LocalUserContacts entity);

    @Override
    @Delete
    void delete(LocalUserContacts entity);
}
