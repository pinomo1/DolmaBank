package com.pinomo.dolmabank.fragment;

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

    @Nullable
    public LoadingFragmentBinding getBinding() {
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
