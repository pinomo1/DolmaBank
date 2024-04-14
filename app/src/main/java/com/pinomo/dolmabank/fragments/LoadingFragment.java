package com.pinomo.dolmabank.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pinomo.dolmabank.R;
import com.pinomo.dolmabank.databinding.LoadingFragmentBinding;

public class LoadingFragment extends Fragment {
    @Nullable
    private LoadingFragmentBinding _binding = null;

    @NonNull
    public LoadingFragmentBinding getBinding() {
        assert _binding != null;
        return _binding;
    }

    public LoadingFragment() {
        super(R.layout.loading_fragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = LoadingFragmentBinding.inflate(inflater, container, false);
        View view = _binding.getRoot();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }
}
