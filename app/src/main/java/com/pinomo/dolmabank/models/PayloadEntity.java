package com.pinomo.dolmabank.models;

/**
 * Represents a payload entity.
 */
sealed class PayloadEntity permits BankCard, BankTransaction, Piggy, User, UserContact {
    /**
     * The id of the entity.
     */
    public Long id;
}
