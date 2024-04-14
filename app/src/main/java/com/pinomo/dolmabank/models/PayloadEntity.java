package com.pinomo.dolmabank.models;

sealed class PayloadEntity permits BankCard, BankTransaction, User, UserContact {
    public Long id;
}
