package com.pinomo.dolmabank.piggy_recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.database.LocalPiggy;
import com.pinomo.dolmabank.models.Piggy;

import java.util.List;
import java.util.function.Consumer;

/**
 * Adapter for the cards recycler view
 */
public class PiggyRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * List of items to be displayed in the recycler view
     */
    List<PiggyRecyclerViewItem> items;

    /**
     * Click listener for the items
     */
    Consumer<PiggyRecyclerViewItem> onClickListener;

    /**
     * Constructor
     *
     * @param items List of items to be displayed in the recycler view
     */
    public PiggyRecyclerAdapter(List<PiggyRecyclerViewItem> items) {
        this.items = items;
    }

    /**
     * Constructor
     *
     * @param items           List of items to be displayed in the recycler view
     * @param onClickListener Click listener for the items
     */
    public PiggyRecyclerAdapter(List<PiggyRecyclerViewItem> items, Consumer<PiggyRecyclerViewItem> onClickListener) {
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
        TextView name;
        /**
         * The Balance.
         */
        TextView amount;
        /**
         * The Card ending.
         */
        ProgressBar progressBar;

        /**
         * Gets name.
         *
         * @return the name
         */
        public TextView getName() {
            return name;
        }

        /**
         * Gets balance.
         *
         * @return the balance
         */
        public TextView getAmount() {
            return amount;
        }

        /**
         * Gets card ending.
         *
         * @return the card ending
         */
        public ProgressBar getProgressBar() {
            return progressBar;
        }

        /**
         * Instantiates a new Body item holder.
         *
         * @param itemView the item view
         */
        public BodyItemHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.piggybank_name);
            amount = itemView.findViewById(R.id.piggybank_amount);
            progressBar = itemView.findViewById(R.id.piggybank_progress);

            itemView.setOnClickListener(
                v -> {}
            );
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
        Log.i("CardsRecyclerAdapter", "onCreateViewHolder");
        return new BodyItemHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.piggy_item_layout, parent, false)
        );
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.i("CardsRecyclerAdapter", "onBindViewHolder");
        if (holder instanceof BodyItemHolder bodyItemHolder) {
            Log.i("CardsRecyclerAdapter", "onBindViewHolder: BodyItemHolder");
            PiggyBodyItem item = (PiggyBodyItem) items.get(position);
            LocalPiggy localPiggy = item.getLocalPiggy();
            Piggy piggy = localPiggy.piggy;
            bodyItemHolder.getName().setText(piggy.name);
            bodyItemHolder.getAmount().setText(piggy.percentage * piggy.goal / 100 + " / " + piggy.goal);
            bodyItemHolder.getProgressBar().setProgress(piggy.percentage);
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
