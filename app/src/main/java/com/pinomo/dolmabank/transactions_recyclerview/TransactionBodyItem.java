package com.pinomo.dolmabank.transactions_recyclerview;

import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.database.LocalBankTransaction;

/**
 * CardBodyItem is a subclass of CardsRecyclerViewItem that represents the body of a card.
 * It contains a LocalBankCard object that represents the card in the database.
 * For more information on LocalBankCard, see the LocalBankCard class in the database package.
 */
public final class TransactionBodyItem extends TransactionsRecyclerViewItem {
    public TransactionBodyItem(LocalBankTransaction localBankTransaction) {
        this.localBankTransaction = localBankTransaction;
    }

    /**
     * The LocalBankCard object that represents the card in the database.
     */
    LocalBankTransaction localBankTransaction;

    public LocalBankTransaction getLocalBankTransaction() {
        return localBankTransaction;
    }

    public void setLocalBankTransaction(LocalBankTransaction localBankTransaction) {
        this.localBankTransaction = localBankTransaction;
    }
}
