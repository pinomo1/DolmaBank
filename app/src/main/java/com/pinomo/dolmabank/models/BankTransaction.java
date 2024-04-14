package com.pinomo.dolmabank.models;

import androidx.annotation.Nullable;

final public class BankTransaction extends PayloadEntity{
    public Double amount;
    public String name;
    @Nullable
    public String contactName;

}
