package com.pinomo.dolmabank.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * The interface Local user dao.
 */
@Dao
public interface LocalUserDao extends BaseLocalEntityDao<LocalUser>
{
    /**
     * Gets first.
     *
     * @return the first
     */
    @Query("SELECT * FROM LocalUser LIMIT 1")
    LocalUser getFirst();

    @Override
    @Update
    void update(LocalUser entity);

    @Override
    @Query("SELECT * FROM LocalUser")
    List<LocalUser> getAll();

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
