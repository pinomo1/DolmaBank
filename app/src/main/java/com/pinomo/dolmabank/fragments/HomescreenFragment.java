package com.pinomo.dolmabank.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.database.LocalBankCard;
import com.pinomo.dolmabank.database.LocalBankCardDao;
import com.pinomo.dolmabank.database.LocalUser;
import com.pinomo.dolmabank.database.LocalUserDao;
import com.pinomo.dolmabank.databinding.HomescreenFragmentBinding;
import com.pinomo.dolmabank.models.BankCard;
import com.pinomo.dolmabank.models.User;
import com.pinomo.dolmabank.utils.AppUtils;
import com.pinomo.dolmabank.utils.crypto.CryptoUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomescreenFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public HomescreenFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private HomescreenFragmentBinding _binding = null;

    /**
     * Instantiates a new Homescreen fragment.
     */
    public HomescreenFragment() {
        super(R.layout.homescreen_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = HomescreenFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();

        LocalUserDao userDao = app.getDb().getLocalUserDao();
        LocalUser localUser = userDao.getFirst();
        User user = localUser.user;
        getBinding().username.setText(getString(R.string.welcome_name, CryptoUtils.decrypt(user.name)));

        LocalBankCardDao bankCardDao = app.getDb().getLocalBankCardDao();
        LocalBankCard localBankCard = bankCardDao.getFirst();
        if (localBankCard != null) {
            BankCard bankCard = localBankCard.bankCard;
            getBinding().noCardYet.setVisibility(View.GONE);
            getBinding().cardBankName.setText(CryptoUtils.decrypt(bankCard.bankName));
            String cardNum = CryptoUtils.decrypt(bankCard.cardNumber);
            StringBuilder formattedCardNum = new StringBuilder();
            for (int i = 0; i < cardNum.length(); i++) {
                if (i % 4 == 0 && i != 0) {
                    formattedCardNum.append(" ");
                }
                formattedCardNum.append(cardNum.charAt(i));
            }
            getBinding().cardId.setText(formattedCardNum.toString());
            getBinding().usernameCardId.setText(CryptoUtils.decrypt(bankCard.cardHolderName));
            getBinding().cvvNumberId.setText(CryptoUtils.decrypt(bankCard.cvv));
        }
        else{
            FrameLayout frameLayout = getBinding().frameLayout;
            for (int i = 0; i < frameLayout.getChildCount(); i++) {
                View child = frameLayout.getChildAt(i);
                child.setVisibility(View.INVISIBLE);
            }
            getBinding().noCardYet.setVisibility(View.VISIBLE);
        }

        getBinding().photoFrame.setOnClickListener(v -> {
            BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.nav_view);
            bottomNavigationView.setSelectedItemId(R.id.profile);
        });

        getBinding().cardsId.setOnClickListener(v -> {
            BottomNavigationView bottomNavigationView = requireActivity().findViewById(R.id.nav_view);
            bottomNavigationView.setSelectedItemId(R.id.banking);
        });

        try {
            Double balance = bankCardDao.getOverallBalance();
            getBinding().yourMoney.setText(AppUtils.formatBalance(balance));
            Log.i("HomescreenFragment", "Got balance: " + balance.toString());
        }
        catch (Exception e) {
            Log.e("HomescreenFragment", "Failed to get balance", e);
            getBinding().yourMoney.setText("$0.00");
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
