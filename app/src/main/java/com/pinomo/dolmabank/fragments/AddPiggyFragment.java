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
import com.pinomo.dolmabank.database.LocalBankTransaction;
import com.pinomo.dolmabank.database.LocalPiggy;
import com.pinomo.dolmabank.databinding.AddPiggyFragmentBinding;
import com.pinomo.dolmabank.databinding.AddTransactionFragmentBinding;
import com.pinomo.dolmabank.models.BankTransaction;
import com.pinomo.dolmabank.models.Piggy;

public class AddPiggyFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public AddPiggyFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private AddPiggyFragmentBinding _binding = null;

    /**
     * Instantiates a new Add card fragment.
     */
    public AddPiggyFragment() {
        super(R.layout.add_piggy_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = AddPiggyFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        getBinding().returnIcon.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_addPiggyFragment_to_piggyFragment);
        });

        getBinding().button.setOnClickListener(v -> {
            String name = getBinding().purposeField.getText().toString();
            Double amount = Double.parseDouble(getBinding().goalField.getText().toString());
            Integer percentage = Integer.parseInt(getBinding().percentageField.getText().toString());

            if (name.isEmpty() || amount.isNaN() || amount <= 0 || percentage <= 0 || percentage > 100){
                return;
            }

            DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
            Piggy piggy = new Piggy();
            piggy.goal = amount;
            piggy.name = name;
            piggy.percentage = percentage;
            LocalPiggy localPiggy = new LocalPiggy();
            localPiggy.piggy = piggy;
            app.getDb().getLocalPiggyDao().insert(localPiggy);

            findNavController(this).navigate(R.id.action_addPiggyFragment_to_piggyFragment);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
