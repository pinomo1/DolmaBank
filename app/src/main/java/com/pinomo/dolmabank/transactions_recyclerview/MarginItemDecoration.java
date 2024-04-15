package com.pinomo.dolmabank.transactions_recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This class is used to add margin to the bottom of the items in the RecyclerView.
 */
public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    /**
     * The size of the margin.
     */
    private Integer spaceSize;

    /**
     * Default constructor. The margin size is set to 25.
     */
    public MarginItemDecoration() {
        this(25);
    }

    /**
     * Constructor with a parameter. The margin size is set to the given value.
     *
     * @param spaceSize The size of the margin.
     */
    public MarginItemDecoration(Integer spaceSize) {
        super();
        this.spaceSize = spaceSize;
    }

    /**
     * Retrieve any offsets for the given item. Each field of outRect specifies the number of pixels
     * @param outRect Rect to receive the output.
     * @param view    The child view to decorate
     * @param parent  RecyclerView this ItemDecoration is decorating
     * @param state   The current state of RecyclerView.
     */
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = spaceSize;
    }
}
