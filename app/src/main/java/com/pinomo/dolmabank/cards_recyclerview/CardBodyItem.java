package com.pinomo.dolmabank.cards_recyclerview;

import com.pinomo.dolmabank.database.LocalBankCard;

public final class CardBodyItem extends CardsRecyclerViewItem {
    public CardBodyItem(LocalBankCard localBankCard) {
        this.localBankCard = localBankCard;
    }

    LocalBankCard localBankCard;

    public LocalBankCard getLocalBankCard() {
        return localBankCard;
    }

    public void setLocalBankCard(LocalBankCard localBankCard) {
        this.localBankCard = localBankCard;
    }
}
