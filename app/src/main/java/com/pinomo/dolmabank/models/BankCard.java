package com.pinomo.dolmabank.models;

/**
 * BankCard class is a model class that represents a bank card.
 */
final public class BankCard extends PayloadEntity {
    /**
     * The cardNumber is a string that represents the card number.
     */
    public String cardNumber;

    /**
     * The cardHolderName is a string that represents the card holder name.
     */
    public String cardHolderName;
    // public String expirationDate;

    /**
     * The cvv is a string that represents the cvv number.
     */
    public String cvv;

    /**
     * The bankName is a string that represents the bank name.
     */
    public String bankName;

    /**
     * The balance is a double that represents the card balance.
     */
    public Double balance;

    /**
     * The cardName is a string that represents the card name.
     */
    public String cardName;
    // public String currency;
    // public String cardType;
}
