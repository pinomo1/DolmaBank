package com.pinomo.dolmabank.fragments;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.pinomo.dolmabank.DolmaBankApp;
import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.cards_recyclerview.MarginItemDecoration;
import com.pinomo.dolmabank.database.LocalPiggy;
import com.pinomo.dolmabank.database.LocalPiggyDao;
import com.pinomo.dolmabank.databinding.PiggyFragmentBinding;
import com.pinomo.dolmabank.piggy_recyclerview.PiggyBodyItem;
import com.pinomo.dolmabank.piggy_recyclerview.PiggyRecyclerAdapter;
import com.pinomo.dolmabank.piggy_recyclerview.PiggyRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PiggyFragment extends Fragment {
    /**
     * Gets binding.
     *
     * @return the binding
     */
    @NonNull
    public PiggyFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    @Nullable
    private PiggyFragmentBinding _binding = null;

    @Nullable
    private RecyclerView recyclerView;
    private PiggyRecyclerAdapter infoAdapter;
    private List<PiggyRecyclerViewItem> piggyArrayList;

    /**
     * Instantiates a new Banking fragment.
     */
    public PiggyFragment() {
        super(R.layout.piggy_fragment);
        piggyArrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = PiggyFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();
        recyclerView = getBinding().piggyRecycler;
        recyclerView.addItemDecoration(new MarginItemDecoration());
        getBinding().returnIcon.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_piggyFragment_to_homescreen);
        });
        getBinding().addPiggyButton.setOnClickListener(v -> {
            findNavController(this).navigate(R.id.action_piggyFragment_to_addPiggyFragment);
        });
        populateRecyclerView();

        return view;
    }

    private void populateRecyclerView() {
        DolmaBankApp app = (DolmaBankApp) requireActivity().getApplication();
        LocalPiggyDao localSmsDao = app.getDb().getLocalPiggyDao();
        List<LocalPiggy> localBankTransactions = localSmsDao.getAll();

        List<PiggyRecyclerViewItem> piggyItems = new ArrayList<>();

        for (LocalPiggy piggy : localBankTransactions) {
            piggyItems.add(createPiggyBodyItem(piggy));
        }

        piggyArrayList.addAll(piggyItems);

        infoAdapter = new PiggyRecyclerAdapter(piggyArrayList);
        assert recyclerView != null;
        recyclerView.setAdapter(infoAdapter);
    }

    private PiggyBodyItem createPiggyBodyItem(LocalPiggy piggy) {
        return new PiggyBodyItem(
                piggy
        );
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
        piggyArrayList.clear();
    }
}

