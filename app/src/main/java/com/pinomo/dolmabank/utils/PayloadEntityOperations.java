package com.pinomo.dolmabank.utils;

import android.content.Context;

public class PayloadEntityOperations {
    private Context context;
    private DatabaseAction databaseAction;

    enum DatabaseAction {
        INSERT,
        DELETE
    }
}
