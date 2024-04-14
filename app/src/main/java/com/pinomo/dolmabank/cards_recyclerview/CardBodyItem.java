package com.pinomo.dolmabank.cards_recyclerview;

import com.pinomo.dolmabank.database.LocalBankCard;

/**
 * CardBodyItem is a subclass of CardsRecyclerViewItem that represents the body of a card.
 * It contains a LocalBankCard object that represents the card in the database.
 * For more information on LocalBankCard, see the LocalBankCard class in the database package.
 */
public final class CardBodyItem extends CardsRecyclerViewItem {
    /**
     * Instantiates a new Card body item.
     *
     * @param localBankCard the local bank card
     */
    public CardBodyItem(LocalBankCard localBankCard) {
        this.localBankCard = localBankCard;
    }

    /**
     * The LocalBankCard object that represents the card in the database.
     */
    LocalBankCard localBankCard;

    /**
     * Returns the LocalBankCard object that represents the card in the database.
     *
     * @return the LocalBankCard object that represents the card in the database
     */
    public LocalBankCard getLocalBankCard() {
        return localBankCard;
    }

    /**
     * Sets the LocalBankCard object that represents the card in the database.
     *
     * @param localBankCard the LocalBankCard object that represents the card in the database
     */
    public void setLocalBankCard(LocalBankCard localBankCard) {
        this.localBankCard = localBankCard;
    }
}
