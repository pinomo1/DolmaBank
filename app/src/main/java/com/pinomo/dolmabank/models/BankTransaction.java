package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

/**
 * Represents a bank transaction.
 */
final public class BankTransaction extends PayloadEntity{
    /**
     * The amount of the transaction.
     */
    public Double amount;

    /**
     * The name of the transaction.
     */
    public String name;

}
