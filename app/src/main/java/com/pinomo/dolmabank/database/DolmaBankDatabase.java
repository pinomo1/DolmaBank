package com.pinomo.dolmabank.database;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LocalBankTransaction.class}, version = 1, exportSchema = false)
abstract public class DolmaBankDatabase extends RoomDatabase {
    abstract LocalBankTransaction getLocalBankTransactionDao();

    @Nullable
    private static DolmaBankDatabase INSTANCE = null;

    public DolmaBankDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (this){
                INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        DolmaBankDatabase.class,
                        "dolma_bank_database"
                        )
                        .build();
            }
        }
        return INSTANCE;
    }
}
