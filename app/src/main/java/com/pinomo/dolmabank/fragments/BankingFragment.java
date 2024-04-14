package com.pinomo.dolmabank.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.cards_recyclerview.CardBodyItem;
import com.pinomo.dolmabank.cards_recyclerview.CardsRecyclerAdapter;
import com.pinomo.dolmabank.cards_recyclerview.CardsRecyclerViewItem;
import com.pinomo.dolmabank.cards_recyclerview.MarginItemDecoration;
import com.pinomo.dolmabank.database.DolmaBankDatabase;
import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.database.LocalBankCardDao;
import com.pinomo.dolmabank.databinding.BankingFragmentBinding;
import com.pinomo.dolmabank.databinding.RegisterLandingFragmentBinding;
import com.pinomo.dolmabank.models.BankCard;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BankingFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public BankingFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private BankingFragmentBinding _binding = null;

    @Nullable
    private RecyclerView recyclerView;
    private CardsRecyclerAdapter infoAdapter;
    private List<CardsRecyclerViewItem> cardArrayList;

    /**
     * Instantiates a new Banking fragment.
     */
    public BankingFragment() {
        super(R.layout.banking_fragment);
        cardArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = BankingFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        recyclerView = getBinding().cardRecyclerView;
        recyclerView.addItemDecoration(new MarginItemDecoration());
        populateRecyclerView();

        getBinding().addCardButton.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_banking_to_addCardFragment);
        });

        return view;
    }

    private void populateRecyclerView() {
        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
        LocalBankCardDao localSmsDao = app.getDb().getLocalBankCardDao();
        List<LocalBankCard> localBankCards = localSmsDao.getAll();

        List<CardBodyItem> cardItems = new ArrayList<>();
        for (LocalBankCard card : localBankCards) {
            cardItems.add(createCardBodyItem(card));
        }

        cardArrayList.addAll(cardItems);

        infoAdapter = new CardsRecyclerAdapter(cardArrayList);
        assert recyclerView != null;
        recyclerView.setAdapter(infoAdapter);
    }

    private CardBodyItem createCardBodyItem(LocalBankCard bankCard) {
        return new CardBodyItem(
                bankCard
        );
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
        cardArrayList.clear();
    }
}
