package com.pinomo.dolmabank.transactions_recyclerview;

import com.pinomo.dolmabank.database.LocalBankTransaction;

public final class TransactionHeaderItem extends TransactionsRecyclerViewItem {
    public TransactionHeaderItem() {
        this.date = "Today";
    }

    /**
     * The LocalBankCard object that represents the card in the database.
     */
    String date;

    public String getDateString() {
        return date;
    }

    public void setDateString(String dateString) {
        this.date = dateString;
    }
}
