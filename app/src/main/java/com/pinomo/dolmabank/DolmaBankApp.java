package com.pinomo.dolmabank;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import androidx.room.Room;

import com.pinomo.dolmabank.database.DolmaBankDatabase;

import java.util.Locale;

/**
 * Application class for DolmaBank
 */
public class DolmaBankApp extends Application {

    /**
     * Database instance
     */
    DolmaBankDatabase db;

    /**
     * Get the database instance
     *
     * @return the database instance
     */
    public DolmaBankDatabase getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        db = Room.databaseBuilder(getApplicationContext(),
                DolmaBankDatabase.class, "dolmabank-db"
        ).allowMainThreadQueries().build();
    }
}
