package com.pinomo.dolmabank.models;

sealed class PayloadEntity permits BankCard, BankTransaction, User, UserContacts {
    Long id;
}
