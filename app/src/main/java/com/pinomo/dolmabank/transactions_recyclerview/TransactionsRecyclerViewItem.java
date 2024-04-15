package com.pinomo.dolmabank.transactions_recyclerview;

/**
 * Represents an item in the cards recycler view.
 */
public sealed class TransactionsRecyclerViewItem permits TransactionBodyItem, TransactionHeaderItem {
    static final int TYPE_HEADER = 0;
    static final int TYPE_BODY = 1;
}

