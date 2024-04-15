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
import com.pinomo.dolmabank.database.LocalBankTransaction;
import com.pinomo.dolmabank.database.LocalBankTransactionDao;
import com.pinomo.dolmabank.databinding.BankingFragmentBinding;
import com.pinomo.dolmabank.databinding.HistoryFragmentBinding;
import com.pinomo.dolmabank.databinding.RegisterLandingFragmentBinding;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.transactions_recyclerview.TransactionBodyItem;
import com.pinomo.dolmabank.transactions_recyclerview.TransactionHeaderItem;
import com.pinomo.dolmabank.transactions_recyclerview.TransactionsRecyclerAdapter;
import com.pinomo.dolmabank.transactions_recyclerview.TransactionsRecyclerViewItem;
import com.pinomo.dolmabank.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public HistoryFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private HistoryFragmentBinding _binding = null;

    @Nullable
    private RecyclerView recyclerView;
    private TransactionsRecyclerAdapter infoAdapter;
    private List<TransactionsRecyclerViewItem> cardArrayList;

    /**
     * Instantiates a new Banking fragment.
     */
    public HistoryFragment() {
        super(R.layout.history_fragment);
        cardArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = HistoryFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        recyclerView = getBinding().transactionRecyclerView;
        recyclerView.addItemDecoration(new MarginItemDecoration());
        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
        LocalBankTransactionDao localBankTransactionDao = app.getDb().getLocalBankTransactionDao();
        Double totalSpent = localBankTransactionDao.totalAmount();
        getBinding().todaySpentMoney.setText(AppUtils.formatBalance(totalSpent));
        populateRecyclerView();

        return view;
    }

    private void populateRecyclerView() {
        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
        LocalBankTransactionDao localSmsDao = app.getDb().getLocalBankTransactionDao();
        List<LocalBankTransaction> localBankTransactions = localSmsDao.getAll();

        List<TransactionsRecyclerViewItem> cardItems = new ArrayList<>();
        if (!localBankTransactions.isEmpty()) {
            cardItems.add(createCardHeaderItem());
        }

        for (LocalBankTransaction transaction : localBankTransactions) {
            cardItems.add(createCardBodyItem(transaction));
        }

        cardArrayList.addAll(cardItems);

        infoAdapter = new TransactionsRecyclerAdapter(cardArrayList);
        assert recyclerView != null;
        recyclerView.setAdapter(infoAdapter);
    }

    private TransactionBodyItem createCardBodyItem(LocalBankTransaction bankCard) {
        return new TransactionBodyItem(
                bankCard
        );
    }

    private TransactionHeaderItem createCardHeaderItem() {
        return new TransactionHeaderItem();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
        cardArrayList.clear();
    }
}
