package com.pinomo.dolmabank.piggy_recyclerview;

import com.pinomo.dolmabank.database.LocalPiggy;

/**
 * CardBodyItem is a subclass of CardsRecyclerViewItem that represents the body of a card.
 * It contains a LocalBankCard object that represents the card in the database.
 * For more information on LocalBankCard, see the LocalBankCard class in the database package.
 */
public final class PiggyBodyItem extends PiggyRecyclerViewItem {
    public PiggyBodyItem(LocalPiggy localPiggy) {
        this.localPiggy = localPiggy;
    }

    /**
     * The LocalBankCard object that represents the card in the database.
     */
    LocalPiggy localPiggy;

    /**
     * Returns the LocalBankCard object that represents the card in the database.
     *
     * @return the LocalBankCard object that represents the card in the database
     */
    public LocalPiggy getLocalPiggy() {
        return localPiggy;
    }

    /**
     * Sets the LocalBankCard object that represents the card in the database.
     *
     * @param localPiggy the LocalBankCard object that represents the card in the database
     */
    public void setLocalPiggy(LocalPiggy localPiggy) {
        this.localPiggy = localPiggy;
    }
}
