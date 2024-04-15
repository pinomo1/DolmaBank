package com.pinomo.dolmabank.transactions_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.database.LocalBankTransaction;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.models.BankTransaction;
import com.pinomo.dolmabank.utils.AppUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

import java.util.List;
import java.util.function.Consumer;

/**
 * Adapter for the cards recycler view
 */
public class TransactionsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * List of items to be displayed in the recycler view
     */
    List<TransactionsRecyclerViewItem> items;

    /**
     * Click listener for the items
     */
    Consumer<TransactionsRecyclerViewItem> onClickListener;

    /**
     * Constructor
     *
     * @param items List of items to be displayed in the recycler view
     */
    public TransactionsRecyclerAdapter(List<TransactionsRecyclerViewItem> items) {
        this.items = items;
    }

    /**
     * Constructor
     *
     * @param items           List of items to be displayed in the recycler view
     * @param onClickListener Click listener for the items
     */
    public TransactionsRecyclerAdapter(List<TransactionsRecyclerViewItem> items, Consumer<TransactionsRecyclerViewItem> onClickListener) {
        this.items = items;
        this.onClickListener = onClickListener;
    }

    /**
     * Class for the body item holder
     * This class is used to hold the views of the body items
     * The body items are the items that are displayed in the recycler view
     * R.layout.cardwallet_item_layout is the layout of the body items
     */
    class BodyItemHolder extends RecyclerView.ViewHolder{
        /**
         * The Name.
         */
        TextView transactionName;
        /**
         * The Balance.
         */
        TextView transactionAmount;

        /**
         * Gets name.
         *
         * @return the name
         */
        public TextView getTransactionName() {
            return transactionName;
        }

        /**
         * Gets balance.
         *
         * @return the balance
         */
        public TextView getTransactionAmount() {
            return transactionAmount;
        }

        /**
         * Instantiates a new Body item holder.
         *
         * @param itemView the item view
         */
        public BodyItemHolder(@NonNull View itemView) {
            super(itemView);
            transactionName = itemView.findViewById(R.id.transaction_name);
            transactionAmount = itemView.findViewById(R.id.transaction_amount);

            itemView.setOnClickListener(
                v -> {}
            );
        }
    }

    class HeaderItemHolder extends RecyclerView.ViewHolder{
        /**
         * The Name.
         */
        TextView dateString;

        /**
         * Gets name.
         *
         * @return the name
         */
        public TextView getDateString() {
            return dateString;
        }

        /**
         * Instantiates a new Body item holder.
         *
         * @param itemView the item view
         */
        public HeaderItemHolder(@NonNull View itemView) {
            super(itemView);
            dateString = itemView.findViewById(R.id.date_string);

            itemView.setOnClickListener(
                    v -> {}
            );
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof TransactionBodyItem) {
            return TransactionsRecyclerViewItem.TYPE_BODY;
        } else {
            return TransactionsRecyclerViewItem.TYPE_HEADER;
        }
    }


    /**
     * Create a new ViewHolder and initializes some private fields to be used by RecyclerView.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TransactionsRecyclerViewItem.TYPE_BODY) {
            return new BodyItemHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.history_item_layout, parent, false)
            );
        } else {
            return new HeaderItemHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.history_header_item_layout, parent, false)
            );
        }
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BodyItemHolder bodyItemHolder) {
            TransactionBodyItem item = (TransactionBodyItem) items.get(position);
            LocalBankTransaction localBankTransaction = item.getLocalBankTransaction();
            BankTransaction bankTransaction = localBankTransaction.transaction;
            bodyItemHolder.getTransactionName().setText(CryptoUtils.decrypt(bankTransaction.name));
            bodyItemHolder.getTransactionAmount().setText(AppUtils.formatBalance(bankTransaction.amount));
            if (bankTransaction.amount < 0) {
                bodyItemHolder.getTransactionAmount().setTextColor(0xFFFF0000);
            } else {
                bodyItemHolder.getTransactionAmount().setTextColor(0xFF00FF00);
            }
        }
        else if (holder instanceof HeaderItemHolder headerItemHolder) {
            TransactionHeaderItem item = (TransactionHeaderItem) items.get(position);
            headerItemHolder.getDateString().setText(item.getDateString());
        }
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }
}
