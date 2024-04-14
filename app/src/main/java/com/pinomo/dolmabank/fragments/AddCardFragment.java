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
import com.pinomo.dolmabank.databinding.AddCardFragmentBinding;
import com.pinomo.dolmabank.databinding.RegisterLandingFragmentBinding;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

public class AddCardFragment extends Fragment {
    @NonNull
    public AddCardFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private AddCardFragmentBinding _binding = null;

    public AddCardFragment() {
        super(R.layout.add_card_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = AddCardFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        getBinding().returnIcon.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_addCardFragment_to_banking);
        });

        getBinding().button.setOnClickListener(v -> {
            if(!isCardNumberValid()){
                getBinding().cardNumber.setError("Invalid card number");
                return;
            }

            if(!isCvvValid()){
                getBinding().cvcCode.setError("Invalid cvv code");
                return;
            }

            String cardNumber = getBinding().cardNumber.getText().toString();
            String cvv = getBinding().cvcCode.getText().toString();
            String bankName = getBinding().bankName.getText().toString();
            String cardHolder = getBinding().cardholderName.getText().toString();
            String cardName = getBinding().cardName.getText().toString();

            if (cardNumber.isEmpty() || cvv.isEmpty() || bankName.isEmpty() || cardHolder.isEmpty() || cardName.isEmpty()) {
                return;
            }

            DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
            BankCard bankCard = new BankCard();
            bankCard.cardNumber = CryptoUtils.Companion.encrypt(cardNumber);
            bankCard.cvv = CryptoUtils.Companion.encrypt(cvv);
            bankCard.bankName = CryptoUtils.Companion.encrypt(bankName);
            bankCard.cardHolderName = CryptoUtils.Companion.encrypt(cardHolder);
            bankCard.cardName = CryptoUtils.Companion.encrypt(cardName);
            bankCard.balance = getRandBalance();
            LocalBankCard localBankCard = new LocalBankCard();
            localBankCard.bankCard = bankCard;
            app.getDb().getLocalBankCardDao().insert(localBankCard);

            findNavController(this).navigate(R.id.action_addCardFragment_to_banking);
        });

        return view;
    }

    private Double getRandBalance(){
        return Math.random() * 10000;
    }

    private boolean isCardNumberValid(){
        return getBinding().cardNumber.getText().toString().length() == 16;
    }

    private boolean isCvvValid(){
        return getBinding().cvcCode.getText().toString().length() == 3;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
