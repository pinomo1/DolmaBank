package com.pinomo.dolmabank.cards_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.utils.AppUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class CardsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CardsRecyclerViewItem> items;
    Consumer<CardsRecyclerViewItem> onClickListener;

    public CardsRecyclerAdapter(List<CardsRecyclerViewItem> items) {
        this.items = items;
    }

    public CardsRecyclerAdapter(List<CardsRecyclerViewItem> items, Consumer<CardsRecyclerViewItem> onClickListener) {
        this.items = items;
        this.onClickListener = onClickListener;
    }

    class BodyItemHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView balance;
        TextView cardEnding;

        public TextView getName() {
            return name;
        }

        public TextView getBalance() {
            return balance;
        }

        public TextView getCardEnding() {
            return cardEnding;
        }

        public BodyItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.card_name);
            balance = itemView.findViewById(R.id.card_balance);
            cardEnding = itemView.findViewById(R.id.card_ending);

            itemView.setOnClickListener(
                v -> {}
            );
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("CardsRecyclerAdapter", "onCreateViewHolder");
        return new BodyItemHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cardwallet_item_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("CardsRecyclerAdapter", "onBindViewHolder");
        if (holder instanceof BodyItemHolder bodyItemHolder) {
            Log.i("CardsRecyclerAdapter", "onBindViewHolder: BodyItemHolder");
            CardBodyItem item = (CardBodyItem) items.get(position);
            LocalBankCard localBankCard = item.getLocalBankCard();
            BankCard bankCard = localBankCard.bankCard;
            bodyItemHolder.getName().setText(CryptoUtils.Companion.decrypt(bankCard.bankName));
            bodyItemHolder.getBalance().setText(AppUtils.formatBalance(bankCard.balance));
            bodyItemHolder.getCardEnding().setText("•••• " + CryptoUtils.Companion.decrypt(bankCard.cardNumber).substring(bankCard.cardNumber.length()-4));
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
