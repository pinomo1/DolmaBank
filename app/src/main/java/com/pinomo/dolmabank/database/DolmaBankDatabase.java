package com.pinomo.dolmabank.database;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * DolmaBankDatabase is the main database (abstract) class that extends RoomDatabase.
 */
@Database(entities = {LocalBankTransaction.class, LocalUser.class, LocalUserContact.class, LocalBankCard.class}, version = 1, exportSchema = false)
abstract public class DolmaBankDatabase extends RoomDatabase {
    /**
     * Abstract method to get the LocalBankTransactionDao object.
     *
     * @return LocalBankTransactionDao object.
     */
    public abstract LocalBankTransactionDao getLocalBankTransactionDao();

    /**
     * Abstract method to get the LocalUserDao object.
     *
     * @return LocalUserDao object.
     */
    public abstract LocalUserDao getLocalUserDao();

    /**
     * Abstract method to get the LocalUserContactDao object.
     *
     * @return LocalUserContactDao object.
     */
    public abstract LocalUserContactDao getLocalUserContactDao();

    /**
     * Abstract method to get the LocalBankCardDao object.
     *
     * @return LocalBankCardDao object.
     */
    public abstract LocalBankCardDao getLocalBankCardDao();

    /**
     * Singleton pattern to get the instance of the DolmaBankDatabase.
     */
    @Nullable
    private static DolmaBankDatabase INSTANCE = null;

    /**
     * Method to get the instance of the DolmaBankDatabase.
     *
     * @param context Context object.
     * @return DolmaBankDatabase object.
     */
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
