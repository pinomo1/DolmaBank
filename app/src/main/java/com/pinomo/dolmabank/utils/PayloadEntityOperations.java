package com.pinomo.dolmabank.utils;

import android.content.Context;

/**
 * The type Payload entity operations.
 */
public class PayloadEntityOperations {
    private Context context;
    private DatabaseAction databaseAction;

    /**
     * The enum Database action.
     */
    enum DatabaseAction {
        /**
         * Insert database action.
         */
        INSERT,
        /**
         * Delete database action.
         */
        DELETE
    }
}
