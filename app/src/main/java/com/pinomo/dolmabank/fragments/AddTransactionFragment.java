package com.pinomo.dolmabank.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.database.LocalBankTransaction;
import com.pinomo.dolmabank.databinding.AddCardFragmentBinding;
import com.pinomo.dolmabank.databinding.AddTransactionFragmentBinding;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.models.BankTransaction;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

/**
 * Fragment for adding a new card to the user's account
 */
public class AddTransactionFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public AddTransactionFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private AddTransactionFragmentBinding _binding = null;

    /**
     * Instantiates a new Add card fragment.
     */
    public AddTransactionFragment() {
        super(R.layout.add_transaction_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = AddTransactionFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        getBinding().returnIcon.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_addTransactionFragment_to_homescreen);
        });

        getBinding().button.setOnClickListener(v -> {
            String transactionName = getBinding().transactionName.getText().toString();
            try{
                Double.parseDouble(getBinding().transactionAmount.getText().toString());
            }
            catch (NumberFormatException e){
                getBinding().transactionAmount.setError("Invalid amount");
                return;
            }
            Double amount = Double.parseDouble(getBinding().transactionAmount.getText().toString());

            if (!isTransactionValid()){
                getBinding().transactionName.setError("Please enter a valid transaction name");
                return;
            }

            if (!isAmountValid()){
                getBinding().transactionAmount.setError("Please enter a valid amount");
                return;
            }

            DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.amount = amount;
            bankTransaction.name = transactionName;
            LocalBankTransaction localBankTransaction = new LocalBankTransaction();
            localBankTransaction.transaction = bankTransaction;
            app.getDb().getLocalBankTransactionDao().insert(localBankTransaction);

            findNavController(this).navigate(R.id.action_addTransactionFragment_to_homescreen);
        });

        return view;
    }

    private boolean isTransactionValid() {
        String transactionName = getBinding().transactionName.getText().toString();
        return !transactionName.isEmpty() || !transactionName.isBlank();
    }

    private boolean isAmountValid() {
        Double amount = Double.parseDouble(getBinding().transactionAmount.getText().toString());
        return !amount.isNaN() && amount != 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
